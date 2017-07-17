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
    var tableAvailable: Boolean=false
    var textView = view.findViewById<TextView>(R.id.text)
    var pos: Int=-1


    init {
        view.setOnClickListener {
            if (tableAvailable)
                adapter.selectedItemAt(pos)
        }
    }

    fun bindTable(bool: Boolean, position: Int, isSelectedPosition: Boolean) {
        tableAvailable = bool
        pos = position
        textView.text = "$position"

        if (isSelectedPosition)
            textView.setBackgroundColor(Color.BLACK)
        else if (tableAvailable) {
            textView.setBackgroundColor(Color.parseColor("#00aa00"))
        } else {
            textView.setBackgroundColor(Color.parseColor("#aa0000"))
        }
    }


}