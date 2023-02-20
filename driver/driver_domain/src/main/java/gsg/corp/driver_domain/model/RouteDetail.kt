package gsg.corp.driver_domain.model

data class RouteDetail(
    val id: Int,
    val codeTracking: String,
    val idBusiness: Int,
    val idRouteType: Int,
    val codeCollectPoint: String,
    val clientFullName: String,
    val clientPhoneFirst: String,
    val clientPhoneSecond: String?,
    val clientAddress: String,
    val clientAddressReference: String?,
    val clientAddressDistrict: String,
    val clientAddressComments: String?,
    val dateRegister: String,
    val dateRoute: String,
    val product: String,
    val productDetail: String?,
    val numberPackages: Int,
    val numberProducts: Int,
    val codePayMethod: String,
    val payAmount: String,
    val payComments: String,
    val flkActive: Int
)