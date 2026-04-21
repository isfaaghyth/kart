package app.isfa.kart.repository.api

interface JsonAssetReader {

    fun read(resourceId: Int): String
}
