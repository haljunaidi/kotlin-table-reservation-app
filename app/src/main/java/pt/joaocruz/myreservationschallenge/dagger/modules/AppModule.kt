package pt.joaocruz.myreservationschallenge.dagger.modules

import android.content.Context
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

    val appContext: Context

    constructor(appContext: Context) {
        this.appContext = appContext
    }

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
    internal fun providesLocalDataManager(sharedPrefManager: SharedPrefManager): LocalDataManager {
        return DBLocalDataManager(sharedPrefManager)
    }

    @Singleton
    @Provides
    internal fun providesNetworkServices(context: Context): NetworkServices {
        return StockAndroidNetworkServices(App.getInstance(context))
    }

    @Provides
    internal fun provideAppContext(): Context {
        return this.appContext
    }



}