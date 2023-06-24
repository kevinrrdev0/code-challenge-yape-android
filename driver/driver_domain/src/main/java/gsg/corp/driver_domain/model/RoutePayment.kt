package gsg.corp.driver_domain.model

data class RoutePayment(
    val codePayMethod: String = "",
    val flkPayGsg: Int = 0,
    val idRoute: Int = 0,
    val idRoutePayment: Int =0,
    val otherPathPhotoPay: String = "",
    val pathPhotoPay: String = "",
    val payAmount: String = "",
    val payDetail: String = ""
)

//const objRoutePayment = {
//    id_route_payment: $("#id_code_pay_method_m_state_route").val(),
//    code_pay_method: $("#code_m_state_pay_method").val(),
//    flk_pay_gsg: $("#flk_pay_gsg").is(":checked") ? 1 : 0,
//    pay_amount: val_pay_amount1,
//    pay_detail: $("#pay_detail").val(),
//};