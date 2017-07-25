package pt.joaocruz.myreservationschallenge.ui.users_screen

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import pt.joaocruz.myreservationschallenge.R
import pt.joaocruz.myreservationschallenge.inflate
import pt.joaocruz.myreservationschallenge.model.Customer

/**
 * Created by jcruz on 17.07.17.
 */
class CustomerListAdapter(customerList: List<Customer>, context: android.content.Context) :
        ArrayAdapter<Customer>(context, R.layout.customer_list_item, customerList) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (convertView==null) {
            view = parent.inflate(R.layout.customer_list_item)
        }
        val customer = getItem(position)

        view!!.findViewById<TextView>(R.id.customerNameLbl).text = "${customer.customerFirstName} ${customer.customerLastName}"

        return view;
    }


}