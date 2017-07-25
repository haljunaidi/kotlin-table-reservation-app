package pt.joaocruz.myreservationschallenge.ui.users_screen

import pt.joaocruz.myreservationschallenge.data.DataManager
import pt.joaocruz.myreservationschallenge.model.Customer
import pt.joaocruz.myreservationschallenge.usecase.GetCustomersUseCase
import pt.joaocruz.myreservationschallenge.usecase.GetTablesMapUseCase
import javax.inject.Inject

/**
 * Created by jcruz on 17.07.17.
 */
class CustomersPresenterImpl @Inject constructor(
        val customersUseCase: GetCustomersUseCase,
        val tablesMapUseCase: GetTablesMapUseCase,
        val dataManager: DataManager) : CustomersPresenter {


    var view : CustomersView?=null
    var selectedCustomer: Customer? = null

    override fun registerView(view: CustomersView) {
        this.view = view
    }

    override fun checkCustomers() {
        customersUseCase.build()
                .doOnError {
                    // Service error. Should send something to the View
                }
                .subscribe {
                    dataManager.saveCustomerList(it)
                    view?.updateCustomerList(it)
                }
    }

    override fun customerSelected(customer: Customer) {
        selectedCustomer = customer
        if (customer==null || customer.id==null || customer.id!!<0)
            view?.showError("Invalid user")
        else
            view?.showTablesScreenForCustomer(customer)
    }

    override fun checkTables() {
        tablesMapUseCase
                .build()
                .doOnError {
                    // Service error. Notify the View
                }
                .subscribe {
                    dataManager.saveTablesMap(it)
                }

    }
}