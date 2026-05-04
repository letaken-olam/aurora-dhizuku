package com.aurora.store.util

import android.content.Context
import android.content.RestrictionsManager

object ManagedConfigUtil {
    private const val LEGACY_DISABLE_STORE_IMAGES_KEY = "disable_store_images"

    fun isStoreImagesDisabled(context: Context): Boolean {
        val restrictionsManager =
            context.getSystemService(Context.RESTRICTIONS_SERVICE) as? RestrictionsManager
        val restrictions = restrictionsManager?.applicationRestrictions
        val key = Preferences.PREFERENCE_MANAGED_DISABLE_STORE_IMAGES
        val fallback = Preferences.getBoolean(context, key)

        val rawValue = restrictions?.get(key) ?: restrictions?.get(LEGACY_DISABLE_STORE_IMAGES_KEY)
        if (rawValue is Boolean) return rawValue
        if (rawValue is String) return rawValue.equals("true", ignoreCase = true)
        if (rawValue is Int) return rawValue != 0

        return restrictions?.getBoolean(key, fallback)
            ?: restrictions?.getBoolean(LEGACY_DISABLE_STORE_IMAGES_KEY, fallback)
            ?: fallback
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
