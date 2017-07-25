package pt.joaocruz.myreservationschallenge.dagger

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by jcruz on 25.07.17.
 */

@Module
class UseCaseModule {

    @Provides
    @MainThreadScheduler
    internal fun provideIOScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @ThreadScheduler
    internal fun provideThreadScheduler(): Scheduler {
        return Schedulers.newThread()
    }

}