package pt.joaocruz.myreservationschallenge.data

import io.reactivex.Observable
import pt.joaocruz.myreservationschallenge.model.Customer
import pt.joaocruz.myreservationschallenge.model.TablesMap

/**
 * Created by jcruz on 17.07.17.
 */
interface LocalDataManager {

    fun storeCustomers(customers: List<Customer>)
    fun storeTablesMap(tablesMap: TablesMap)
    fun getCustomers(): Observable<ArrayList<Customer>>
    fun getTablesMap(): Observable<TablesMap>
    fun getCustomerWithID(id: Long): Customer?
    fun deleteAllReservations()
}