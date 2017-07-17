package pt.joaocruz.myreservationschallenge.dagger

import dagger.Module
import dagger.Provides
import pt.joaocruz.myreservationschallenge.data.DataManager
import pt.joaocruz.myreservationschallenge.data.DataManagerImpl
import pt.joaocruz.myreservationschallenge.data.OnlineDataManager
import pt.joaocruz.myreservationschallenge.data.RetrofitManager
import pt.joaocruz.myreservationschallenge.usecase.GetCustomersUseCase
import pt.joaocruz.myreservationschallenge.users_screen.CustomersPresenter
import pt.joaocruz.myreservationschallenge.users_screen.CustomersPresenterImpl
import javax.inject.Singleton

/**
 * Created by jcruz on 17.07.17.
 */
@Module
class AppModule {

    @Provides
    internal fun provideUsersPresenter(customersUseCase: GetCustomersUseCase): CustomersPresenter {
        return CustomersPresenterImpl(customersUseCase)
    }

    @Singleton
    @Provides
    internal fun providesDataManager(onlineDataManager: OnlineDataManager): DataManager {
        return DataManagerImpl(onlineDataManager)
    }

    @Singleton
    @Provides
    internal fun providesOnlineDataManager(): OnlineDataManager {
        return RetrofitManager()
    }

    @Provides
    internal fun provideCustomerUseCase(dataManager: DataManager) : GetCustomersUseCase {
        return GetCustomersUseCase(dataManager)
    }



}