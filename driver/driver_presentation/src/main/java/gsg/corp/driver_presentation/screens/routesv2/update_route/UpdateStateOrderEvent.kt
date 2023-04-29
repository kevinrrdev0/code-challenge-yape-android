package gsg.corp.driver_presentation.screens.routesv2.update_route

sealed class UpdateStateOrderEvent{
    data class OnStateSelected(val id: Int,val name:String): UpdateStateOrderEvent()
    data class OnDateRescheduledEnter(val dateRescheduled: String): UpdateStateOrderEvent()
    data class OnCommentEnter(val comment: String): UpdateStateOrderEvent()
    data class OnTakePhotoOrder(val pathPhotoOrder: String): UpdateStateOrderEvent()
    data class OnTakePhotoCollect(val pathPhotoCollect:String): UpdateStateOrderEvent()
    object OnUpdateOrder : UpdateStateOrderEvent()
}
