package pt.joaocruz.myreservationschallenge.data

import com.orm.SugarRecord
import com.orm.query.Select
import io.reactivex.Observable
import pt.joaocruz.myreservationschallenge.model.Customer
import pt.joaocruz.myreservationschallenge.model.TablesMap

/**
 * Created by jcruz on 17.07.17.
 */
class DBLocalDataManager : LocalDataManager {


    override fun storeCustomers(customers: List<Customer>) {
        SugarRecord.saveInTx(customers)
    }

    override fun getCustomers(): Observable<List<Customer>> {
        return Observable.just(Select.from(Customer::class.java).list())
    }

    override fun storeTablesMap(tablesMap: TablesMap) {
        val sp = SharedPrefManager()
        sp.storeTablesMap(tablesMap)
    }

    override fun getTablesMap(): Observable<TablesMap> {
        return Observable.just(SharedPrefManager().loadTablesMap())
    }

    override fun getCustomerWithID(id: Long): Customer? {
        val result = SugarRecord.findById(Customer::class.java, id)
        if (result==null)
            return Customer()
        else
            return result
    }
}