package pt.joaocruz.myreservationschallenge.data

import android.content.Context
import android.net.ConnectivityManager



/**
 * Created by jcruz on 17.07.17.
 */
class StockAndroidNetworkServices(context: Context) : NetworkServices {

    var mContext = context

    override fun hasInternet(): Boolean {
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isAvailable && networkInfo.isConnectedOrConnecting
    }
}