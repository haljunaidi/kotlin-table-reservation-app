package pt.joaocruz.myreservationschallenge.dagger

import dagger.Component
import pt.joaocruz.myreservationschallenge.App
import pt.joaocruz.myreservationschallenge.users_screen.CustomersViewImpl
import javax.inject.Singleton

/**
 * Created by jcruz on 17.07.17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: App)
    fun inject(usersViewImpl: CustomersViewImpl)

}