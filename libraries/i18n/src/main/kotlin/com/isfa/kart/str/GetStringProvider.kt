package com.isfa.kart.str

interface GetStringProvider {

    fun get(key: StringKey, vararg args: Any): String
}