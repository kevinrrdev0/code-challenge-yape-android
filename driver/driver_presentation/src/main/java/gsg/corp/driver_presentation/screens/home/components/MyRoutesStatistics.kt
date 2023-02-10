package gsg.corp.driver_presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MyRoutesStatistics() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Mis Rutas(30)",
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB0B0B1)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
    Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
        Column {
            Text(text = "Entregas : 21")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Zonificado: 16")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Pendientes: 3")
        }
        Column() {
            Text(text = "No Entregados: 0")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Express: 5")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Reprogram : 2")
        }
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp, top = 15.dp), ) {
        Divider(
            Modifier
                .height(1.dp)
                .background(
                    Color(0xFFA5A4A4)
                )
        )
    }
}