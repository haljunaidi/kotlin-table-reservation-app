package pt.joaocruz.myreservationschallenge.ui.tables_screen

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_tables_layout.*
import org.jetbrains.anko.dip
import pt.joaocruz.myreservationschallenge.App
import pt.joaocruz.myreservationschallenge.model.Customer
import pt.joaocruz.myreservationschallenge.model.TablesMap
import javax.inject.Inject

/**
 * Created by jcruz on 17.07.17.
 */
class TablesViewImpl : TablesView, AppCompatActivity() {

    @Inject
    lateinit var presenter: TablesPresenter
    var adapter: GridAdapter?=null

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(pt.joaocruz.myreservationschallenge.R.layout.activity_tables_layout)
        App.getInstance().appComponent.inject(this)

        var customer = intent.getLongExtra("customer_id", -1)
        setupRecyclerView()
        presenter.registerView(this)
        presenter.loadCustomerForID(customer)
    }



    fun setupRecyclerView() {
        adapter = GridAdapter()
        recyclerView.layoutManager = GridLayoutManager(this, 5)
        recyclerView.addItemDecoration(GridAdapter.SpacesItemDecoration(dip(2.5F)))
        recyclerView.adapter = adapter
    }

    override fun updateWithCustomer(customer: Customer) {
        title = "Table for ${customer.customerFirstName} ${customer.customerLastName}"
    }

    override fun updateTables(tablesMap: TablesMap) {
        adapter?.setTables(tablesMap)
    }
}