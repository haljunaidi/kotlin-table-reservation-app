package pt.joaocruz.myreservationschallenge.dagger.components

import dagger.Component
import pt.joaocruz.myreservationschallenge.background.AlarmReceiver
import pt.joaocruz.myreservationschallenge.dagger.modules.AppModule
import pt.joaocruz.myreservationschallenge.dagger.modules.PresentersModule
import pt.joaocruz.myreservationschallenge.dagger.modules.UseCaseModule
import pt.joaocruz.myreservationschallenge.ui.tables_screen.TablesViewImpl
import pt.joaocruz.myreservationschallenge.ui.users_screen.CustomersViewImpl
import javax.inject.Singleton

/**
 * Created by jcruz on 17.07.17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, PresentersModule::class, UseCaseModule::class))
interface AppComponent {

    fun inject(usersViewImpl: CustomersViewImpl)
    fun inject(tablesViewImpl: TablesViewImpl)
    fun inject(alarmReceiver: AlarmReceiver)

}