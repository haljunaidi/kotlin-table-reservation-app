package pt.joaocruz.myreservationschallenge.usecase

import io.reactivex.Observable
import pt.joaocruz.myreservationschallenge.data.DataManager
import pt.joaocruz.myreservationschallenge.model.TablesMap

/**
 * Created by jcruz on 17.07.17.
 */
class GetTablesMapUseCase(dataManager: DataManager) : UseCase {

    var dm = dataManager

    override fun build(): Observable<TablesMap> {
        return dm.getTablesMap()
    }


}