package com.isfa.kart.str.impl

import com.isfa.kart.str.ErrorStringProvider
import com.isfa.kart.str.GetStringProvider
import com.isfa.kart.str.StringProvider

class GlobalStringProvider(
    private val getStringProvider: GetStringProvider,
) : StringProvider,
    GetStringProvider by getStringProvider,
    ErrorStringProvider by KartErrorStringProvider(getStringProvider)