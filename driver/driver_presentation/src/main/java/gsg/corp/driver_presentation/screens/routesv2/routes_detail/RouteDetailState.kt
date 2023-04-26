package gsg.corp.driver_presentation.screens.routesv2.routes_detail

import android.net.Uri
import gsg.corp.core.domain.model.GeneralType
import gsg.corp.core.global_models.MessageError
import gsg.corp.driver_domain.model.RouteDetail

data class RouteDetailState(
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
    val pathPhotoState: String = "",
    val pathPhotoCollect: String = "",
    val isLoading: Boolean = false,
    val messageError: MessageError = MessageError(),
    val routeDetail:RouteDetail = RouteDetail()
)