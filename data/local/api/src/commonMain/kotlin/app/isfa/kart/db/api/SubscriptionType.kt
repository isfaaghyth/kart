package app.isfa.kart.db.api

enum class SubscriptionType(val subType: String) {
    Trial("trial"),
    Daily("daily"),
    Weekly("weekly"),
    Monthly("monthly"),
    Quarterly("quarterly"),
    SemiAnnual("semiannual"),
    Annual("annual"),
    TwoYears("2years"),
    FiveYears("5years"),
    Lifetime("lifetime"),
    None("none");
}