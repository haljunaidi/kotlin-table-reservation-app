package pt.joaocruz.myreservationschallenge.data

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import pt.joaocruz.myreservationschallenge.model.Customer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by jcruz on 17.07.17.
 */
class RetrofitManager : OnlineDataManager {

    interface ReservationsAPI {

        @GET("customer-list.json")
        fun getCustomers() : Observable<List<Customer>>

    }

    var api: ReservationsAPI
    var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
                .baseUrl("https://s3-eu-west-1.amazonaws.com/quandoo-assessment/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        api = retrofit.create(ReservationsAPI::class.java)
    }


    override fun getCustomers(): Observable<List<Customer>> {
        return api.getCustomers()
    }



}