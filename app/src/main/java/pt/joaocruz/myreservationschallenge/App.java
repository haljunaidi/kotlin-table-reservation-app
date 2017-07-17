package pt.joaocruz.myreservationschallenge;

import android.app.Application;

import pt.joaocruz.myreservationschallenge.dagger.AppComponent;
import pt.joaocruz.myreservationschallenge.dagger.AppModule;
import pt.joaocruz.myreservationschallenge.dagger.DaggerAppComponent;

/**
 * Created by jcruz on 17.07.17.
 */

public class App extends Application {

    private static App instance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public static App getInstance() {
        return instance;
    }
    public AppComponent getAppComponent() {
        return appComponent;
    }


}
