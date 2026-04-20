@file:Suppress("SameParameterValue")

package app.isfa.kart.repository.fixture

import app.isfa.kart.db.api.BrandTypeOf
import app.isfa.kart.db.api.CardType
import app.isfa.kart.db.api.MerchantCategory
import app.isfa.kart.db.api.SubscriptionType
import app.isfa.kart.db.api.source.brand.KartBrandModel
import app.isfa.kart.db.api.source.membership.KartMembershipModel
import app.isfa.kart.db.api.source.subscription.KartSubscriptionModel

fun retailBrand(slug: String, name: String) = KartBrandModel(
    slug = slug,
    name = name,
    type = BrandTypeOf.Membership,
    category = MerchantCategory.Retail,
    faviconUrl = "",
    url = "",
    colors = "",
)

fun entertainmentBrand(slug: String, name: String) = KartBrandModel(
    slug = slug,
    name = name,
    type = BrandTypeOf.Subscription,
    category = MerchantCategory.Entertainment,
    faviconUrl = "",
    url = "",
    colors = "",
)

fun memberModel(id: Int, accountId: String, brand: KartBrandModel) =
    KartMembershipModel(
        id = id,
        accountId = accountId,
        cardType = CardType.Barcode,
        brand = brand
    )

fun subscriptionModel(id: Int, accountId: String, brand: KartBrandModel) =
    KartSubscriptionModel(
        id = id,
        accountId = accountId,
        cardType = CardType.Numeric,
        subscriptionType = SubscriptionType.Monthly,
        brand = brand,
    )