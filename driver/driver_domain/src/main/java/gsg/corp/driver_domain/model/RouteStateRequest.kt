package gsg.corp.driver_domain.model

import android.net.Uri

data class RouteStateRequest(
    val idRoute: Int = 0,
    val id_state_tracking: Int = 0,
    val comment: String = "",
    val dateRescheduled: String = "",
    val photoOrder: String = "",
    val photoCollect: String = "",
)