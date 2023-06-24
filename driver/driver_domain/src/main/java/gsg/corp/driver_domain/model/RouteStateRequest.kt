package gsg.corp.driver_domain.model

data class RouteStateRequest(
    val idRoute: Int = 0,
    val idUser: Int = 0,
    val idStateTracking: Int = 0,
    val comment: String = "",
    val dateRescheduled: String = "",
    val photoStatus: String = "",
    val otherPhotoStatus: String = "",
    val routesPayments:List<RoutePayment> = listOf()
)
//
//const body_m_state_route = {
//    id_route: $("#id_m_state_route").val(),
//    id_state_tracking: $("#id_state_tracking").val(),
//    id_user: $("#id_user").val(),
//    comment: $("#comment_m_state_route_edit").val(),
//    date_rescheduled:
//    $("#id_state_tracking").val() !== "11"
//    ? ""
//    : $("#date_rescheduled_m_state_route_edit").val(),
//    RoutePayments: disallowedStates.includes($("#id_state_tracking").val())
//    ? routePayments
//    : [],
//    id_route_state_ids
//};