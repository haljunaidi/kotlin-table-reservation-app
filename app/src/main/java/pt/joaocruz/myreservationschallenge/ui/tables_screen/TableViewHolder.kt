package pt.joaocruz.myreservationschallenge.ui.tables_screen

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import pt.joaocruz.myreservationschallenge.R

/**
 * Created by jcruz on 17.07.17.
 */
class TableViewHolder(view: View, gridAdapter: GridAdapter): RecyclerView.ViewHolder(view) {

    val adapter = gridAdapter
    var table: Boolean?=null
    var textView = view.findViewById<TextView>(R.id.text)


    init {
        view.setOnClickListener {

        }
    }

    fun bindTable(bool: Boolean, position: Int) {
        table = bool
        textView.text = "$position"
        if (bool) {
            textView.setBackgroundColor(Color.parseColor("#00aa00"))
        } else {
            textView.setBackgroundColor(Color.parseColor("#aa0000"))
        }
    }


}