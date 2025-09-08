package info.nightscout.androidaps.plugins.pump.carelevo.data.mapper

import info.nightscout.androidaps.plugins.pump.carelevo.data.model.entities.CarelevoAlarmInfoEntity
import info.nightscout.androidaps.plugins.pump.carelevo.domain.model.alarm.CarelevoAlarmInfo

/*
 * Project   : CareLevoAAPS
 * File      : CarelevoAlarmInfoMapper
 * Package   : info.nightscout.androidaps.plugins.pump.carelevo.data.mapper
 * Created   : 2025. 9. 4. PM 5:06
 */


fun CarelevoAlarmInfoEntity.transformToDomainModel(): CarelevoAlarmInfo =
    CarelevoAlarmInfo(
        alarmId = alarmId,
        alarmType = alarmType,
        cause = cause,
        createdAt = createdAt,
        updatedAt = updatedAt,
        acknowledged = acknowledged
    )

fun CarelevoAlarmInfo.transformToEntity(): CarelevoAlarmInfoEntity =
    CarelevoAlarmInfoEntity(
        alarmId = alarmId,
        alarmType = alarmType,
        cause = cause,
        createdAt = createdAt,
        updatedAt = updatedAt,
        acknowledged = acknowledged
    )