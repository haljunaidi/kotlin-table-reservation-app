package pt.joaocruz.myreservationschallenge.data

import io.reactivex.Observable
import pt.joaocruz.myreservationschallenge.model.Customer
import pt.joaocruz.myreservationschallenge.model.TablesMap

/**
 * Created by jcruz on 17.07.17.
 */
interface DataManager {

    fun getCustomersList() : Observable<List<Customer>>
    fun getTablesMap(): Observable<TablesMap>
    fun saveCustomerList(customers: List<Customer>)
    fun saveTablesMap(tablesMap: TablesMap)
    fun getCustomerWithID(id: Long): Customer?

}