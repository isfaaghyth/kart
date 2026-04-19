package app.isfa.kart.db.api.source.brand

import app.isfa.kart.db.api.BrandTypeOf

/**
 * Domain-level representation of a brand / merchant stored in [brand_infos].
 * Shared by both [KartMembershipModel] and [KartSubscriptionModel].
 */
data class KartBrandModel(
    /** Unique stable identifier for the brand (also acts as FK in kart_members / kart_subscriptions). */
    val slug: String,

    /** Display name of the brand (e.g. "UNIQLO", "Netflix"). */
    val name: String,

    /** Whether this brand is used for memberships or subscriptions. */
    val type: BrandTypeOf,

    /** URL to the brand's favicon / logo asset. */
    val faviconUrl: String,

    /** Brand's website URL. */
    val url: String,

    /**
     * Serialised card colour pair, e.g. "#FF5733,#C70039".
     * The UI layer is responsible for parsing this into actual colours.
     */
    val colors: String,
)
