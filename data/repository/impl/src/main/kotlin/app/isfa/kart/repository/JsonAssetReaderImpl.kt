package app.isfa.kart.repository

import android.content.Context
import app.isfa.kart.repository.api.JsonAssetReader

class JsonAssetReaderImpl(private val context: Context) : JsonAssetReader {

    override fun read(resourceId: Int): String {
        return context.resources.openRawResource(resourceId)
            .bufferedReader()
            .use { it.readText() }
    }
}