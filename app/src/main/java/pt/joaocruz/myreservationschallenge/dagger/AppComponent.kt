package pt.joaocruz.myreservationschallenge.dagger

import dagger.Component
import pt.joaocruz.myreservationschallenge.background.AlarmReceiver
import pt.joaocruz.myreservationschallenge.ui.tables_screen.TablesViewImpl
import pt.joaocruz.myreservationschallenge.ui.users_screen.CustomersViewImpl
import javax.inject.Singleton

/**
 * Created by jcruz on 17.07.17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(usersViewImpl: CustomersViewImpl)
    fun inject(tablesViewImpl: TablesViewImpl)
    fun inject(alarmReceiver: AlarmReceiver)
}