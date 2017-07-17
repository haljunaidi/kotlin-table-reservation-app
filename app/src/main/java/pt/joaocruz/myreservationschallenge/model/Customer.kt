package pt.joaocruz.myreservationschallenge.model

import com.orm.SugarRecord
import com.orm.dsl.Table


/**
 * Created by jcruz on 17.07.17.
 */


@Table
class Customer {

    var id: Long?=null
    var customerFirstName: String?=null
    var customerLastName: String?=null


}