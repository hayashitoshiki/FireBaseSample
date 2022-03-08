package com.myapp.firebasesample.domain.usecase

/**
 * 設定用UseCase
 *
 */
interface SettingUseCase {

    /**
     * Fcm設定値取得
     *
     * @return 設定値
     */
    fun getFcmEnable() : Boolean

    /**
     * Fcm値設定
     *
     * @param flg 設定値
     */
    fun setFcmEnable(flg: Boolean)
}