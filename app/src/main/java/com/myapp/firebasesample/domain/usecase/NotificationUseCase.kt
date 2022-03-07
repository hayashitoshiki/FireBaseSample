package com.myapp.firebasesample.domain.usecase

import com.google.firebase.messaging.FirebaseMessagingService
import android.app.NotificationManager
import android.app.NotificationChannel
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage
import com.myapp.firebasesample.data.local.preferences.PreferenceManager
import com.myapp.firebasesample.MyApplication
import com.myapp.firebasesample.R

/**
 * FireBase Push通知サービス
 *
 */
class NotificationUseCase: FirebaseMessagingService() {

    private val preferenceManager: PreferenceManager = PreferenceManager(MyApplication.shared.applicationContext)

    companion object {
        const val CHANNEL_ID = "FireBasePushNotification"
    }

    /**
     * 新規トークン生成
     *
     * @param token トークン
     */
    override fun onNewToken(token: String) {
        Log.d("NotificationUseCase", "onNewToken()：Refreshed token: " + token)
        val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        var channel = nm.getNotificationChannel(CHANNEL_ID)
        if (channel == null) {
            channel = NotificationChannel(
                CHANNEL_ID,
                "プッシュ通知用のチャンネルです",
                NotificationManager.IMPORTANCE_HIGH)
            NotificationManager.IMPORTANCE_NONE
            nm.createNotificationChannel(channel)
        }
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
        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["message"]

        if (channelId == CHANNEL_ID && preferenceManager.getBoolean(PreferenceManager.Key.BooleanKey.FCM)) {
            val notification = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .build()
            val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            nm.notify(0, notification)
        }
    }

    fun getImportance() : Boolean {
        return preferenceManager.getBoolean(PreferenceManager.Key.BooleanKey.FCM)
    }

    fun setEnable(flg: Boolean) {
        preferenceManager.setBoolean(PreferenceManager.Key.BooleanKey.FCM, flg)
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