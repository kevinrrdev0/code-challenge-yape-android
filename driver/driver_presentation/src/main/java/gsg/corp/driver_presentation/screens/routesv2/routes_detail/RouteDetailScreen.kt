package gsg.corp.driver_presentation.screens.routesv2.routes_detail

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import gsg.corp.core_ui.global_components_inputs.GlobalInput
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerMid
import gsg.corp.core_ui.global_components_inputs.GlobalSpacerSmall
import gsg.corp.core_ui.global_components_texts.TextBody
import gsg.corp.core_ui.global_components_texts.TextSubtitle
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
            text = "Detalle de Ruta ${state.routeDetail.codeTracking}",
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
                TextBody(text = routeDetail.fullName)
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
                TextBody(text = "${routeDetail.address} / ${routeDetail.district} ")
            }
            TextBody(text = "Ref.: ${routeDetail.address}")
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
            TextBody(text = "Prod.: ${routeDetail.product}")
            TextBody(text = "Cant. Paquetes: ${routeDetail.numberPackages}")
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.vector__4_),
                    tint = Color.Black,
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp)
                        .padding(end = 4.dp)
                )
                TextBody(text = "Costo: ${routeDetail.cost} / Adelanto: ${routeDetail.advance} / Cobrar: ${routeDetail.payAmount}")
            }
            TextBody(text = "Metodo de Pago: ${routeDetail.codePayMethod}")
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
            TextSubtitle("Cuentas Cliente")
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
            TextSubtitle("Cuentas GSG")
        }
    }

    ExpressDialogScreen(show = showClientAccounts) { showClientAccounts = false }
    ExpressDialogScreen(show = showGSGAccounts) { showGSGAccounts = false }

}

@Composable
fun RouteStateOrder(state: RouteDetailState, onEvent: (RouteDetailEvent) -> Unit) {
    val stateCode = state.routeDetail.stCode
    OutlinedTextField(
        value = stateCode,
        onValueChange = {},
        readOnly = true,
        label = { Text(text = "Estado del pedido") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Red,
            backgroundColor = Color.Transparent,
            leadingIconColor = Color.Red,
            cursorColor = Color.Red,
            focusedLabelColor = Color.Red,
        )
    )

    if (stateCode == "REPROGRAMADO") {
        GlobalSpacerSmall()
        OutlinedTextField(
            value = state.dateRescheduled,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "Fecha de la reprogramacion") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Red,
                backgroundColor = Color.Transparent,
                leadingIconColor = Color.Red,
                cursorColor = Color.Red,
                focusedLabelColor = Color.Red,
            )
        )

    }
    GlobalSpacerSmall()
    GlobalInput(
        state.routeDetail.comment,
        "Comentario",
        maxLines = 3,
        onValueChange = { onEvent(RouteDetailEvent.OnCommentEnter(it)) })

    if (stateCode == "ENTREGADO" || stateCode == "RECHAZADO" || stateCode == "PERDIDO") {
        GlobalSpacerSmall()
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            if (state.routeDetail.pathPhotoState.isNotEmpty()) {
                AsyncCoilImage(state.routeDetail.pathPhotoState)
            }
            if (state.routeDetail.otherPathPhotoState.isNotEmpty()) {
                AsyncCoilImage(state.routeDetail.otherPathPhotoState)
            }
        }
    }
}

@Composable
fun AsyncCoilImage(url: String) {
    var imageZoom by remember {
        mutableStateOf(false)
    }

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.image),
        contentDescription = "full description",//stringResource(R.string.description)
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(128.dp)
            .clickable {
                imageZoom = true
            }
    )

    if (imageZoom) {
        Dialog(
            onDismissRequest = { imageZoom = false },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .background(Color.Black)
                    .clickable(onClick = { imageZoom = false })
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.image),
                    contentDescription = "full description",//stringResource(R.string.description)
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
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