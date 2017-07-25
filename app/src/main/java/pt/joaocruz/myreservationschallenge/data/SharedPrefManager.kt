package pt.joaocruz.myreservationschallenge.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import pt.joaocruz.myreservationschallenge.App
import pt.joaocruz.myreservationschallenge.model.TablesMap
import javax.inject.Inject

/**
 * Created by jcruz on 17.07.17.
 */
class SharedPrefManager @Inject constructor (val context: Context) {

    val SHARED_PREFERENCES = "myreservations_sp"


    private fun sharedPreferences(): SharedPreferences {
        return App.getInstance(context).getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }

    fun storeTablesMap(map: TablesMap) {
        val gson = Gson()
        var json = gson.toJson(map)
        sharedPreferences().edit().putString("tables_map", json).commit()
    }

    fun loadTablesMap(): TablesMap {
        val gson = Gson()
        var json = sharedPreferences().getString("tables_map", null)
        if (json==null) {
            return TablesMap()
        }
        else
            return gson.fromJson(json, TablesMap::class.java)
    }

}