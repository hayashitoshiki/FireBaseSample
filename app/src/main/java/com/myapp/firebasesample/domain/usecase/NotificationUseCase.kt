package com.myapp.firebasesample.domain.usecase

import com.google.firebase.messaging.FirebaseMessagingService
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.RemoteMessage
import com.myapp.firebasesample.R

/**
 * FireBase Push通知サービス
 *
 */
class NotificationUseCase : FirebaseMessagingService() {

    companion object {
        private const val CHANNEL_ID = "FireBasePushNotification"
    }

    /**
     * 新規トークン生成
     *
     * @param token トークン
     */
    override fun onNewToken(token: String) {
        Log.d("NotificationUseCase", "onNewToken()：Refreshed token: " + token);
        val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION_CODES.O <= Build.VERSION.SDK_INT) {
            var channel = nm.getNotificationChannel(CHANNEL_ID)
            if (channel == null) {
                channel = NotificationChannel(
                    CHANNEL_ID,
                    "プッシュ通知用のチャンネルです",
                    NotificationManager.IMPORTANCE_HIGH)
                nm.createNotificationChannel(channel)
            }
        }
    }

    /**
     * push通知受信
     *
     * @param remoteMessage 受信データ
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let { data ->
            val title = data.title
            val message = data.body
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .build()
            val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            nm.notify(0, notification)
        }
    }
}