package pt.joaocruz.myreservationschallenge.ui.users_screen

import pt.joaocruz.myreservationschallenge.model.Customer

/**
 * Created by jcruz on 17.07.17.
 */
interface CustomersPresenter {

    fun checkTables()
    fun registerView(view: CustomersView)
    fun checkCustomers()
    fun customerSelected(customer: Customer)
}