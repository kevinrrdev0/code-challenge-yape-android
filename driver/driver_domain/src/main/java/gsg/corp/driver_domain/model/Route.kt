package gsg.corp.driver_domain.model

data class Route(
    val id: String,
    val route_type: String,
    val driver: String,
    val telephone1: String,
    val client: String,
    val cel1: String,
    val cel2: String,
    val district: String,
    val address: String,
    val address_ref: String,
    val product_name: String,
    val product_detail: String,
    val pay_method: String,
    val amount_collect: String,
    val detail_payment: String,
    val date_route: String,
    val state: String,
    val cost_service: String,
)
