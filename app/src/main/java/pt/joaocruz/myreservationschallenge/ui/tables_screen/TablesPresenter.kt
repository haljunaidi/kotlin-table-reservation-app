package pt.joaocruz.myreservationschallenge.ui.tables_screen


/**
 * Created by jcruz on 17.07.17.
 */
interface TablesPresenter {

    fun registerView(view: TablesView)
    fun loadCustomerForID(id: Long)
    fun checkTables()
}