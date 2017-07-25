package pt.joaocruz.myreservationschallenge.ui.users_screen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_users_view_impl.*
import pt.joaocruz.myreservationschallenge.App
import pt.joaocruz.myreservationschallenge.model.Customer
import pt.joaocruz.myreservationschallenge.ui.tables_screen.TablesViewImpl
import javax.inject.Inject

class CustomersViewImpl : CustomersView, AppCompatActivity() {


    @Inject
    lateinit var presenter: CustomersPresenter
    var adapter: CustomerListAdapter?=null


    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(pt.joaocruz.myreservationschallenge.R.layout.activity_users_view_impl)
        App.getInstance(this).appComponent.inject(this)
        presenter.registerView(this)
        presenter.checkTables()
        presenter.checkCustomers()
    }


    override fun updateCustomerList(customers: List<Customer>) {
        adapter = CustomerListAdapter(customers, this)
        list.adapter = adapter
        list.setOnItemClickListener { _, _, position, _ ->
            presenter.customerSelected(adapter!!.getItem(position))
        }
    }

    override fun showTablesScreenForCustomer(customer: Customer) {
        var intent = Intent(this, TablesViewImpl::class.java)
        intent.putExtra("customer_id", customer.id)
        startActivity(intent)
    }

    override fun showError(message: String) {
        // Show an alert/toast/whatever
    }
}
