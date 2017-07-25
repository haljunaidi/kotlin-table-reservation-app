package pt.joaocruz.myreservationschallenge.dagger

import dagger.Module
import dagger.Provides
import pt.joaocruz.myreservationschallenge.App
import pt.joaocruz.myreservationschallenge.data.*
import javax.inject.Singleton

/**
 * Created by jcruz on 17.07.17.
 */
@Module
class AppModule {

    @Singleton
    @Provides
    internal fun providesDataManager(dataManagerImpl: DataManagerImpl): DataManager {
        return dataManagerImpl
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


}