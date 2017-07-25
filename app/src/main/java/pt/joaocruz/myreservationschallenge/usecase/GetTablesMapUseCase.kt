package pt.joaocruz.myreservationschallenge.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import pt.joaocruz.myreservationschallenge.dagger.qualifiers.MainThreadScheduler
import pt.joaocruz.myreservationschallenge.dagger.qualifiers.ThreadScheduler
import pt.joaocruz.myreservationschallenge.data.DataManager
import pt.joaocruz.myreservationschallenge.model.TablesMap
import javax.inject.Inject

/**
 * Created by jcruz on 17.07.17.
 */
open class GetTablesMapUseCase @Inject constructor (dataManager: DataManager, @ThreadScheduler newThreadScheduler: Scheduler, @MainThreadScheduler ioScheduler: Scheduler) : UseCase {

    var dm = dataManager
    var newThreadScheduler = newThreadScheduler
    var ioScheduler = ioScheduler

    override fun build(): Observable<TablesMap> {
        return dm.getTablesMap()
                .subscribeOn(newThreadScheduler)
                .observeOn(ioScheduler)
    }


}