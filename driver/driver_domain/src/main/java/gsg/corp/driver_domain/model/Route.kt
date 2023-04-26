package gsg.corp.driver_domain.model

data class Route(
    val address: String = "",
    val address_reference: String= "",
    val advance: String = "",
    val code_pay_method: String = "",
    val code_tracking: String = "",
    val cost: String = "",
    val date_route: String = "",
    val district: String= "" ,
    val driver: String = "",
    val full_name: String = "",
    val id: Int = 0,
    val id_provider: Int = 0,
    val name_business: String = "",
    val number_packages: Int = 0,
    val other_telephone: String = "",
    val pay_amount: String = "",
    val product: String = "",
    val size: String = "",
    val st_code: String = "",
    val telephone: String = ""
)
