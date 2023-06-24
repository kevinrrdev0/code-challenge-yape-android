package gsg.corp.driver_data.remote.request

import android.net.Uri
import gsg.corp.driver_domain.model.RoutePayment

class MetadataRequest(
    val id_route: Int = 0,
    val id_user: Int = 0,
    val id_state_tracking: Int = 0,
    val comment: String = "",
    val date_rescheduled: String = "",
    val RoutePayments : List<RoutePayment> = listOf()
)