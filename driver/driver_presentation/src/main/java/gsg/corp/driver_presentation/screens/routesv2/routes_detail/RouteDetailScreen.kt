package gsg.corp.driver_presentation.screens.routesv2.routes_detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import gsg.corp.core_ui.global_components_inputs.GlobalExtraSpacerSmall
import gsg.corp.core_ui.global_components_texts.TextBody
import gsg.corp.core_ui.global_components_texts.TextSubTitle
import gsg.corp.core_ui.global_components_ui.BoxLoadAnimation
import gsg.corp.driver_domain.model.RouteDetail
import gsg.corp.driver_presentation.R

@Composable
fun RouteDetailScreen(state: RouteDetailState, onEvent: (RouteDetailEvent) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        if (!state.isLoading) {
            BodyRouteDetail(state.routeDetail)
        }
    }
    BoxLoadAnimation(state.isLoading)
}

@Composable
fun BodyRouteDetail(routeDetail: RouteDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Detalle de Ruta ${routeDetail.code_tracking}",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
        RouteDetailOrder(routeDetail)
        GlobalExtraSpacerSmall()
        Divider(
            Modifier
                .height(1.dp)
                .background(Color(0xFFA5A4A4))
        )
        GlobalExtraSpacerSmall()
        RoutePayOrder(routeDetail)


    }
}

@Composable
fun RouteDetailOrder(routeDetail: RouteDetail) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.vector__3_),
            contentDescription = null,
            modifier = Modifier.size(22.dp).padding(end = 4.dp)
        )
        TextBody(body = routeDetail.full_name)
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.vector__2_),
            contentDescription = null,
            modifier = Modifier.size(22.dp).padding(end = 4.dp)
        )
        TextBody(body = "${routeDetail.address} / ${routeDetail.district} ")
    }
    TextBody(body = "Ref.: ${routeDetail.address}")
    TextBody(body = "Prod.: ${routeDetail.product}")
    TextBody(body = "Cant. Paquetes: ${routeDetail.number_packages}")
}

@Composable
fun RoutePayOrder(routeDetail: RouteDetail) {
    var showClientAccounts by remember { mutableStateOf(false) }
    var showGSGAccounts by remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.vector__4_),
            contentDescription = null,
            modifier = Modifier.size(22.dp).padding(end = 4.dp)
        )
        TextBody(body = "Costo: ${routeDetail.cost} / Adelanto: ${routeDetail.advance} / Cobrar: ${routeDetail.pay_amount}")
    }
    TextBody(body = "Metodo de Pago: ${routeDetail.code_pay_method}")

    Row(horizontalArrangement = Arrangement.SpaceBetween){
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
            TextSubTitle("Ver Cuentas Cliente")
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
            TextSubTitle("Ver Cuentas GSG")
        }
    }

    ExpressDialogScreen(show = showClientAccounts) { showClientAccounts = false }
    ExpressDialogScreen(show = showGSGAccounts) { showGSGAccounts = false }
}

@Composable
fun ExpressScreen(
    onUndeliveredScreen: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Express RUT-EXP1A",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            ExpressData()
            Spacer(
                modifier = Modifier
                    .height(15.dp)
            )
        }
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
        Spacer(
            modifier = Modifier
                .height(19.dp)
        )
        CollectionData()
        Column(modifier = Modifier.fillMaxSize(), Arrangement.Bottom) {
            ExpressDeliveries(
                onClickUndelivered = onUndeliveredScreen
            )
        }
    }
}

@Composable
fun ExpressData() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Datos del Express"
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Row {
            Icon(
                painter = painterResource(id = R.drawable.vector__3_),
                contentDescription = null,
                modifier = Modifier.padding(end = 7.dp)
            )
            Text(text = "Cliente")
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "kevin Reyes",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 25.dp)
            )
            Box(
                modifier = Modifier
                    .padding(end = 40.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.vector__1_),
                    contentDescription = null,
                    tint = Color(0xFF5CEE3C)
                )
            }
            Box(modifier = Modifier.align(Alignment.TopEnd)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_phone),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }

        Row {
            Icon(
                painter = painterResource(id = R.drawable.vector__2_),
                contentDescription = null,
                modifier = Modifier.padding(end = 7.dp)
            )
            Text(text = "Direccion:")
        }
        Spacer(
            modifier = Modifier
                .height(3.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Av. angelica gamarra cuadra 7",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 21.dp)
            )

        }
        Spacer(
            modifier = Modifier
                .height(3.dp)
        )
        Column(
            modifier = Modifier.padding(start = 21.dp)
        ) {
            Text(
                text = "Referencia"
            )
            Text(
                text = "Frente KFC",
                fontWeight = FontWeight.SemiBold
            )
            Spacer(
                modifier = Modifier
                    .height(3.dp)
            )
            Text(
                text = "Producto a entregar"
            )
            Text(
                text = "zapatillas nike talla 42",
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .padding(start = 21.dp)
        ) {
            Text(
                text = "Cantidad:"
            )
            Spacer(
                modifier = Modifier
                    .width(3.dp)
            )
            Text(text = "3", fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun CollectionData() {
    var show by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp)
    )
    {
        Text(
            text = "Datos de Cobro",
            Modifier
                .padding(start = 12.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.vector__4_),
                contentDescription = null
            )
            Spacer(
                modifier = Modifier
                    .width(5.dp)
            )
            Text(
                text = "Monto a cobrar",
                modifier = Modifier
                    .padding(top = 1.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 26.dp)
        ) {
            Text(
                text = "5",
                fontWeight = FontWeight.SemiBold
            )
            Spacer(
                modifier = Modifier.size(2.dp)
            )
            Text(text = "Metodo de Pago")
            Text(text = "efectivo / Transferencia / Plin / Yape", fontWeight = FontWeight.SemiBold)

            Spacer(
                modifier = Modifier.height(13.dp)
            )
            Text(text = "Cuentas para las transferencias")
            Spacer(
                modifier = Modifier.height(17.dp)
            )
            Button(
                onClick = { show = true },
                colors = ButtonDefaults
                    .buttonColors
                        (
                        backgroundColor = Color.Red,
                        contentColor = Color.White
                    ),
                shape = RoundedCornerShape(25),
                modifier = Modifier
                    .width(135.dp)
                    .height(35.dp)
            ) {
                Text(text = "Ver Cuentas", fontSize = 13.sp)
            }
            ExpressDialogScreen(show = show) { show = false }
        }

    }
}

@Composable
fun ExpressDeliveries(
    onClickUndelivered: () -> Unit
) {
    Row(modifier = Modifier.padding(15.dp)) {
        TextButton(
            onClick = { onClickUndelivered() },
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
            onClick = {},
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