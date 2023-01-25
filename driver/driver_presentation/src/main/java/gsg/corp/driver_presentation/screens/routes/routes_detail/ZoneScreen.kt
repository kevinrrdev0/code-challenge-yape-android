package gsg.corp.driver_presentation.screens.routes.routes_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
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
            Text(
                text = "FRIS SPORTRS",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 21.dp)
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
                    .padding(start = 15.dp)
            )
            Spacer(
                modifier = Modifier.width(40.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_content_copy_24),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
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