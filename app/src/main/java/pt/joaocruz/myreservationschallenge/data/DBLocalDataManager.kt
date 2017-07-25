package pt.joaocruz.myreservationschallenge.data

import com.orm.SugarRecord
import com.orm.query.Select
import io.reactivex.Observable
import pt.joaocruz.myreservationschallenge.model.Customer
import pt.joaocruz.myreservationschallenge.model.TablesMap

/**
 * Created by jcruz on 17.07.17.
 */
class DBLocalDataManager (val sharedPrefManager: SharedPrefManager): LocalDataManager {


    override fun storeCustomers(customers: List<Customer>) {
        SugarRecord.saveInTx(customers)
    }

    override fun getCustomers(): Observable<ArrayList<Customer>> {
        val list = ArrayList<Customer>()
        list.addAll(Select.from(Customer::class.java).list())
        return Observable.just(list)
    }

    override fun storeTablesMap(tablesMap: TablesMap) {
        sharedPrefManager.storeTablesMap(tablesMap)
    }

    override fun getTablesMap(): Observable<TablesMap> {
        return Observable.just(sharedPrefManager.loadTablesMap())
    }

    override fun getCustomerWithID(id: Long): Customer? {
        val result = SugarRecord.findById(Customer::class.java, id)
        if (result==null)
            return Customer()
        else
            return result
    }

    override fun deleteAllReservations() {
        var tablesMap = sharedPrefManager.loadTablesMap()
        if (tablesMap.tables!=null) {
            for (i in 0..(tablesMap.tables!!.size-1)) {
                tablesMap.tables!![i] = true
            }
            storeTablesMap(tablesMap)
        }
    }
}