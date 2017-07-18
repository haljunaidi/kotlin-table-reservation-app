package pt.joaocruz.myreservationschallenge.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import pt.joaocruz.myreservationschallenge.data.DataManager
import pt.joaocruz.myreservationschallenge.model.TablesMap

/**
 * Created by jcruz on 17.07.17.
 */
open class GetTablesMapUseCase(dataManager: DataManager, newThreadScheduler: Scheduler, ioScheduler: Scheduler) : UseCase {

    var dm = dataManager
    var newThreadScheduler = newThreadScheduler
    var ioScheduler = ioScheduler

    override fun build(): Observable<TablesMap> {
        return dm.getTablesMap()
                .subscribeOn(newThreadScheduler)
                .observeOn(ioScheduler)
    }


}