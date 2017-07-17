package pt.joaocruz.myreservationschallenge.data

import io.reactivex.Observable
import pt.joaocruz.myreservationschallenge.model.Customer

/**
 * Created by jcruz on 17.07.17.
 */
class DataManagerImpl(onlineDataManager: OnlineDataManager) : DataManager {

    val services = onlineDataManager

    override fun getCustomersList(): Observable<List<Customer>> {
        return services.getCustomers()
    }


}