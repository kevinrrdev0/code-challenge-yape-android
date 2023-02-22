package gsg.corp.driver_presentation.screens.routes.routes_detail

import androidx.compose.foundation.*
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
import gsg.corp.driver_presentation.screens.route_detail.RouteDetailScreen

@Preview
@Composable
fun ZoneScreen() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Zona - RUT-ZON1-1A",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            ZoneDataDetail()
            Spacer(
                modifier = Modifier
                    .height(45.dp)
            )
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
            ZonesButton()
            Column(modifier = Modifier.fillMaxSize(), Arrangement.Bottom) {
                ButtonsStateDelivered()
            }
        }
    }
}

@Composable
fun ZoneDataDetail() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Datos de la entrega"
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Row {
            Icon(
                painter = painterResource(id = R.drawable.carbon_enterprise),
                contentDescription = null,
                modifier = Modifier.padding(end = 7.dp)
            )
            Text(text = "Empresa:")
        }
        Row {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Fritz Sports",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = 21.dp)
                )
                Box(modifier = Modifier
                    .padding(end = 40.dp)
                    .align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.vector__1_),
                        contentDescription = null,
                        tint = Color(0xFF5CEE3C)
                    )
                }
                Box(modifier = Modifier.align(Alignment.TopEnd)){
                    Image(
                        painter = painterResource(id = R.drawable.ic_phone),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
        Row() {
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
                text = "Av. la marina 245 curze con choristar",
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
                text = "Refereccion"
            )
            Text(
                text = "Fuente al parque de la libertad",
                fontWeight = FontWeight.SemiBold
            )
            Spacer(
                modifier = Modifier
                    .height(3.dp)
            )
            Text(
                text = "Producto:"
            )
            Text(
                text = "Zapatillas nike",
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
            Text(text = "4", fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun ZonesButton() {
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
            Text(
                text = "Metodo de Pago"
            )
            Text(
                text = "efectivo / Transferencia / Plin / Yape",
                fontWeight = FontWeight.SemiBold
            )
            Spacer(
                modifier = Modifier.height(2.dp)
            )
            Text(
                text = "Cuentas para las transferencias"
            )
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
                    .width(135.dp)
                    .height(35.dp)
            ) {
                Text(text = "Ver Cuentas")
            }
            //ExpressDialogScreen(show = show) { show = false }
        }
    }
}
@Composable
fun ButtonsStateDelivered() {
    Row(modifier = Modifier.padding(15.dp)) {
        TextButton(
            onClick = {},
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