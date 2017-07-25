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
open class GetTablesMapUseCase @Inject constructor (
        val dataManager: DataManager,

        @ThreadScheduler
        val newThreadScheduler: Scheduler,

        @MainThreadScheduler val ioScheduler: Scheduler

) : UseCase {

    override fun build(): Observable<TablesMap> {
        return dataManager.getTablesMap()
                .subscribeOn(newThreadScheduler)
                .observeOn(ioScheduler)
    }


}