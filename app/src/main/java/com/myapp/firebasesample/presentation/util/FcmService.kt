package com.myapp.firebasesample.presentation.util

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.myapp.firebasesample.MyApplication
import com.myapp.firebasesample.data.local.preferences.PreferenceManager

/**
 * FCM管理
 *
 */
class FcmService : FirebaseMessagingService() {

    private val preferenceManager: PreferenceManager = PreferenceManager(MyApplication.shared.applicationContext)
    private val noticeManager: NoticeManager = NoticeManager()

    /**
     * 新規トークン生成
     *
     * @param token トークン
     */
    override fun onNewToken(token: String) {
        Log.d("NotificationUseCase", "onNewToken()：Refreshed token: " + token)
    }

    /**
     * push通知受信
     *
     * @param remoteMessage 受信データ
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("NotificationUseCase","onMessageReceived")
        Log.d("NotificationUseCase","data = " + remoteMessage.data)
        Log.d("NotificationUseCase","notification = " + remoteMessage.notification)
        val channelId = remoteMessage.data["channelId"]
        val title = remoteMessage.data["title"] ?: ""
        val message = remoteMessage.data["message"] ?: ""

        if (channelId == NoticeManager.CHANNELS.FCM.id && preferenceManager.getBoolean(
                PreferenceManager.Key.BooleanKey.FCM)) {
            noticeManager.showFcm(this, title, message)
        }
    }
}

// FCM　DATAサンプル
//
// curl --header "Authorization: key=[プロジェクトの設定>Could Messaging>サーバーキー]" \
//     --header Content-Type:"application/json" \
//     https://fcm.googleapis.com/fcm/send \
//     -d "{
//     \"to\": \"[デバイストークン]\",
//     \"priority\":\"high\",
//     \"data\":
//          {\"title\": \"this is title\",
//           \"message\": \"this is body\",
//            \"channelId\": \"FireBasePushNotification\"
//           }
//      }"