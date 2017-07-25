package pt.joaocruz.myreservationschallenge.ui.tables_screen

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_tables_layout.*
import org.jetbrains.anko.dip
import pt.joaocruz.myreservationschallenge.App
import pt.joaocruz.myreservationschallenge.R
import pt.joaocruz.myreservationschallenge.model.Customer
import pt.joaocruz.myreservationschallenge.model.TablesMap
import javax.inject.Inject
import android.support.v4.app.NotificationCompat.getExtras
import android.os.Bundle
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import org.jetbrains.anko.toast


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
        App.getInstance(this).appComponent.inject(this)
        setupRecyclerView()
        var customer = intent.getLongExtra("customer_id", -1)
        presenter.registerView(this)
        presenter.loadCustomerForID(customer)
        registerReceiver(broadcastReceiver, IntentFilter("pt.joaocruz.myreservationschallenge.dbupdate"));

    }

    fun setupRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(this, 5)
        recyclerView.addItemDecoration(GridAdapter.SpacesItemDecoration(dip(2.5F)))
    }

    fun refreshRecyclerView(tablesMap: TablesMap) {
        adapter = GridAdapter(tablesMap)
        adapter!!.setTables(tablesMap)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

    // View Interface

    override fun updateWithCustomer(customer: Customer) {
        title = "Table for ${customer.customerFirstName} ${customer.customerLastName}"
    }

    override fun updateTables(tablesMap: TablesMap) {
        refreshRecyclerView(tablesMap)
    }

    override fun goBackToCustomers() {
        finish()
    }

    override fun showError(message: String) {
        // Show an alert/toast/whatever
    }

    // Menu

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.tables_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_confirm -> {
                val selectedPos = adapter?.selectedItem?:-1
                if(adapter?.tablesMap !=null)
                    presenter.clickedSubmitWithSelectedPosition(selectedPos, adapter?.tablesMap!!)
                return true
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
    }

    // Broadcast

    var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == "pt.joaocruz.myreservationschallenge.dbupdate") {
                presenter.checkTables()
            }


        }
    }
}