package gsg.corp.driver_presentation.screens.routesv2.routes_detail

import android.net.Uri
import gsg.corp.driver_presentation.screens.routesv2.RouteEvent

sealed class RouteDetailEvent{
    data class OnStateSelected(val id: Int,val name:String): RouteDetailEvent()
    data class OnDateRescheduledEnter(val dateRescheduled: String): RouteDetailEvent()
    data class OnCommentEnter(val comment: String): RouteDetailEvent()
    data class OnTakePhotoOrder(val pathPhotoOrder: String): RouteDetailEvent()
    data class OnTakePhotoCollect(val pathPhotoCollect:String): RouteDetailEvent()
    object OnUpdateOrder : RouteDetailEvent()
}
