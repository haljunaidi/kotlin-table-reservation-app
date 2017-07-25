package pt.joaocruz.myreservationschallenge.dagger.modules

import dagger.Module
import dagger.Provides
import pt.joaocruz.myreservationschallenge.dagger.qualifiers.MainThreadScheduler
import pt.joaocruz.myreservationschallenge.dagger.qualifiers.ThreadScheduler

/**
 * Created by jcruz on 25.07.17.
 */

@Module
class UseCaseModule {

    @Provides
    @MainThreadScheduler
    internal fun provideIOScheduler(): io.reactivex.Scheduler {
        return io.reactivex.android.schedulers.AndroidSchedulers.mainThread()
    }

    @Provides
    @ThreadScheduler
    internal fun provideThreadScheduler(): io.reactivex.Scheduler {
        return io.reactivex.schedulers.Schedulers.newThread()
    }

}