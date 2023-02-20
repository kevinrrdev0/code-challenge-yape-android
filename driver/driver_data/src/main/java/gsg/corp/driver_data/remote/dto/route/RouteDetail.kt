package gsg.corp.driver_data.remote.dto.route

data class RouteDetail(
    val id: Int,
    val code_tracking: String,
    val id_business: Int,
    val id_route_type: Int,
    val code_collect_point: String,
    val client_full_name: String,
    val client_telephone_1: String,
    val client_telephone_2: String?,
    val client_address: String,
    val client_address_reference: String?,
    val client_address_district: String,
    val client_address_comments: String?,
    val date_register: String,
    val date_route: String,
    val product: String,
    val product_detail: String?,
    val number_packages: Int,
    val number_products: Int,
    val code_pay_method: String,
    val pay_amount: String,
    val pay_comments: String,
    val flk_active: Int
)