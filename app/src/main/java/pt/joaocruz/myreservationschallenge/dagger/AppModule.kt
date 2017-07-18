package pt.joaocruz.myreservationschallenge.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import pt.joaocruz.myreservationschallenge.App
import pt.joaocruz.myreservationschallenge.data.*
import pt.joaocruz.myreservationschallenge.ui.tables_screen.TablesPresenter
import pt.joaocruz.myreservationschallenge.ui.tables_screen.TablesPresenterImpl
import pt.joaocruz.myreservationschallenge.usecase.GetCustomersUseCase
import pt.joaocruz.myreservationschallenge.ui.users_screen.CustomersPresenter
import pt.joaocruz.myreservationschallenge.ui.users_screen.CustomersPresenterImpl
import pt.joaocruz.myreservationschallenge.usecase.GetCustomerUseCase
import pt.joaocruz.myreservationschallenge.usecase.GetTablesMapUseCase
import javax.inject.Singleton

/**
 * Created by jcruz on 17.07.17.
 */
@Module
class AppModule {

    // Presenters

    @Provides
    internal fun provideUsersPresenter(customersUseCase: GetCustomersUseCase, dataManager: DataManager): CustomersPresenter {
        return CustomersPresenterImpl(customersUseCase, dataManager)
    }

    @Provides
    internal fun provideTablesPresenter(tablesMapUseCase: GetTablesMapUseCase, customerUseCase: GetCustomerUseCase, dataManager: DataManager): TablesPresenter {
        return TablesPresenterImpl(tablesMapUseCase, customerUseCase, dataManager)
    }


    // Managers

    @Singleton
    @Provides
    internal fun providesDataManager(onlineDataManager: OnlineDataManager, networkServices: NetworkServices ,localDataManager: LocalDataManager): DataManager {
        return DataManagerImpl(onlineDataManager, networkServices, localDataManager)
    }

    @Singleton
    @Provides
    internal fun providesOnlineDataManager(): OnlineDataManager {
        return RetrofitManager()
    }

    @Singleton
    @Provides
    internal fun providesLocalDataManager(): LocalDataManager {
        return DBLocalDataManager()
    }

    @Singleton
    @Provides
    internal fun providesNetworkServices(): NetworkServices {
        return StockAndroidNetworkServices(App.getInstance())
    }


    // Use cases

    @Provides
    internal fun provideCustomersUseCase(dataManager: DataManager) : GetCustomersUseCase {
        return GetCustomersUseCase(dataManager, Schedulers.newThread(), Schedulers.io())
    }

    @Provides
    internal fun provideCustomerUseCase(dataManager: DataManager) : GetCustomerUseCase {
        return GetCustomerUseCase(dataManager, Schedulers.newThread(), Schedulers.io())
    }

    @Provides
    internal fun provideTablesMapUseCase(dataManager: DataManager) : GetTablesMapUseCase {
        return GetTablesMapUseCase(dataManager, Schedulers.newThread(), Schedulers.io())
    }

}