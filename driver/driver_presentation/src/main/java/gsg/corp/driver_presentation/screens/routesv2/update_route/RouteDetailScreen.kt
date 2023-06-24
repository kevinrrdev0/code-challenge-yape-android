package gsg.corp.driver_presentation.screens.routesv2.update_route

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gsg.corp.core.util.UiEvent
import gsg.corp.core.util.toUpperCaseLocale
import gsg.corp.core_ui.LightGray
import gsg.corp.core_ui.LocalSpacing
import gsg.corp.core_ui.R
import gsg.corp.core_ui.RedGsg
import gsg.corp.core_ui.TextGray
import gsg.corp.core_ui.global_components_actions.DatePickerComponent
import gsg.corp.core_ui.global_components_actions.GlobalOutLineButton
import gsg.corp.core_ui.global_components_actions.ImagePicker
import gsg.corp.core_ui.global_components_inputs.GlobalCheckBox
import gsg.corp.core_ui.global_components_inputs.GlobalExtraSpacerSmall
import gsg.corp.core_ui.global_components_inputs.GlobalExtraSpacerSmallRow
import gsg.corp.core_ui.global_components_inputs.GlobalInput
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerExtraSmall
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerExtraSmallRow
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerMid
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerRowSmall
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerRowSmallMedium
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerSmall
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerSmallMedium
import gsg.corp.core_ui.global_components_texts.TextBody
import gsg.corp.core_ui.global_components_texts.TextHeadline5
import gsg.corp.core_ui.global_components_texts.TextHeadline6
import gsg.corp.core_ui.global_components_texts.TextSubtitle
import gsg.corp.core_ui.global_components_texts.TextSubtitle2
import gsg.corp.core_ui.global_components_ui.BoxLoadAnimation
import gsg.corp.driver_presentation.screens.routesv2.components.CustomDropDown
import gsg.corp.driver_presentation.screens.routesv2.components.CustomDropDownCode
import gsg.corp.driver_presentation.screens.routesv2.components.PhoneNumberActions
import gsg.corp.driver_presentation.screens.routesv2.routes_detail.RouteDetailEvent
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
                Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 6.dp, top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(38.dp)
                            .clickable {
                                onNavigateUp()
                            },
                        imageVector = Icons.Rounded.ChevronLeft,
                        contentDescription = null,
                        tint = RedGsg
                    )
                    GlobalSpacerExtraSmallRow()
                    TextHeadline5(
                        text = "Orden ${item.codeTracking}",
                        boldHighlighting = true,
                        fontWeight = FontWeight.Bold
                    )
                }
                GlobalSpacerSmallMedium()
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                ) {
                    TextHeadline6(
                        text = "Datos de la entrega",
                        textColor = TextGray,
                        boldHighlighting = true
                    )
                    GlobalSpacerSmall()
                    Row(verticalAlignment = Alignment.Top) {
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
                        Column {
                            TextBody(
                                text = "Cliente final:",
                                textColor = TextGray,
                                fontWeight = FontWeight.Light
                            )
                            GlobalSpacerExtraSmall()
                            TextBody(
                                text = item.fullName,
                                fontWeight = FontWeight.Medium
                            )
                            GlobalSpacerExtraSmall()
                            Row {
                                PhoneNumberActions(
                                    item.telephone,
                                    fontWeight = FontWeight.Medium
                                )
                                if (item.otherTelephone.isNotEmpty()) {
                                    GlobalSpacerRowSmallMedium()
                                    PhoneNumberActions(
                                        item.otherTelephone,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }

                        }
                    }
                    GlobalSpacerSmall()
                    Row(verticalAlignment = Alignment.Top) {
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
                        Column {
                            TextBody(
                                text = "Comentario del cliente:",
                                textColor = TextGray,
                                fontWeight = FontWeight.Light
                            )
                            GlobalSpacerExtraSmall()
                            TextBody(
                                text = item.observation,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    GlobalSpacerSmall()
                    Row(verticalAlignment = Alignment.Top) {
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
                        Column {
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
                    Row(verticalAlignment = Alignment.Top) {
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
                        Column {
                            TextBody(
                                text = "Referencias de ubicación:",
                                textColor = TextGray,
                                fontWeight = FontWeight.Light
                            )
                            GlobalSpacerExtraSmall()
                            TextBody(
                                text = item.addressReference,
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
                    Row(verticalAlignment = Alignment.Top) {
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
                        Column {
                            TextBody(
                                text = "Producto:",
                                textColor = TextGray,
                                fontWeight = FontWeight.Light
                            )
                            GlobalSpacerExtraSmall()
                            TextBody(
                                text = "${item.numberPackages} / ${item.product}",
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    GlobalSpacerSmall()
                    Row(verticalAlignment = Alignment.Top) {
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
                        Column {
                            TextBody(
                                text = "Detalle producto:",
                                textColor = TextGray,
                                fontWeight = FontWeight.Light
                            )
                            GlobalSpacerExtraSmall()
                            TextBody(
                                text = item.productDetail,
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

                    Row(verticalAlignment = Alignment.Top) {
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
                        Column {
                            TextBody(
                                text = "Monto a Cobrar:",
                                textColor = TextGray,
                                fontWeight = FontWeight.Light
                            )
                            GlobalSpacerExtraSmall()
                            TextBody(
                                text = "S/ ${item.payAmount} / ${item.codePayMethod}",
                                fontWeight = FontWeight.Medium,
                                boldHighlighting = true,
                                boldText = listOf("S/ ${item.payAmount}")
                            )
                        }
                    }
                    GlobalSpacerSmall()
                    Row(verticalAlignment = Alignment.Top) {
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
                        Column {
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
                    GlobalSpacerMid()
                    Divider(thickness = 1.dp, color = RedGsg)
                    GlobalSpacerMid()
                    TextHeadline6(
                        text = "Actualizar la ruta",
                        textColor = TextGray,
                        boldHighlighting = true
                    )
                    GlobalSpacerSmall()
                    CustomDropDown(
                        state.listState,
                        state.state,
                        onEventDropDown = { id, name ->
                            onEvent(
                                UpdateStateOrderEvent.OnStateSelected(
                                    id,
                                    name
                                )
                            )
                        },
                        label = "Seleccionar estado del pedido"
                    )
                    GlobalSpacerSmall()
                    GlobalInput(
                        state.comment,
                        "Ingresar Comentario",
                        maxLines = 4,
                        maxChar = 300,
                        onValueChange = { onEvent(UpdateStateOrderEvent.OnCommentEnter(it)) })

                    if (state.state.name == "REPROGRAMADO") {
                        GlobalSpacerSmall()
                        DatePickerComponent(
                            date = state.dateRescheduled,
                            label = "Fecha de la reprogramacion",
                            onDateSelected = { firstFormat, _ ->
                                onEvent(UpdateStateOrderEvent.OnDateRescheduledEnter(firstFormat))
                            })
                    } else {
                        onEvent(UpdateStateOrderEvent.OnDateRescheduledEnter(""))
                    }

                    GlobalSpacerSmall()
                    if (state.state.name == "ENTREGADO" || state.state.name == "RECHAZADO" || state.state.name == "PERDIDO") {
                        GlobalSpacerSmall()
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            ImagePicker(
                                label = "Foto Pedido",
                                url = item.pathPhotoState,
                                onPhotoIsTaken = {
                                    it?.let { uri ->
                                        onEvent(UpdateStateOrderEvent.OnTakePhotoOrder(uri))
                                    }
                                })
                            ImagePicker(
                                label = "Foto Pedido 2",
                                url = item.otherPathPhotoState,
                                onPhotoIsTaken = {
                                    it?.let { uri ->
                                        onEvent(UpdateStateOrderEvent.OnTakeOtherPhotoOrder(uri))
                                    }
                                })
                        }
                        GlobalSpacerSmall()
                    }

                    if (state.state.name == "ENTREGADO") {
                        GlobalSpacerSmall()
                        Row(verticalAlignment = Alignment.Top) {
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
                            Column {
                                TextBody(
                                    text = "Método de pago 1:",
                                    textColor = TextGray,
                                    fontWeight = FontWeight.Light
                                )
                                GlobalSpacerExtraSmall()
                                CustomDropDownCode(
                                    state.listPayMethod,
                                    state.codePayMethod1,
                                    onEventDropDown = { code, name ->
                                        onEvent(
                                            UpdateStateOrderEvent.OnCodePayMethod1Selected(
                                                code,
                                                name
                                            )
                                        )
                                    },
                                    label = "Seleccionar metodo de pago 1"
                                )
                                GlobalSpacerExtraSmall()

                                ImagePicker(
                                    label = "Foto del pago 1",
                                    url = state.pathPhotoPay1,
                                    onPhotoIsTaken = {
                                        it?.let { uri ->
                                            onEvent(UpdateStateOrderEvent.OnTakePhotoPay1(uri))
                                        }
                                    })
                                GlobalSpacerExtraSmall()
                                GlobalInput(
                                    state.payAmount1,
                                    "Monto pagado 1",
                                    maxLines = 1,
                                    maxChar = 10,
                                    type = KeyboardType.Decimal,
                                    onValueChange = {
                                        onEvent(
                                            UpdateStateOrderEvent.OnPayAmount1Enter(
                                                it
                                            )
                                        )
                                    })
                                GlobalSpacerExtraSmall()
                                GlobalInput(
                                    state.detailPay1,
                                    "Detale del pago 1",
                                    maxLines = 6,
                                    maxChar = 300,
                                    onValueChange = {
                                        onEvent(
                                            UpdateStateOrderEvent.OnCommentEnter(
                                                it
                                            )
                                        )
                                    })
                            }
                        }
                        GlobalSpacerExtraSmall()
                        Row(
                            Modifier.padding(start = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            GlobalCheckBox(state.flkPayGSG1, onCheckedChange = {
                                onEvent(UpdateStateOrderEvent.OnFlkPay1GSGChecked(it))
                            })
                            TextBody(text = "Pago para GSG?")
                        }
                        if (state.codePayMethod1.code != "" && state.codePayMethod1.code != "PAGADO") {
                            GlobalSpacerSmall()
                            Row(verticalAlignment = Alignment.Top) {
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
                                Column {
                                    TextBody(
                                        text = "Método de pago 2:",
                                        textColor = TextGray,
                                        fontWeight = FontWeight.Light
                                    )
                                    GlobalSpacerExtraSmall()
                                    CustomDropDownCode(
                                        state.listPayMethod,
                                        state.codePayMethod2,
                                        onEventDropDown = { code, name ->
                                            onEvent(
                                                UpdateStateOrderEvent.OnCodePayMethod2Selected(
                                                    code,
                                                    name
                                                )
                                            )
                                        },
                                        label = "Seleccionar metodo de pago 2"
                                    )
                                    GlobalSpacerExtraSmall()

                                    ImagePicker(
                                        label = "Foto del pago 2",
                                        url = state.pathPhotoPay2,
                                        onPhotoIsTaken = {
                                            it?.let { uri ->
                                                onEvent(UpdateStateOrderEvent.OnTakePhotoPay2(uri))
                                            }
                                        })
                                    GlobalSpacerExtraSmall()
                                    GlobalInput(
                                        state.payAmount2,
                                        "Monto pagado 2",
                                        maxLines = 1,
                                        maxChar = 10,
                                        type = KeyboardType.Decimal,
                                        onValueChange = {
                                            onEvent(
                                                UpdateStateOrderEvent.OnPayAmount2Enter(
                                                    it
                                                )
                                            )
                                        })
                                    GlobalSpacerExtraSmall()
                                    GlobalInput(
                                        state.detailPay2,
                                        "Detale del pago 2",
                                        maxLines = 6,
                                        maxChar = 300,
                                        onValueChange = {
                                            onEvent(
                                                UpdateStateOrderEvent.OnCommentEnter(
                                                    it
                                                )
                                            )
                                        })
                                }
                            }
                            GlobalSpacerExtraSmall()
                            Row(
                                Modifier.padding(start = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                GlobalCheckBox(state.flkPayGSG2, onCheckedChange = {
                                    onEvent(UpdateStateOrderEvent.OnFlkPay2GSGChecked(it))
                                })
                                TextBody(text = "Pago para GSG?")
                            }
                        }

                    }
                }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GlobalSpacerSmall()
                    if (state.routeDetail.stCode != "ENTREGADO") {
                        OutlinedButton(
                            onClick = {
                                onEvent(UpdateStateOrderEvent.OnUpdateOrder)
                            },
                            border = BorderStroke(1.dp, RedGsg)
                        ) {
                            TextSubtitle(
                                text = "Guardar Nuevo Estado",
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                                textColor = RedGsg
                            )
                        }
                    }
                    GlobalSpacerExtraSmall()
                    TextButton(
                        onClick = {
                            onNavigateUp()
                        }
                    ) {
                        TextSubtitle(
                            text = "Regresar a las rutas",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                            textColor = RedGsg
                        )
                    }
                    GlobalSpacerSmall()
                }
            }
        }
    }
    BoxLoadAnimation(state.isLoading)
}