package com.myapp.firebasesample.presentation.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import androidx.core.app.NotificationCompat
import com.myapp.firebasesample.R

/**
 * 通知管理
 *
 */
class NoticeManager {

    companion object {
        /**
         * 通知ID
         */
        var notificationId = 0
    }

    /**
     * 通知の種類
     *
     * @property id チャンネルID
     * @property channelName チャンネル名
     * @property title 通知タイトル
     * @property explanation 説明
     */
    enum class CHANNELS(val id: String, val channelName: String, val title: String, val explanation: String) {
        FCM("FireBasePushNotification", "FCM　サンプル","FCM サンプル", "FCMのサンプル用チャンネルです"),
    }

    private lateinit var notificationManager: NotificationManager

    /**
     * FCM通知表示
     *
     * @param context Context
     * @param title タイトル
     * @param message メッセージ
     */
    fun showFcm(context: Context, title: String, message: String) {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(CHANNELS.FCM)
        showNotification(CHANNELS.FCM, title, message, context)
    }


    // 通し設定　＆　通知表示
    private fun showNotification(channel: CHANNELS, title: String,  message: String, context: Context) {
        val builder = NotificationCompat.Builder(context, channel.id)
            // アイコン
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            // タイトル
            .setContentTitle(title)
            // 本文テキスト
            .setContentText(message)
            // 通知の優先度
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        notificationManager.notify(notificationId, builder.build())
        notificationId ++
    }

    // チャンネル登録
    private fun createNotificationChannel(channel: CHANNELS) {
        // チャネル作成
        val notificationChannel = NotificationChannel(channel.id, channel.channelName, NotificationManager.IMPORTANCE_HIGH).also {
            it.enableLights(true)
            it.lightColor = Color.RED
            it.enableVibration(true)
            it.description = channel.explanation
        }
        notificationManager.createNotificationChannel(notificationChannel)
    }
}
