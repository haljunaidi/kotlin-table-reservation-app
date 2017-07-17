package pt.joaocruz.myreservationschallenge.usecase

import io.reactivex.Observable
import pt.joaocruz.myreservationschallenge.data.DataManager
import pt.joaocruz.myreservationschallenge.model.Customer

/**
 * Created by jcruz on 17.07.17.
 */
class GetCustomersUseCase(dataManager: DataManager) : UseCase {

    var dm = dataManager

    override fun build(): Observable<List<Customer>> {
        return dm.getCustomersList()
    }


}