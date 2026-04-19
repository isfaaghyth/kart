package app.isfa.kart.db.api

sealed interface BrandTypeOf {
    data object Membership : BrandTypeOf
    data object Subscription : BrandTypeOf
}