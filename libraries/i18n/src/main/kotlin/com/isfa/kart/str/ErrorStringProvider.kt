package com.isfa.kart.str

interface ErrorStringProvider {

    fun duplicateAccountId(): String
    fun unexpectedError(): String
}