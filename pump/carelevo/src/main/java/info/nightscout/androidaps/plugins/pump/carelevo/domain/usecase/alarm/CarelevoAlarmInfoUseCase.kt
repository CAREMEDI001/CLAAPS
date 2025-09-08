package info.nightscout.androidaps.plugins.pump.carelevo.domain.usecase.alarm

import info.nightscout.androidaps.plugins.pump.carelevo.domain.model.alarm.CarelevoAlarmInfo
import info.nightscout.androidaps.plugins.pump.carelevo.domain.repository.CarelevoAlarmInfoRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.Optional
import javax.inject.Inject

/*
 * Project   : CareLevoAAPS
 * File      : CarelevoAlarmInfoUseCase
 * Package   : info.nightscout.androidaps.plugins.pump.carelevo.domain.usecase.alarm
 * Created   : 2025. 9. 4. PM 6:32
 */
class CarelevoAlarmInfoUseCase @Inject constructor(
    private val repository: CarelevoAlarmInfoRepository
) {

    fun observeAlarms(): Observable<Optional<List<CarelevoAlarmInfo>>> =
        repository.observeAlarms()

    fun getAlarmsOnce(includeUnacknowledged: Boolean = true): Single<Optional<List<CarelevoAlarmInfo>>> = repository.getAlarmsOnce(includeUnacknowledged)

    fun upsertAlarm(alarm: CarelevoAlarmInfo): Completable =
        repository.upsertAlarm(alarm)

    fun acknowledgeAlarm(alarmId: String, now: String): Completable =
        repository.markAcknowledged(alarmId, acknowledged = true, updatedAt = now)

    fun clearAlarms(): Completable =
        repository.clearAlarms()
}