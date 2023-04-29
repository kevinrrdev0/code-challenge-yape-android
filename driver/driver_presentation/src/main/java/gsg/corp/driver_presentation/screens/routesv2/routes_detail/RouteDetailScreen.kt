package gsg.corp.driver_presentation.screens.routesv2.routes_detail

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberImagePainter
import gsg.corp.core.domain.model.GeneralType
import gsg.corp.core_ui.global_components_actions.CameraView
import gsg.corp.core_ui.global_components_actions.DatePickerComponent
import gsg.corp.core_ui.global_components_actions.GlobalCamera
import gsg.corp.core_ui.global_components_actions.ImagePicker
import gsg.corp.core_ui.global_components_inputs.GlobalInput
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerMid
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerSmall
import gsg.corp.core_ui.global_components_texts.TextBody
import gsg.corp.core_ui.global_components_texts.TextSubTitle
import gsg.corp.core_ui.global_components_ui.BoxLoadAnimation
import gsg.corp.driver_domain.model.RouteDetail
import gsg.corp.driver_presentation.R


@Preview
@Composable
fun PreviewRouteDetailScreen() {
    RouteDetailScreen(RouteDetailState(), onEvent = {})
}

@Composable
fun RouteDetailScreen(state: RouteDetailState, onEvent: (RouteDetailEvent) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        if (!state.isLoading) {
            BodyRouteDetail(state, onEvent = { onEvent(it) })
        }
    }
    BoxLoadAnimation(state.isLoading)
}

@Composable
fun BodyRouteDetail(state: RouteDetailState, onEvent: (RouteDetailEvent) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Detalle de Ruta ${state.routeDetail.code_tracking}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        GlobalSpacerSmall()
        RouteDetailOrder(state.routeDetail)
        GlobalSpacerSmall()
        RoutePayOrder(state.routeDetail)
        GlobalSpacerSmall()
        RouteStateOrder(state, onEvent = { onEvent(it) })
        endBody(onEvent = { onEvent(it) })
    }
}

@Composable
fun endBody(onEvent: (RouteDetailEvent) -> Unit) {
    Row(modifier = Modifier.padding(15.dp)) {
        TextButton(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color.Red
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_close_24),
                contentDescription = null
            )
            Text(text = "No Entregado")
        }
        Spacer(modifier = Modifier.width(20.dp))
        TextButton(
            onClick = {
                onEvent(RouteDetailEvent.OnUpdateOrder)

            },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color(0xFF41DA26)
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_check_24),
                contentDescription = null
            )
            Text(text = "Entregado")
        }
    }
}

@Composable
fun RouteDetailOrder(routeDetail: RouteDetail) {

    Card(
        elevation = 10.dp, modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.vector__3_),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(22.dp)
                        .padding(end = 4.dp)
                )
                TextBody(body = routeDetail.full_name)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.vector__2_),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(22.dp)
                        .padding(end = 4.dp)
                )
                TextBody(body = "${routeDetail.address} / ${routeDetail.district} ")
            }
            TextBody(body = "Ref.: ${routeDetail.address}")
        }
    }


}

@Composable
fun RoutePayOrder(routeDetail: RouteDetail) {

    Card(
        elevation = 10.dp, modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(14.dp)) {
            TextBody(body = "Prod.: ${routeDetail.product}")
            TextBody(body = "Cant. Paquetes: ${routeDetail.number_packages}")
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.vector__4_),
                    tint = Color.Black,
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp)
                        .padding(end = 4.dp)
                )
                TextBody(body = "Costo: ${routeDetail.cost} / Adelanto: ${routeDetail.advance} / Cobrar: ${routeDetail.pay_amount}")
            }
            TextBody(body = "Metodo de Pago: ${routeDetail.code_pay_method}")
        }
    }

    var showClientAccounts by remember { mutableStateOf(false) }
    var showGSGAccounts by remember { mutableStateOf(false) }
    GlobalSpacerMid()
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Button(
            onClick = { showClientAccounts = true },
            colors = ButtonDefaults
                .buttonColors
                    (
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                ),
            shape = RoundedCornerShape(16)

        ) {
            Icon(
                imageVector = Icons.Outlined.CreditCard,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            TextSubTitle("Cuentas Cliente")
        }
        Button(
            onClick = { showGSGAccounts = true },
            colors = ButtonDefaults
                .buttonColors
                    (
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                ),
            shape = RoundedCornerShape(8)
        ) {
            Icon(
                imageVector = Icons.Outlined.CreditCard,
                contentDescription = null,
                modifier = Modifier.padding(end = 7.dp)
            )
            TextSubTitle("Cuentas GSG")
        }
    }

    ExpressDialogScreen(show = showClientAccounts) { showClientAccounts = false }
    ExpressDialogScreen(show = showGSGAccounts) { showGSGAccounts = false }

}


@Composable
fun RouteStateOrder(state: RouteDetailState, onEvent: (RouteDetailEvent) -> Unit) {
    CustomDropDown(
        state.listState,
        state.state,
        onEventDropDown = { id, name->onEvent(RouteDetailEvent.OnStateSelected(id,name))})

    if (state.state.name == "REPROGRAMADO") {
        GlobalSpacerSmall()
        DatePickerComponent(
            date = state.dateRescheduled,
            label = "Fecha de la reprogramacion",
            onDateSelected = { firstFormat, _ ->
                onEvent(RouteDetailEvent.OnDateRescheduledEnter(firstFormat))
            })
    } else {
        onEvent(RouteDetailEvent.OnDateRescheduledEnter(""))
    }
    GlobalSpacerSmall()
    GlobalInput(
        state.comment,
        "Ingresar Comentario",
        maxLines = 3,
        onValueChange = { onEvent(RouteDetailEvent.OnCommentEnter(it)) })

    if (state.state.name == "ENTREGADO" || state.state.name == "RECHAZADO" || state.state.name == "PERDIDO") {
        GlobalSpacerSmall()
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            ImagePicker(label = "Foto Pedido", onPhotoIsTaken = {
                it?.let { uri ->
                    onEvent(RouteDetailEvent.OnTakePhotoOrder(uri))
                }

            })
            ImagePicker(label = "Foto Pago", onPhotoIsTaken = {
                it?.let { uri ->
                    onEvent(RouteDetailEvent.OnTakePhotoCollect(uri))
                }
            })
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropDown(
    listItems: List<GeneralType>,
    stateSelected: GeneralType,
    onEventDropDown: (Int,String) -> Unit
) {
    val contextForToast = LocalContext.current.applicationContext

    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }

    // remember the selected item
    var selectedItem by remember {
        mutableStateOf(stateSelected)
    }

    // box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        // text field
        OutlinedTextField(
            value = selectedItem.name,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "Estado del pedido") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Red,
                backgroundColor = Color.Transparent,
                leadingIconColor = Color.Red,
                cursorColor = Color.Red,
                focusedLabelColor = Color.Red,
            )
        )
        //menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { option ->
                DropdownMenuItem(onClick = {
                    selectedItem = option
                    // put event
                    onEventDropDown(
                            selectedItem.id,
                            selectedItem.name
                    )
                    Toast.makeText(contextForToast, option.name, Toast.LENGTH_SHORT).show()
                    expanded = false
                }) {
                    Text(text = option.name)
                }
            }
        }
    }

}


@Composable
fun ExpressDialogScreen(show: Boolean, exit: () -> Unit) {

    val test = listOf("test", "asdasd")

    if (show) {
        Dialog(
            onDismissRequest = {
                exit()
            }
        ) {
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .verticalScroll(rememberScrollState())
                    .width(393.dp)
                    .heightIn(0.dp, 500.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 12.dp, vertical = 24.dp)
                ) {
                    Column(Modifier.fillMaxWidth()) {
                        Text(
                            text = "Nro de Cuentas del Proveedor",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color(0xFF656060)
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                    LazyColumn(
                        Modifier.heightIn(0.dp, 365.dp)
                    ) {
                        itemsIndexed(
                            items = test
                        ) { index, t ->
                            Spacer(modifier = Modifier.height(15.dp))
                            Row(
                                Modifier
                                    .padding(start = 15.dp), Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.carbon_enterprise),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(7.dp))
                                Text(
                                    text = t,
                                    fontSize = 16.sp,
                                    color = Color(0xFF656060),
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                            }
                            Column(
                                Modifier.padding(start = 44.dp)
                            ) {
                                Text(
                                    text = t, fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = t,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(
                                modifier = Modifier
                                    .height(9.dp)
                            )
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(start = 18.dp, end = 18.dp)
                            ) {
                                Divider(
                                    Modifier
                                        .height(1.dp)
                                        .background(Color(0xFFA5A4A4))
                                )
                            }

                        }
                    }
                    Column(Modifier.fillMaxWidth()) {
                        Spacer(
                            modifier = Modifier
                                .height(36.dp)
                        )
                        Row(
                            Modifier
                                .clickable { exit() }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.arrowleft2),
                                contentDescription = null,
                                tint = Color.Red
                            )
                            Text(
                                text = "Atras",
                                color = Color.Red,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }
    }
}