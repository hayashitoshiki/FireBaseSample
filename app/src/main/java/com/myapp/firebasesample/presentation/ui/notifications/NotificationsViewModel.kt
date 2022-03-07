package com.myapp.firebasesample.presentation.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myapp.firebasesample.domain.usecase.NotificationUseCase

class NotificationsViewModel(private val notificationUseCase: NotificationUseCase) : ViewModel() {

    private val _fcmText = MediatorLiveData<String>()
    val fcmText: LiveData<String> = _fcmText
    private val _fcmChecked: MutableLiveData<Boolean> = MutableLiveData(true)
    val isFcmChecked: LiveData<Boolean> = _fcmChecked

    init {
        _fcmChecked.value = notificationUseCase.getImportance()
        _fcmText.addSource(isFcmChecked) {
            _fcmText.value = if (it) { "ON" } else { "OFF" }
        }
    }

    // 切り替え
    fun changeFcm(flg: Boolean) {
        _fcmChecked.value = flg
        notificationUseCase.setEnable(flg)
    }

}