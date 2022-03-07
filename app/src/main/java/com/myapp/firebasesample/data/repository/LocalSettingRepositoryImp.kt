package com.myapp.firebasesample.data.repository

import com.myapp.firebasesample.data.local.preferences.PreferenceManager
import com.myapp.firebasesample.domain.repository.LocalSettingRepository

class LocalSettingRepositoryImp(
    private val preferenceManager: PreferenceManager
): LocalSettingRepository {

    override fun getFcmData(): Boolean {
        return preferenceManager.getBoolean(PreferenceManager.Key.BooleanKey.FCM)
    }

    override fun setFcmData(frg: Boolean) {
        preferenceManager.setBoolean(PreferenceManager.Key.BooleanKey.FCM, frg)
    }
}