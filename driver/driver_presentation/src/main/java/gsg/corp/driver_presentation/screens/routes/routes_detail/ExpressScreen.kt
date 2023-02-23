package gsg.corp.driver_presentation.screens.routes.routes_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import gsg.corp.driver_presentation.R

@Composable
fun ExpressScreen(
    onUndeliveredScreen: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp)
        ){
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
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp)) {
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
        Row {
            Text(
                text = "kevin Reyes",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 25.dp)
            )
            Spacer(
                modifier = Modifier
                    .width(170.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.vector__1_),
                contentDescription = null,
                tint = Color(0xFF5CEE3C)
            )
            Spacer(
                modifier = Modifier
                    .width(7.dp)
            )
            Box(
                Modifier
                    .background(Color(0xFF25D2D8))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_phone_24),
                    contentDescription = null,
                    tint = Color.White,
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
        ){
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
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 12.dp))
    {
        Text(
            text = "Datos de Cobro",
            Modifier
                .padding(start = 12.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
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
        ){
            Text(
                text = "5",
                fontWeight = FontWeight.SemiBold
            )
            Spacer(
                modifier = Modifier.size(2.dp)
            )
            Text(text = "Metodo de Pago")
            Text(text = "efectivo / Transferencia / Plin / Yape",  fontWeight = FontWeight.ExtraBold)

            Spacer(
                modifier = Modifier.size(2.dp)
            )
            Text(text = "Cuentas para las transferencias")
            Spacer(
                modifier = Modifier.height(6.dp)
            )
            Button(onClick = { show = true},
                colors = ButtonDefaults
                    .buttonColors
                        (backgroundColor = Color.Red,
                    contentColor = Color.White),
                shape = RoundedCornerShape(25),
                modifier = Modifier
                    .width(125.dp)
                    .height(35.dp)
            ) {
                Text(text = "Ver Cuentas",fontSize = 13.sp)
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
fun ExpressDialogScreen(show: Boolean, salir: () -> Unit,) {
    if (show) {
        Dialog(
            onDismissRequest = {}
        ) {
            Card(
                modifier = Modifier
                    .clip( RoundedCornerShape(16.dp))
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 12.dp, vertical = 24.dp)

                ) {
                    Text(
                        text = "Nro de Cuentas del Proveedor",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = Color(0xFF656060)
                    )
                    Row(
                        Modifier
                            .padding(start = 15.dp), Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.carbon_enterprise),
                            contentDescription = null
                        )
                        Text(
                            text = "BCP",
                            fontSize = 16.sp,
                            color = Color(0xFF656060),
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                    Column(
                        Modifier.padding(start = 60.dp)
                    ){
                        Text(
                            text = "191 4982823 0 12", fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Kevin Quispe Mamani",
                            fontWeight = FontWeight.Bold
                        )

                    }
                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
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
                    Spacer(
                        modifier = Modifier
                            .height(19.dp)
                    )
                    Row(
                        Modifier
                            .padding(start = 15.dp)
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.carbon_enterprise),
                            contentDescription = null
                        )
                        Text(text = "InterBank", fontSize = 16.sp, color = Color(0xFF656060))
                    }
                    Column(
                        Modifier
                            .padding(start = 60.dp)
                    ){
                        Text(
                            text = "191 4982823 0 12",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Mauricio Reyes Castro",
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif
                        )
                        Spacer(modifier = Modifier.height(10.dp))
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
                        modifier = Modifier.height(19.dp)
                    )
                    Row(
                        Modifier
                            .padding(start = 15.dp)
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.carbon_enterprise),
                            contentDescription = null
                        )
                        Text(
                            text = "BBVA",
                            fontSize = 16.sp, color = Color(0xFF656060)
                        )
                    }
                    Column(
                        Modifier
                            .padding(start = 60.dp)
                    ){
                        Text(
                            text = "191 4982823 0 12",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Kevin Quispe Mamani",
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
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
                    Row(
                        Modifier
                            .padding(start = 15.dp)
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.carbon_enterprise),
                            contentDescription = null
                        )
                        Text(text = "Yape", fontSize = 16.sp, color = Color(0xFF656060))
                    }
                    Column(
                        Modifier
                            .padding(start = 60.dp)
                    ){
                        Text(
                            text = "191 4982823 0 12",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Kevin Quispe Mamani",
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(
                            modifier = Modifier.height(10.dp)
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
                            .height(10.dp)
                    )
                    Row(
                        Modifier
                            .clickable { salir() }
                    ){
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
