package app.isfa.kart.repository

import app.isfa.kart.db.api.BrandTypeOf
import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.MerchantCategory
import app.isfa.kart.db.api.SubscriptionType
import app.isfa.kart.db.api.source.membership.CreateKartMembershipModel
import app.isfa.kart.db.api.source.subscription.CreateKartSubscriptionModel
import app.isfa.kart.repository.api.KartCardUiModel
import app.isfa.kart.repository.fake.FakeMembershipDataSource
import app.isfa.kart.repository.fake.FakeSubscriptionDataSource
import app.isfa.kart.repository.fixture.entertainmentBrand
import app.isfa.kart.repository.fixture.memberModel
import app.isfa.kart.repository.fixture.retailBrand
import app.isfa.kart.repository.fixture.subscriptionModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class KartCardRepositoryTest {

    private lateinit var membershipDataSource: FakeMembershipDataSource
    private lateinit var subscriptionDataSource: FakeSubscriptionDataSource
    private lateinit var fetchRepository: KartCardRepositoryImpl
    private lateinit var addRepository: KartAddCardRepositoryImpl
    private lateinit var searchRepository: KartSearchCardRepositoryImpl

    private val uniqlo = retailBrand("uniqlo", "UNIQLO")
    private val hAndM   = retailBrand("h-and-m", "H&M")
    private val netflix = entertainmentBrand("netflix", "Netflix")

    @Before
    fun setUp() {
        membershipDataSource = FakeMembershipDataSource(
            listOf(
                memberModel(id = 1, accountId = "ACC-001", brand = uniqlo),
                memberModel(id = 2, accountId = "ACC-002", brand = hAndM),
            )
        )
        subscriptionDataSource = FakeSubscriptionDataSource(
            listOf(
                subscriptionModel(id = 3, accountId = "NF-999", brand = netflix),
            )
        )

        fetchRepository = KartCardRepositoryImpl(membershipDataSource, subscriptionDataSource)
        addRepository = KartAddCardRepositoryImpl(membershipDataSource, subscriptionDataSource)
        searchRepository = KartSearchCardRepositoryImpl(fetchRepository)
    }

    @Test
    fun `allCards returns combined list sorted by id descending`() = runTest {
        val cards = fetchRepository.allCards().first()

        assertEquals(3, cards.size)
        assertEquals(3, cards[0].id) // netflix (id=3) first
        assertEquals(2, cards[1].id) // H&M     (id=2)
        assertEquals(1, cards[2].id) // UNIQLO   (id=1)
    }

    @Test
    fun `allCards emits updated list when data source changes`() = runTest {
        // Start with 2 members + 1 sub
        assertEquals(3, fetchRepository.allCards().first().size)

        // Add another member
        membershipDataSource.emit(
            listOf(
                memberModel(id = 1, accountId = "ACC-001", brand = uniqlo),
                memberModel(id = 2, accountId = "ACC-002", brand = hAndM),
                memberModel(id = 4, accountId = "ACC-NEW", brand = uniqlo),
            )
        )

        assertEquals(4, fetchRepository.allCards().first().size)
    }

    @Test
    fun `members returns only Member cards`() = runTest {
        val cards = fetchRepository.members().first()

        assertEquals(2, cards.size)
        assertTrue(cards.any { it.brand.name == "UNIQLO" })
        assertTrue(cards.any { it.brand.name == "H&M" })
    }

    @Test
    fun `members returns empty when no membership cards exist`() = runTest {
        membershipDataSource.emit(emptyList())

        val cards = fetchRepository.members().first()

        assertTrue(cards.isEmpty())
    }

    @Test
    fun `subscriptions returns only Subscription cards`() = runTest {
        val cards = fetchRepository.subscriptions().first()

        assertEquals(1, cards.size)
        assertEquals("Netflix", cards[0].brand.name)
    }

    @Test
    fun `subscriptions returns empty when no subscription cards exist`() = runTest {
        subscriptionDataSource.emit(emptyList())

        val cards = fetchRepository.subscriptions().first()

        assertTrue(cards.isEmpty())
    }

    @Test
    fun `search by brand name returns matching cards case-insensitively`() = runTest {
        val cards = searchRepository.search(keyword = "uniq").first()

        assertEquals(1, cards.size)
        assertEquals("UNIQLO", cards[0].brand.name)
    }

    @Test
    fun `search by accountId returns matching cards`() = runTest {
        val cards = searchRepository.search(keyword = "NF-999").first()

        assertEquals(1, cards.size)
        assertEquals("Netflix", cards[0].brand.name)
    }

    @Test
    fun `search with blank keyword returns all cards`() = runTest {
        val cards = searchRepository.search(keyword = "").first()

        assertEquals(3, cards.size)
    }

    @Test
    fun `search with type filter restricts to Membership cards only`() = runTest {
        val cards = searchRepository.search(keyword = "", type = BrandTypeOf.Membership).first()

        assertEquals(2, cards.size)
        assertTrue(cards.all { it.brand.type == BrandTypeOf.Membership })
    }

    @Test
    fun `search with type filter restricts to Subscription cards only`() = runTest {
        val cards = searchRepository.search(keyword = "", type = BrandTypeOf.Subscription).first()

        assertEquals(1, cards.size)
        assertTrue(cards.all { it.brand.type == BrandTypeOf.Subscription })
    }

    @Test
    fun `search with keyword and type filter combines both predicates`() = runTest {
        val cards = searchRepository.search(keyword = "uni", type = BrandTypeOf.Membership).first()

        assertEquals(1, cards.size)
        assertEquals("UNIQLO", cards[0].brand.name)
    }

    @Test
    fun `search returns empty when no cards match keyword`() = runTest {
        val cards = searchRepository.search(keyword = "zzznotexist").first()

        assertTrue(cards.isEmpty())
    }

    @Test
    fun `filterByCategory returns cards of matching category`() = runTest {
        val cards = searchRepository.filterByCategory(MerchantCategory.Retail).first()

        assertEquals(2, cards.size)
        assertTrue(cards.all { it.brand.category == MerchantCategory.Retail })
    }

    @Test
    fun `filterByCategory Entertainment returns only subscription cards`() = runTest {
        val cards = searchRepository.filterByCategory(MerchantCategory.Entertainment).first()

        assertEquals(1, cards.size)
        assertEquals("Netflix", cards[0].brand.name)
    }

    @Test
    fun `filterByCategory with no matches returns empty list`() = runTest {
        val cards = searchRepository.filterByCategory(MerchantCategory.Gas).first()

        assertTrue(cards.isEmpty())
    }

    @Test
    fun `addMember returns success when insertion succeeds`() = runTest {
        val model = CreateKartMembershipModel(
            brandSlug = "uniqlo",
            accountId = "NEW-111",
            cardType = CardType.Barcode,
        )
        membershipDataSource.insertResult = Result.success(true)

        val result = addRepository.addMember(model)

        assertTrue(result.isSuccess)
        assertEquals(true, result.getOrNull())
    }

    @Test
    fun `addMember returns failure when insertion fails`() = runTest {
        val model = CreateKartMembershipModel(
            brandSlug = "uniqlo",
            accountId = "NEW-111",
            cardType = CardType.Barcode,
        )
        val exception = RuntimeException("Database error")
        membershipDataSource.insertResult = Result.failure(exception)

        val result = addRepository.addMember(model)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `addSubscription returns success when insertion succeeds`() = runTest {
        val model = CreateKartSubscriptionModel(
            brandSlug = "netflix",
            accountId = "NF-NEW",
            cardType = CardType.Numeric,
            subscriptionType = SubscriptionType.Annual,
            expirationDate = 0
        )
        subscriptionDataSource.insertResult = Result.success(true)

        val result = addRepository.addSubscription(model)

        assertTrue(result.isSuccess)
        assertEquals(true, result.getOrNull())
    }

    @Test
    fun `addSubscription returns failure when insertion fails`() = runTest {
        val model = CreateKartSubscriptionModel(
            brandSlug = "netflix",
            accountId = "NF-NEW",
            cardType = CardType.Numeric,
            subscriptionType = SubscriptionType.Annual,
            expirationDate = 0
        )
        val exception = RuntimeException("Database error")
        subscriptionDataSource.insertResult = Result.failure(exception)

        val result = addRepository.addSubscription(model)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `allCards maps membership rows to KartCard Member`() = runTest {
        val cards = fetchRepository.allCards().first()
        val members = cards.filterIsInstance<KartCardUiModel.Member>()

        assertFalse(members.isEmpty())
        assertTrue(members.all { it.brand.type == BrandTypeOf.Membership })
    }

    @Test
    fun `allCards maps subscription rows to KartCard Subscription with subscriptionType`() = runTest {
        val cards = fetchRepository.allCards().first()
        val subs = cards.filterIsInstance<KartCardUiModel.Subscription>()

        assertEquals(1, subs.size)
        assertEquals(SubscriptionType.Monthly, subs[0].subscriptionType)
    }
}
