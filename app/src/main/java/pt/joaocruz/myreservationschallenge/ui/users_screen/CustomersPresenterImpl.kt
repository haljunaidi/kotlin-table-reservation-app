package pt.joaocruz.myreservationschallenge.ui.users_screen

import pt.joaocruz.myreservationschallenge.data.DataManager
import pt.joaocruz.myreservationschallenge.model.Customer
import pt.joaocruz.myreservationschallenge.usecase.GetCustomersUseCase

/**
 * Created by jcruz on 17.07.17.
 */
class CustomersPresenterImpl(customersUseCase: GetCustomersUseCase, dataManager: DataManager) : CustomersPresenter {


    var view : CustomersView?=null
    var useCase = customersUseCase
    var dm = dataManager

    override fun registerView(view: CustomersView) {
        this.view = view
    }

    override fun checkCustomers() {
        useCase.build()
                .doOnError {
                    // Service error. Should send something to the View
                }
                .subscribe {
                    dm.saveCustomerList(it)
                    view?.updateCustomerList(it)
                }
    }

    override fun customerSelected(customer: Customer) {
        if (customer==null || customer.id==null || customer.id!!<0)
            view?.showError("Invalid user")
        else
            view?.showTablesScreenForCustomer(customer)
    }
}