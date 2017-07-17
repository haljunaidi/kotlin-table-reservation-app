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

class GridAdapter(tablesMap: TablesMap): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var tablesMap: TablesMap = tablesMap
    var selectedItem = -1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is TableViewHolder) {
            val table = tablesMap.tables?.get(position)?:false
            holder.bindTable(table, position, (position==selectedItem))
        }
    }

    override fun getItemCount(): Int {
        return tablesMap.tables?.size?:0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflatedView = parent.inflate(R.layout.table_view)
        val holder = TableViewHolder(inflatedView, this)
        return holder
    }

    fun selectedItemAt(position: Int) {
        selectedItem = position
        notifyDataSetChanged()
    }

    fun setTables(tables: TablesMap) {
        this.tablesMap = tables
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