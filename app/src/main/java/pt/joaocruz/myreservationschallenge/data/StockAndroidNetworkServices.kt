package pt.joaocruz.myreservationschallenge.data

import android.content.Context
import android.net.ConnectivityManager



/**
 * Created by jcruz on 17.07.17.
 */
class StockAndroidNetworkServices(val context: Context) : NetworkServices {

    override fun hasInternet(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isAvailable && networkInfo.isConnectedOrConnecting
    }
}