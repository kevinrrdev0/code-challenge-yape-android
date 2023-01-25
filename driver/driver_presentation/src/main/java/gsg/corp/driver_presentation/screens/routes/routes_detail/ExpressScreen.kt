package gsg.corp.driver_presentation.screens.routes.routes_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gsg.corp.driver_presentation.R

@Preview
@Composable
fun ExpressScreen() {
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
                painter = painterResource(id = R.drawable.vector__3_),
                contentDescription = null,
                modifier = Modifier.padding(end = 7.dp)
            )
            Text(text = "Cliente")
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
            Button(onClick = { /*TODO*/ }, 
                colors = ButtonDefaults
                    .buttonColors
                        (backgroundColor = Color.Red,
                    contentColor = Color.White),
                shape = RoundedCornerShape(25),
                modifier = Modifier
                    .width(90.dp)
                    .height(15.dp)
            ) {
                Text(text = "Ver Cuentas")
            }
        }

    }
}