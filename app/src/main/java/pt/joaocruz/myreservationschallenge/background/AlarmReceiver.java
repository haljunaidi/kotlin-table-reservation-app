package pt.joaocruz.myreservationschallenge.background;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import javax.inject.Inject;

import pt.joaocruz.myreservationschallenge.App;
import pt.joaocruz.myreservationschallenge.data.DataManager;


/**
 * Created by jcruz on 17.07.17.
 */

public class AlarmReceiver extends WakefulBroadcastReceiver {

    @Inject
    DataManager dataManager;

    @Override
    public void onReceive(final Context context, Intent intent) {
        App.getInstance(context).getAppComponent().inject(this);
        dataManager.deleteAllReservations();
        Intent i = new Intent("pt.joaocruz.myreservationschallenge.dbupdate");
        i.putExtra("message", "BAM! NOTIFICATION");
        App.getInstance(context).sendBroadcast(i);
    }
}