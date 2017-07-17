package pt.joaocruz.myreservationschallenge;

import android.app.Application;

import com.orm.SugarContext;

import pt.joaocruz.myreservationschallenge.dagger.*;

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
        SugarContext.init(this);
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
