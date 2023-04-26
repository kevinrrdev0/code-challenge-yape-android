package gsg.corp.driver_presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import gsg.corp.driver_presentation.screens.home.components.FilterButtons
import gsg.corp.driver_presentation.screens.home.components.MyRoutesStatistics
import gsg.corp.driver_presentation.screens.home.components.ToggleButtons
import gsg.corp.driver_presentation.screens.home.navigation.ToggleButtonsNavGraph

@Preview
@Composable
fun HomeScreen() {

     Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
         Spacer(modifier = Modifier.height(18.dp))
         Username( username = "Daniel Flores")
         Spacer(modifier = Modifier.height(18.dp))
         FilterButtons(
             selectOption = 1
         )
         Spacer(modifier = Modifier.height(29.dp))
     }
}

@Composable
fun Username( username: String) {
    Row() {
        Text(text = "Bienvenido", fontSize = 22.sp)

        Spacer(
            modifier = Modifier.width(5.dp))

        Text(text = username , fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)
    }
}