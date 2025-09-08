package info.nightscout.androidaps.plugins.pump.carelevo.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import info.nightscout.androidaps.plugins.pump.carelevo.domain.model.alarm.CarelevoAlarmInfo
import info.nightscout.androidaps.plugins.pump.carelevo.domain.usecase.alarm.CarelevoAlarmInfoUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

/*
 * Project   : CareLevoAAPS
 * File      : CarelevoAlarmNotifier
 * Package   : info.nightscout.androidaps.plugins.pump.carelevo.common
 * Created   : 2025. 9. 5. AM 9:48
 */
@Singleton
class CarelevoAlarmNotifier @Inject constructor(
    private val context: Context,
    private val alarmUseCase: CarelevoAlarmInfoUseCase
) {

    private val disposables = CompositeDisposable()
    private val channelId = "carelevo_alarm_channel"

    fun startObserving() {
        createNotificationChannel()

        disposables.add(
            alarmUseCase.getAlarmsOnce(true)
                .toObservable()
                .concatWith(alarmUseCase.observeAlarms())
                .subscribe(
                    { optionalList ->
                        val alarms = optionalList.orElse(emptyList())
                        alarms.forEach { alarm ->
                            Log.d("CarelevoAlarmNotifier", "[CarelevoAlarmNotifier::getAlarmsOnce] alarm : $alarm")
                            if (!alarm.acknowledged) {
                                showNotification(alarm)
                                /*Intent(context, CarelevoActivity::class.java).apply {
                                    putExtra("screenType", CarelevoScreenType.CONNECTION_FLOW_START)
                                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    context.startActivity(this)
                                }*/
                            }
                        }
                    },
                    { e ->
                        Log.e("CarelevoAlarmNotifier", "Error loading alarms once", e)
                    }
                )
        )
    }

    fun stopObserving() {
        disposables.clear()
    }

    private fun showNotification(alarm: CarelevoAlarmInfo) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(app.aaps.core.ui.R.drawable.notif_icon) // 적절한 아이콘
            .setContentTitle("케어레보 알람 발생")
            .setContentText("알람 종류: ${alarm.alarmType}, 원인: ${alarm.cause}")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        // 알람 ID 별로 notificationId 를 다르게 주면 여러 알람이 동시에 표시 가능
        notificationManager.notify(alarm.alarmId.hashCode(), notification)
    }

    private fun createNotificationChannel() {
        val name = "Carelevo Alarm Channel"
        val descriptionText = "케어레보 패치 알람 알림 채널"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}