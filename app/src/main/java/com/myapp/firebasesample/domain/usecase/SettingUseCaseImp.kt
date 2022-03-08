package com.myapp.firebasesample.domain.usecase

import com.myapp.firebasesample.domain.repository.LocalSettingRepository

/**
 * FireBase Push通知サービス
 *
 */
class SettingUseCaseImp(private val localSettingRepositoryImp: LocalSettingRepository): SettingUseCase {

    override fun getFcmEnable() : Boolean {
        return localSettingRepositoryImp.getFcmData()
    }

    override fun setFcmEnable(flg: Boolean) {
        localSettingRepositoryImp.setFcmData(flg)
    }
}
