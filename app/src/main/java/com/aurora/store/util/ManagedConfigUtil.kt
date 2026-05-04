package com.aurora.store.util

import android.content.Context
import android.content.RestrictionsManager

object ManagedConfigUtil {

    fun isStoreImagesDisabled(context: Context): Boolean {
        val restrictionsManager =
            context.getSystemService(Context.RESTRICTIONS_SERVICE) as? RestrictionsManager
        val restrictions = restrictionsManager?.applicationRestrictions

        return restrictions?.getBoolean(
            Preferences.PREFERENCE_MANAGED_DISABLE_STORE_IMAGES,
            Preferences.getBoolean(context, Preferences.PREFERENCE_MANAGED_DISABLE_STORE_IMAGES)
        ) ?: Preferences.getBoolean(context, Preferences.PREFERENCE_MANAGED_DISABLE_STORE_IMAGES)
    }

    fun syncManagedConfigurations(context: Context) {
        val disableStoreImages = isStoreImagesDisabled(context)
        Preferences.putBooleanNow(
            context,
            Preferences.PREFERENCE_MANAGED_DISABLE_STORE_IMAGES,
            disableStoreImages
        )
    }
}
