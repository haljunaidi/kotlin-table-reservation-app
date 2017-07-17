package pt.joaocruz.myreservationschallenge.data

import io.reactivex.Observable
import pt.joaocruz.myreservationschallenge.model.Customer
import pt.joaocruz.myreservationschallenge.model.TablesMap

/**
 * Created by jcruz on 17.07.17.
 */
class DataManagerImpl(onlineDataManager: OnlineDataManager, networkServices: NetworkServices, localDataManager: LocalDataManager) : DataManager {

    val remote = onlineDataManager
    val local = localDataManager
    val network = networkServices

    override fun getCustomersList(): Observable<List<Customer>> {
        if (network.hasInternet())
            return remote.getCustomers()
        else
            return local.getCustomers()
    }

    override fun getTablesMap(): Observable<TablesMap> {
        if (network.hasInternet())
            return remote
                    .getTablesMap()
                    .map { TablesMap().apply { tables = it } }
        else
            return local.getTablesMap()
    }

    override fun saveCustomerList(customers: List<Customer>) {
        local.storeCustomers(customers)
    }

    override fun saveTablesMap(tablesMap: TablesMap) {
        local.storeTablesMap(tablesMap)
    }

    override fun getCustomerWithID(id: Long): Customer? {
        return local.getCustomerWithID(id)
    }
}