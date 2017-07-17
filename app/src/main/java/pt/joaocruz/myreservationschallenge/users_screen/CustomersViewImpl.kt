package pt.joaocruz.myreservationschallenge.users_screen

import kotlinx.android.synthetic.main.activity_users_view_impl.*
import pt.joaocruz.myreservationschallenge.App
import pt.joaocruz.myreservationschallenge.model.Customer
import javax.inject.Inject

class CustomersViewImpl : CustomersView, android.support.v7.app.AppCompatActivity() {


    @Inject
    lateinit var presenter: CustomersPresenter


    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(pt.joaocruz.myreservationschallenge.R.layout.activity_users_view_impl)
        App.getInstance().appComponent.inject(this)
        presenter.registerView(this)
        presenter.checkCustomers()
    }


    override fun updateCustomerList(customers: List<Customer>) {
        list.adapter = CustomerListAdapter(customers, this)
    }
}
