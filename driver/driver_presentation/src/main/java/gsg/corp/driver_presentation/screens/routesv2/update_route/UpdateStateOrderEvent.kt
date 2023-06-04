package gsg.corp.driver_presentation.screens.routesv2.update_route

sealed class UpdateStateOrderEvent{
    data class OnStateSelected(val id: Int,val name:String): UpdateStateOrderEvent()
    data class OnDateRescheduledEnter(val dateRescheduled: String): UpdateStateOrderEvent()
    data class OnCommentEnter(val comment: String): UpdateStateOrderEvent()
    data class OnTakePhotoOrder(val pathPhotoOrder: String): UpdateStateOrderEvent()
    data class OnTakePhotoCollect(val pathPhotoCollect:String): UpdateStateOrderEvent()
    data class OnCodePayMethod1Selected(val code: String,val name:String): UpdateStateOrderEvent()
    data class OnFlkPay1GSGChecked(val checked: Boolean ): UpdateStateOrderEvent()
    data class OnTakePhotoPay1(val pathPhotoPay1:String): UpdateStateOrderEvent()
    data class OnCodePayMethod2Selected(val code: String,val name:String): UpdateStateOrderEvent()
    data class OnFlkPay2GSGChecked(val checked: Boolean ): UpdateStateOrderEvent()
    data class OnTakePhotoPay2(val pathPhotoPay2:String): UpdateStateOrderEvent()
    object OnUpdateOrder : UpdateStateOrderEvent()
}
