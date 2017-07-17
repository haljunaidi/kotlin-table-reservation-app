package pt.joaocruz.myreservationschallenge.users_screen

import pt.joaocruz.myreservationschallenge.model.Customer

/**
 * Created by jcruz on 17.07.17.
 */
interface CustomersView {

    fun updateCustomerList(customers: List<Customer>)

}