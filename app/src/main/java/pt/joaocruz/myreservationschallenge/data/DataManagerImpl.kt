package pt.joaocruz.myreservationschallenge.data

import io.reactivex.Observable
import pt.joaocruz.myreservationschallenge.model.Customer
import pt.joaocruz.myreservationschallenge.model.TablesMap
import javax.inject.Inject

/**
 * Created by jcruz on 17.07.17.
 */
class DataManagerImpl @Inject constructor(
        val onlineDataManager: OnlineDataManager,
        val networkServices: NetworkServices,
        val localDataManager: LocalDataManager) : DataManager {

    override fun getCustomersList(): Observable<ArrayList<Customer>> {
        if (networkServices.hasInternet())
            return onlineDataManager.getCustomers()
        else
            return localDataManager.getCustomers()
    }

    override fun getTablesMap(): Observable<TablesMap> {
        if (networkServices.hasInternet())
            return onlineDataManager
                    .getTablesMap()
                    .map { TablesMap().apply { tables = it } }
        else
            return localDataManager.getTablesMap()
    }

    override fun saveCustomerList(customers: List<Customer>) {
        localDataManager.storeCustomers(customers)
    }

    override fun saveTablesMap(tablesMap: TablesMap) {
        localDataManager.storeTablesMap(tablesMap)
    }

    override fun getCustomerWithID(id: Long): Customer? {
        return localDataManager.getCustomerWithID(id)
    }

    override fun deleteAllReservations() {
        localDataManager.deleteAllReservations()
    }
}