package com.iceman.shopperdrive.navigation

import androidx.annotation.StringRes
import com.iceman.shopperdrive.R
enum class SdScreens(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    Detail(title = R.string.detail_title),
    History(title = R.string.summary_title),


}