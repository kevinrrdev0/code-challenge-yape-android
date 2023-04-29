package gsg.corp.driver_domain.model

data class RouteStateRequest(
    val idRoute: Int = 0,
    val idUser: Int = 0,
    val idStateTracking: Int = 0,
    val comment: String = "",
    val dateRescheduled: String = "",
    val photoOrder: String = "",
    val photoCollect: String = "",
)