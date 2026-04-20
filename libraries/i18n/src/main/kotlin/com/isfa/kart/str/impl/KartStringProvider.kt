package com.isfa.kart.str.impl

import android.content.Context
import com.isfa.kart.str.GetStringProvider
import com.isfa.kart.str.StringKey

class KartStringProvider(private val context: Context) : GetStringProvider {

    override fun get(key: StringKey, vararg args: Any): String {
        return context.getString(key, *args)
    }
}