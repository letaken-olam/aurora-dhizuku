package com.aurora.store.util

import android.content.Context
import android.os.Bundle
import android.os.UserManager

object ManagedConfiguration {

    const val HIDE_APP_DETAILS_SCREENSHOTS = "hide_app_details_screenshots"

    fun getBoolean(context: Context, key: String, default: Boolean = false): Boolean {
        val restrictions = getRestrictions(context)
        return if (restrictions.containsKey(key)) restrictions.getBoolean(key) else default
    }

    private fun getRestrictions(context: Context): Bundle {
        val userManager = context.getSystemService(UserManager::class.java)
        return userManager?.applicationRestrictions ?: Bundle.EMPTY
    }
}
