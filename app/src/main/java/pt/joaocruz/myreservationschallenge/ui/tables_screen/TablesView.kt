package pt.joaocruz.myreservationschallenge.ui.tables_screen

import pt.joaocruz.myreservationschallenge.model.Customer
import pt.joaocruz.myreservationschallenge.model.TablesMap

/**
 * Created by jcruz on 17.07.17.
 */
interface TablesView {

    fun updateTables(tablesMap: TablesMap)
    fun updateWithCustomer(customer: Customer)
    fun goBackToCustomers()
    fun showError(message: String)
}