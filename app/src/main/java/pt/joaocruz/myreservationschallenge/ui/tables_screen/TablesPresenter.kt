package pt.joaocruz.myreservationschallenge.ui.tables_screen

import pt.joaocruz.myreservationschallenge.model.TablesMap
import java.text.FieldPosition


/**
 * Created by jcruz on 17.07.17.
 */
interface TablesPresenter {

    fun registerView(view: TablesView)
    fun loadCustomerForID(id: Long)
    fun checkTables()
    fun clickedSubmitWithSelectedPosition(position: Int, tablesMap: TablesMap)
}