package pt.joaocruz.myreservationschallenge.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import pt.joaocruz.myreservationschallenge.data.DataManager
import pt.joaocruz.myreservationschallenge.model.Customer

/**
 * Created by jcruz on 17.07.17.
 */
open class GetCustomerUseCase(dataManager: DataManager, newThreadScheduler: Scheduler, ioScheduler: Scheduler) : UseCase {

    var dm = dataManager
    var id: Long?=null
    var newThreadScheduler = newThreadScheduler
    var ioScheduler = ioScheduler

    fun withID(id: Long): GetCustomerUseCase {
        this.id = id
        return this
    }


    // For simplicity, lets assume an empty customer is an invalid customer
    override fun build(): Observable<Customer> {
        if (id!=null && id!!>-1) {
            val customer = dm.getCustomerWithID(id!!)
            if (customer==null)
                return applySchedulers(Observable.just(Customer()))
            else
                return applySchedulers(Observable.just(customer))
        } else
            return applySchedulers(Observable.just(Customer()))
    }

    private fun applySchedulers(observable: Observable<Customer>) : Observable<Customer> {
        return observable
                .subscribeOn(newThreadScheduler)
                .observeOn(ioScheduler)
    }

}