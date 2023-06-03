package gsg.corp.driver_presentation.screens.routesv2.update_route

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.ArrowLeft
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gsg.corp.core.util.UiEvent
import gsg.corp.core.util.toUpperCaseLocale
import gsg.corp.core_ui.R
import gsg.corp.core_ui.RedGsg
import gsg.corp.core_ui.TextGray
import gsg.corp.core_ui.global_components_actions.ImagePicker
import gsg.corp.core_ui.global_components_inputs.GlobalExtraSpacerSmall
import gsg.corp.core_ui.global_components_inputs.GlobalExtraSpacerSmallRow
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerExtraSmall
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerMid
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerRowSmall
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerRowSmallMedium
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerSmall
import gsg.corp.core_ui.global_components_texts.TextBody
import gsg.corp.core_ui.global_components_texts.TextHeadline5
import gsg.corp.core_ui.global_components_texts.TextHeadline6
import gsg.corp.core_ui.global_components_texts.TextSubtitle
import gsg.corp.core_ui.global_components_texts.TextSubtitle2
import gsg.corp.core_ui.global_components_ui.BoxLoadAnimation
import gsg.corp.driver_presentation.screens.routesv2.components.PhoneNumberActions
import kotlinx.coroutines.flow.Flow

@Composable
fun RouteDetailScreenV2(
    state: UpdateStateOrderState,
    uiEvent: Flow<UiEvent>,
    onEvent: (UpdateStateOrderEvent) -> Unit,
    onNavigateUp: () -> Unit
) {
    val item = state.routeDetail
    LaunchedEffect(key1 = true) {
        uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> {
                }

                is UiEvent.ShowSnackBar -> {
                }

                is UiEvent.NavigateUp -> onNavigateUp()
                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        if (!state.isLoading) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {

                TextHeadline5(
                    text = "Orden ${item.code_tracking}",
                    boldHighlighting = true,
                    fontWeight = FontWeight.Bold
                )
                GlobalSpacerMid()
                TextHeadline6(
                    text = "Datos de la entrega",
                    textColor = TextGray,
                    boldHighlighting = true
                )
                GlobalSpacerSmall()
                Row(verticalAlignment = Alignment.Top){
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                onNavigateUp()
                            },
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = null,
                        tint = RedGsg
                    )
                    GlobalExtraSpacerSmallRow()
                    Column{
                        TextBody(
                            text = "Cliente final:",
                            textColor = TextGray,
                            fontWeight = FontWeight.Light
                        )
                        GlobalSpacerExtraSmall()
                        TextBody(
                            text = item.full_name,
                            fontWeight = FontWeight.Medium
                        )
                        GlobalSpacerExtraSmall()
                        Row {
                            PhoneNumberActions(item.telephone,
                                fontWeight = FontWeight.Medium)
                            if (item.other_telephone.isNotEmpty()) {
                                GlobalSpacerRowSmallMedium()
                                PhoneNumberActions(item.other_telephone,
                                    fontWeight = FontWeight.Medium)
                            }
                        }

                    }
                }
                GlobalSpacerSmall()
                Row(verticalAlignment = Alignment.Top){
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                onNavigateUp()
                            },
                        painter = painterResource(id = R.drawable.ic_chat_message),
                        contentDescription = null,
                        tint = RedGsg
                    )
                    GlobalExtraSpacerSmallRow()
                    Column{
                        TextBody(
                            text = "Comentario del cliente:",
                            textColor = TextGray,
                            fontWeight = FontWeight.Light
                        )
                        GlobalSpacerExtraSmall()
                        TextBody(
                            text = "porfavor llamar cuando esten en la puerta modificar el texto por lado de backend",
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                GlobalSpacerSmall()
                Row(verticalAlignment = Alignment.Top){
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                onNavigateUp()
                            },
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = null,
                        tint = RedGsg
                    )
                    GlobalExtraSpacerSmallRow()
                    Column{
                        TextBody(
                            text = "Dirección de envio:",
                            textColor = TextGray,
                            fontWeight = FontWeight.Light
                        )
                        GlobalSpacerExtraSmall()
                        TextBody(
                            text = "${item.district.toUpperCaseLocale()} - ${item.address.toUpperCaseLocale()} ",
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                GlobalSpacerSmall()
                Row(verticalAlignment = Alignment.Top){
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                onNavigateUp()
                            },
                        painter = painterResource(id = R.drawable.ic_map_ref),
                        contentDescription = null,
                        tint = RedGsg
                    )
                    GlobalExtraSpacerSmallRow()
                    Column{
                        TextBody(
                            text = "Referencias de ubicación:",
                            textColor = TextGray,
                            fontWeight = FontWeight.Light
                        )
                        GlobalSpacerExtraSmall()
                        TextBody(
                            text = item.address_reference,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                GlobalSpacerSmall()
                TextHeadline6(
                    text = "Detalles del producto",
                    textColor = TextGray,
                    boldHighlighting = true
                )
                GlobalSpacerSmall()
                Row(verticalAlignment = Alignment.Top){
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                onNavigateUp()
                            },
                        painter = painterResource(id = R.drawable.ic_box_package),
                        contentDescription = null,
                        tint = RedGsg
                    )
                    GlobalExtraSpacerSmallRow()
                    Column{
                        TextBody(
                            text = "Producto:",
                            textColor = TextGray,
                            fontWeight = FontWeight.Light
                        )
                        GlobalSpacerExtraSmall()
                        TextBody(
                            text = "${item.number_packages} / ${item.product}",
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                GlobalSpacerSmall()
                Row(verticalAlignment = Alignment.Top){
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                onNavigateUp()
                            },
                        painter = painterResource(id = R.drawable.ic_product_detail),
                        contentDescription = null,
                        tint = RedGsg
                    )
                    GlobalExtraSpacerSmallRow()
                    Column{
                        TextBody(
                            text = "Detalle producto:",
                            textColor = TextGray,
                            fontWeight = FontWeight.Light
                        )
                        GlobalSpacerExtraSmall()
                        TextBody(
                            text = "${item.product} detalle del producto aqui",
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                GlobalSpacerSmall()
                TextHeadline6(
                    text = "Detalles del cobro",
                    textColor = TextGray,
                    boldHighlighting = true
                )
                GlobalSpacerSmall()

                Row(verticalAlignment = Alignment.Top){
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                onNavigateUp()
                            },
                        painter = painterResource(id = R.drawable.ic_wallet),
                        contentDescription = null,
                        tint = RedGsg
                    )
                    GlobalExtraSpacerSmallRow()
                    Column{
                        TextBody(
                            text = "Monto a Cobrar:",
                            textColor = TextGray,
                            fontWeight = FontWeight.Light
                        )
                        GlobalSpacerExtraSmall()
                        TextBody(
                            text = "S/ ${item.pay_amount} / ${item.code_pay_method}",
                            fontWeight = FontWeight.Medium,
                            boldHighlighting = true,
                            boldText = listOf("S/ ${item.pay_amount}")
                        )


                    }
                }
                GlobalSpacerSmall()
                Row(verticalAlignment = Alignment.Top){
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                onNavigateUp()
                            },
                        painter = painterResource(id = R.drawable.ic_chat_message),
                        contentDescription = null,
                        tint = RedGsg
                    )
                    GlobalExtraSpacerSmallRow()
                    Column{
                        TextBody(
                            text = "Detalle del cobro:",
                            textColor = TextGray,
                            fontWeight = FontWeight.Light
                        )
                        GlobalSpacerExtraSmall()
                        TextBody(
                            text = "paga con tarjeta llevar POS para pago con cuotas",
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                GlobalSpacerSmall()
                Divider(thickness = 1.dp, color = RedGsg)
                GlobalSpacerSmall()
                TextHeadline6(
                    text = "Actualizar la ruta",
                    textColor = TextGray,
                    boldHighlighting = true
                )
                GlobalSpacerSmall()

                if (true || state.state.name == "ENTREGADO" || state.state.name == "RECHAZADO" || state.state.name == "PERDIDO") {
                    GlobalSpacerSmall()
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        ImagePicker(label = "Foto Pedido", onPhotoIsTaken = {
                            it?.let { uri ->
                                onEvent(UpdateStateOrderEvent.OnTakePhotoOrder(uri))
                            }

                        })
                        ImagePicker(label = "Foto Pago", onPhotoIsTaken = {
                            it?.let { uri ->
                                onEvent(UpdateStateOrderEvent.OnTakePhotoCollect(uri))
                            }
                        })
                    }
                }
            }

        }
    }
    BoxLoadAnimation(state.isLoading)
}
//
//Icon(
//modifier = Modifier
//.size(32.dp)
//.clickable {
//    onNavigateUp()
//},
//imageVector = Icons.Rounded.ChevronLeft,
//contentDescription = null,
//tint = RedGsg
//)