package pt.joaocruz.myreservationschallenge.usecase

import io.reactivex.Observable
import pt.joaocruz.myreservationschallenge.data.DataManager
import pt.joaocruz.myreservationschallenge.model.Customer

/**
 * Created by jcruz on 17.07.17.
 */
class GetCustomerUseCase(dataManager: DataManager) : UseCase {

    var dm = dataManager
    var id: Long?=null

    fun withID(id: Long): GetCustomerUseCase {
        this.id = id
        return this
    }


    // For simplicity, lets assume an empty customer is an invalid customer
    override fun build(): Observable<Customer> {
        if (id!=null) {
            val customer = dm.getCustomerWithID(id!!)
            if (customer==null)
                return Observable.just(Customer())
            else
                return Observable.just(customer)
        } else
            return Observable.just(Customer())
    }


}