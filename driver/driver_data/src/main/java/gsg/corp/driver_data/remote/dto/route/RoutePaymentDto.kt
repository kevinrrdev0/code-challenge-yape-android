package gsg.corp.driver_data.remote.dto.route

data class RoutePaymentDto(
    val code_pay_method: String,
    val flk_pay_gsg: Int,
    val id_route: Int,
    val id_route_payment: Int,
    val other_path_photo_pay: String,
    val path_photo_pay: String,
    val pay_amount: String,
    val pay_detail: String
)