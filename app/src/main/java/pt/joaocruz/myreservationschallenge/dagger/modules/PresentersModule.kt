package pt.joaocruz.myreservationschallenge.dagger.modules

import dagger.Module
import dagger.Provides

/**
 * Created by jcruz on 25.07.17.
 */

@Module
class PresentersModule {

    @Provides
    internal fun provideUsersPresenter(customersPresenterImpl: pt.joaocruz.myreservationschallenge.ui.users_screen.CustomersPresenterImpl): pt.joaocruz.myreservationschallenge.ui.users_screen.CustomersPresenter {
        return customersPresenterImpl
    }

    @Provides
    internal fun provideTablesPresenter(tablesPresenterImpl: pt.joaocruz.myreservationschallenge.ui.tables_screen.TablesPresenterImpl): pt.joaocruz.myreservationschallenge.ui.tables_screen.TablesPresenter {
        return tablesPresenterImpl
    }

}