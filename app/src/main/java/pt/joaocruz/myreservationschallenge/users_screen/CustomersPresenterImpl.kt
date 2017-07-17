package pt.joaocruz.myreservationschallenge.users_screen

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pt.joaocruz.myreservationschallenge.usecase.GetCustomersUseCase

/**
 * Created by jcruz on 17.07.17.
 */
class CustomersPresenterImpl(customersUseCase: GetCustomersUseCase) : CustomersPresenter {


    var view : CustomersView?=null
    var useCase = customersUseCase


    override fun registerView(view: CustomersView) {
        this.view = view
    }

    override fun checkCustomers() {
        useCase.build()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    // Service error. Should send something to the View
                }
                .subscribe {
                    view?.updateCustomerList(it)
                }
    }


}