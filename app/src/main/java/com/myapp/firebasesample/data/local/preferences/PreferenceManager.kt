package com.myapp.firebasesample.data.local.preferences

import android.content.Context

/**
 * Preference制御管理
 */
class PreferenceManager constructor(val context: Context) {

    companion object{
        private const val PREFERENCE_NAME = "firebaseSample"
    }

    /**
     * キー
     *
     */
    class Key {
        /**
         * Boolean型指定
         */
        enum class BooleanKey {
            // FCM
            FCM,
        }
    }

    /**
     * Int型格納
     *
     * @param key   キー
     * @param value 格納する値
     */
    fun setBoolean(
        key: Key.BooleanKey,
        value: Boolean
    ) {
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(key.name, value)
        editor.apply()
    }

    /**
     * Int型取得
     *
     * @param key キー
     * @return キーに紐づくInt型オブジェクト
     */
    fun getBoolean(key: Key.BooleanKey): Boolean {
        val defaultValue = true
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return preferences.getBoolean(key.name, defaultValue)
    }

}
