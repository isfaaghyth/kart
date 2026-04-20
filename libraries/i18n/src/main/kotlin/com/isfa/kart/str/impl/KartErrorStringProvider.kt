package com.isfa.kart.str.impl

import app.isfa.kart.i18n.R
import com.isfa.kart.str.ErrorStringProvider
import com.isfa.kart.str.GetStringProvider

class KartErrorStringProvider(private val provider: GetStringProvider) : ErrorStringProvider {

    override fun duplicateAccountId() = provider.get(
        key = R.string.error_duplicate_account_id
    )

    override fun unexpectedError() = provider.get(
        key = R.string.error_unexpected_flow
    )
}