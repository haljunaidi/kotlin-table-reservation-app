package pt.joaocruz.myreservationschallenge.dagger

import dagger.Module
import dagger.Provides
import pt.joaocruz.myreservationschallenge.ui.tables_screen.TablesPresenter
import pt.joaocruz.myreservationschallenge.ui.tables_screen.TablesPresenterImpl
import pt.joaocruz.myreservationschallenge.ui.users_screen.CustomersPresenter
import pt.joaocruz.myreservationschallenge.ui.users_screen.CustomersPresenterImpl

/**
 * Created by jcruz on 25.07.17.
 */

@Module
class PresentersModule {

    @Provides
    internal fun provideUsersPresenter(customersPresenterImpl: CustomersPresenterImpl): CustomersPresenter {
        return customersPresenterImpl
    }

    @Provides
    internal fun provideTablesPresenter(tablesPresenterImpl: TablesPresenterImpl): TablesPresenter {
        return tablesPresenterImpl
    }

}