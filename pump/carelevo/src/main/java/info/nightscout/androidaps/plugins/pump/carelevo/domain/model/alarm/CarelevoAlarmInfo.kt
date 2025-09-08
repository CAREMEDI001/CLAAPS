package info.nightscout.androidaps.plugins.pump.carelevo.domain.model.alarm

import info.nightscout.androidaps.plugins.pump.carelevo.domain.type.AlarmCause

/*
 * Project   : CareLevoAAPS
 * File      : CarelevoAlarmInfo
 * Package   : info.nightscout.androidaps.plugins.pump.carelevo.domain.model.alarm
 * Created   : 2025. 9. 4. PM 5:04
 */
data class CarelevoAlarmInfo(
    val alarmId: String,
    val alarmType: Int,
    val cause: AlarmCause,
    val createdAt: String,
    val updatedAt: String,
    val acknowledged: Boolean
)
