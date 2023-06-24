package gsg.corp.driver_presentation.screens.routesv2.update_route

import android.net.Uri
import gsg.corp.core.domain.model.GeneralType
import gsg.corp.core.domain.model.GeneralTypeCode
import gsg.corp.core.global_models.MessageError
import gsg.corp.driver_domain.model.RouteDetail

data class UpdateStateOrderState(
    val idRoute: Int = 0,
    val uri: Uri = Uri.EMPTY,
    val state: GeneralType = GeneralType(0, ""),
    val listState: List<GeneralType> = listOf(
        GeneralType(7, "PENDIENTE"),
        GeneralType(8, "CANCELADO"),
        GeneralType(9, "EN RUTA"),
        GeneralType(10, "EN EL PUNTO"),
        GeneralType(11, "REPROGRAMADO"),
        GeneralType(12, "PERDIDO"),
        GeneralType(13, "RECHAZADO"),
        GeneralType(14, "ENTREGADO")
    ),
    val comment: String = "",
    val dateRescheduled: String = "",
    val photoOrder: String = "",
    val otherPhotoOrder:  String = "",
    val isLoading: Boolean = false,
    val messageError: MessageError = MessageError(),
    val routeDetail:RouteDetail = RouteDetail(),
    val listPayMethod: List<GeneralTypeCode> = listOf(
        GeneralTypeCode("", "Seleccionar metodo de pago"),
        GeneralTypeCode("PAGADO", "PAGADO"),
        GeneralTypeCode("EFECTIVO", "EFECTIVO"),
        GeneralTypeCode("TARJETA", "TARJETA"),
        GeneralTypeCode("BCP", "TRANSF. BCP"),
        GeneralTypeCode("BBVA", "TRANSF. BBVA"),
        GeneralTypeCode("INTERBANK", "TRANSF. INTERBANK"),
        GeneralTypeCode("SCOTIABANK", "TRANSF. SCOTIABANK"),
        GeneralTypeCode("TRANSFERENCIA", "OTRO BANCO"),
        GeneralTypeCode("YAPE", "YAPE"),
        GeneralTypeCode("PLIN", "PLIN"),
        GeneralTypeCode("OTRO", "OTRO")
    ),
    val codePayMethod1: GeneralTypeCode = GeneralTypeCode(),
    val flkPayGSG1 : Boolean = false,
    val detailPay1: String = "",
    val pathPhotoPay1: String = "",
    val payAmount1: String = "",
    val codePayMethod2: GeneralTypeCode = GeneralTypeCode(),
    val flkPayGSG2 : Boolean = false,
    val detailPay2: String = "",
    val pathPhotoPay2: String = "",
    val payAmount2: String = ""
)