package pt.joaocruz.myreservationschallenge.dagger.qualifiers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;


/**
 * Created by jcruz on 25.07.17.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface MainThreadScheduler {
}
