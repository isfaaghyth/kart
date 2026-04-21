package app.isfa.kart.repository.api

interface RepositoryDependencies {

    fun kartFetchCardRepository(): KartCardRepository
    fun kartSearchCardRepository(): KartSearchCardRepository
    fun kartAddCardRepository(): KartAddCardRepository
    fun kartBrandInfoRepository(): KartBrandInfoRepository
}