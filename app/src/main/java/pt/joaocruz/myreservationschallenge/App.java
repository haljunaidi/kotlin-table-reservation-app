package pt.joaocruz.myreservationschallenge;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.orm.SugarContext;

import pt.joaocruz.myreservationschallenge.background.AlarmReceiver;
import pt.joaocruz.myreservationschallenge.dagger.*;

/**
 * Created by jcruz on 17.07.17.
 */

public class App extends Application {

    private static App instance;
    private AppComponent appComponent;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SugarContext.init(this);
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
        setupAlarmManager();
    }

    public static App getInstance() {
        return instance;
    }
    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void setupAlarmManager() {
        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        long millis = 10 * 60 * 1000;
        alarmMgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + millis, millis, alarmIntent);
    }

}
