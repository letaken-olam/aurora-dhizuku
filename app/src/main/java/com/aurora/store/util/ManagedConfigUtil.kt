package com.aurora.store.util

import android.content.Context
import android.content.RestrictionsManager
import android.os.Bundle

object ManagedConfigUtil {

    fun syncManagedConfigurations(context: Context) {
        val restrictionsManager =
            context.getSystemService(Context.RESTRICTIONS_SERVICE) as? RestrictionsManager ?: return
        val restrictions: Bundle = restrictionsManager.applicationRestrictions

        val disableStoreImages = restrictions.getBoolean(
            Preferences.PREFERENCE_MANAGED_DISABLE_STORE_IMAGES,
            false
        )

        Preferences.putBooleanNow(
            context,
            Preferences.PREFERENCE_MANAGED_DISABLE_STORE_IMAGES,
            disableStoreImages
        )
    }
}
