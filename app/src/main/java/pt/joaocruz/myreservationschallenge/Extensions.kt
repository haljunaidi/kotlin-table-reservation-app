package pt.joaocruz.myreservationschallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by jcruz on 17.07.17.
 */


fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}