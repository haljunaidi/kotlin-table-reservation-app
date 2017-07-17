package pt.joaocruz.myreservationschallenge.ui.users_screen

import pt.joaocruz.myreservationschallenge.inflate
import pt.joaocruz.myreservationschallenge.model.Customer

/**
 * Created by jcruz on 17.07.17.
 */
class CustomerListAdapter(customerList: List<pt.joaocruz.myreservationschallenge.model.Customer>, context: android.content.Context) :
        android.widget.ArrayAdapter<Customer>(context, pt.joaocruz.myreservationschallenge.R.layout.customer_list_item, customerList) {


    override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
        var view = convertView
        if (convertView==null) {
            view = parent.inflate(pt.joaocruz.myreservationschallenge.R.layout.customer_list_item)
        }
        val customer = getItem(position)

        view!!.findViewById<android.widget.TextView>(pt.joaocruz.myreservationschallenge.R.id.customerNameLbl).text = "${customer.customerFirstName} ${customer.customerLastName}"

        return view;
    }


}