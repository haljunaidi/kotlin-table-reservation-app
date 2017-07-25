package pt.joaocruz.myreservationschallenge.ui.tables_screen

import pt.joaocruz.myreservationschallenge.data.DataManager
import pt.joaocruz.myreservationschallenge.model.TablesMap
import pt.joaocruz.myreservationschallenge.usecase.GetCustomerUseCase
import pt.joaocruz.myreservationschallenge.usecase.GetTablesMapUseCase
import javax.inject.Inject

/**
 * Created by jcruz on 17.07.17.
 */
class TablesPresenterImpl @Inject constructor (
        val tablesMapUseCase: GetTablesMapUseCase,
        val customerUseCase: GetCustomerUseCase,
        val dataManager: DataManager) : TablesPresenter {

    var view: TablesView?=null

    override fun registerView(view: TablesView) {
        this.view = view
    }

    override fun checkTables() {
        tablesMapUseCase.build()
                .doOnError {
                    // Service error. Should send something to the View
                }
                .subscribe {
                    dataManager.saveTablesMap(it)
                    view?.updateTables(it)
                }
    }

    override fun loadCustomerForID(id: Long) {
        customerUseCase
                .withID(id)
                .build()
                .doOnError {
                    // Service error. Should send something to the View
                }
                .subscribe {
                    if (it.customerFirstName != null) { // For simplicity.
                        view?.updateWithCustomer(it)
                        checkTables()
                    }
                }
    }

    override fun clickedSubmitWithSelectedPosition(position: Int, tablesMap: TablesMap) {
        if (tablesMap.tables!=null && position>-1) {
            if (!tablesMap.tables!![position]) {
                view?.showError("Table is occupied")
            } else {
                tablesMap.tables!![position] = false
                dataManager.saveTablesMap(tablesMap)
            }
        }
        view?.goBackToCustomers()
    }
}