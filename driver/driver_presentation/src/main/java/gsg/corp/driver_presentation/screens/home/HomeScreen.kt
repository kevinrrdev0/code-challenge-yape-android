package gsg.corp.driver_presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gsg.corp.driver_presentation.screens.home.components.MyRoutesStatistics
import gsg.corp.driver_presentation.screens.home.parts.ToggleButtons

@Preview
@Composable
fun HomeScreen() {
     Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
         Spacer(modifier = Modifier.size(12.dp))
        Username( username = "Daniel Flores")
          Spacer(modifier = Modifier.height(18.dp))
         ToggleButtons(today = "Hoy", week = "Semanal", month = "Mensual")
          Spacer(modifier = Modifier.height(29.dp))
         MyRoutesStatistics()


         Column(
             Modifier
                 .fillMaxWidth()
                 .padding(start = 18.dp, end = 18.dp, top = 15.dp), ) {
             Divider(
                 Modifier
                     .height(1.dp)
                     .background(Color(0xFFA5A4A4)
                     )
             )
         }
     }
}

@Composable
fun Username( username: String) {
    Row() {
        Text(text = "Bienvenido", fontSize = 26.sp)

        Spacer(
            modifier = Modifier.width(12.dp))

        Text(text = username , fontWeight = FontWeight.ExtraBold, fontSize = 26.sp)
    }
}

