package pt.joaocruz.myreservationschallenge.usecase

import io.reactivex.Observable

/**
 * Created by jcruz on 17.07.17.
 */
interface UseCase {
    fun build(): Observable<*>
}