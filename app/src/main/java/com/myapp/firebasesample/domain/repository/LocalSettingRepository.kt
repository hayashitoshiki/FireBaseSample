package com.myapp.firebasesample.domain.repository

/**
 * 設定値管理
 *
 */
interface LocalSettingRepository {

    /**
     * FCM設定値取得
     *
     * @return 設定値
     */
    fun getFcmData() : Boolean

    /**
     * FCM設定値設定
     *
     * @param frg 設定値
     */
    fun setFcmData(frg: Boolean)
}