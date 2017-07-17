package pt.joaocruz.myreservationschallenge.ui.tables_screen

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import pt.joaocruz.myreservationschallenge.R
import pt.joaocruz.myreservationschallenge.inflate
import pt.joaocruz.myreservationschallenge.model.TablesMap

/**
 * Created by jcruz on 17.07.17.
 */

class GridAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val tables = ArrayList<Boolean>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is TableViewHolder) {
            holder.bindTable(tables[position], position)
        }
    }

    override fun getItemCount(): Int {
        return tables.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflatedView = parent.inflate(R.layout.table_view)
        val holder = TableViewHolder(inflatedView, this)
        return holder
    }

    fun setTables(tables: TablesMap) {
        this.tables.clear()
        this.tables.addAll(tables.tables?:ArrayList())
        notifyDataSetChanged()
    }

    class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.left = space
            outRect.right = space
            outRect.bottom = space
            outRect.top = space
        }
    }


}