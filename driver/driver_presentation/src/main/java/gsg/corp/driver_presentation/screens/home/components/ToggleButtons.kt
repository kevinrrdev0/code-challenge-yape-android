package gsg.corp.driver_presentation.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import gsg.corp.core.R
import gsg.corp.core_ui.ColorTextButton
import gsg.corp.core_ui.ColorWhite
import gsg.corp.core_ui.RedGsg
import gsg.corp.core_ui.navigation.NavigationRouteDriver

@Composable
fun ToggleButtons(
    navController: NavHostController
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val(OneRoutes, TwoRoutes , ThreeRoutes) = createRefs()
        Box(
            modifier = Modifier
                .constrainAs(OneRoutes) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(TwoRoutes.start)
                }
        ){
            val selectToday =  NavigationRouteDriver.BottomNavToday.route == backStackEntry.value?.destination?.route

            val backgroundToday = if (selectToday) RedGsg else ColorWhite
            val textColorToday = if (selectToday) ColorWhite else ColorTextButton

            Button(
                onClick = {
                    navController.navigate(NavigationRouteDriver.BottomNavToday.route)
                },
                border = BorderStroke(1.dp, Color(0xFF79747E)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = backgroundToday
                ),
                shape = RoundedCornerShape(
                    50,
                    0,
                    0,
                    50
                ),
            ) {
                Icon(
                    painterResource(id = R.drawable.ic_baseline_check_24),
                    contentDescription = null, modifier = Modifier.size(17.dp),
                    tint = Color.White
                )
                Text(text = NavigationRouteDriver.BottomNavToday.name, color = textColorToday)
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(TwoRoutes){
                    top.linkTo(parent.top)
                    start.linkTo(OneRoutes.end)
                    end.linkTo(ThreeRoutes.start)
                }
        ){
            val selectWeekly = NavigationRouteDriver.BottomNavWeekly.route == backStackEntry.value?.destination?.route

            val background = if (selectWeekly) RedGsg else ColorWhite
            val textColor = if (selectWeekly) ColorWhite else ColorTextButton

            Button(
                onClick = {
                    navController.navigate(NavigationRouteDriver.BottomNavWeekly.route)
                },
                border = BorderStroke(1.dp, Color(0xFF79747E)),
                shape = RoundedCornerShape(
                    0,
                    0,
                    0,
                    0
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = background,
                ),
            ){
                Text(text = NavigationRouteDriver.BottomNavWeekly.name, color = textColor)
            }
        }
        Box(
            modifier = Modifier
                .constrainAs(ThreeRoutes){
                    top.linkTo(parent.top)
                    start.linkTo(TwoRoutes.end)
                    end.linkTo(parent.end)
                }
        ){

            val selectMonthly = NavigationRouteDriver.BottomNavMonthly.route == backStackEntry.value?.destination?.route

            val background = if (selectMonthly) RedGsg else ColorWhite
            val textColor = if (selectMonthly) ColorWhite else ColorTextButton

            TextButton(
                onClick = {
                    navController.navigate(NavigationRouteDriver.BottomNavMonthly.route)
                },
                border = BorderStroke(1.dp, Color(0xFF79747E)),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    disabledElevation = 0.dp
                ),
                shape = RoundedCornerShape(
                    0,
                    50,
                    50,
                    0
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = background,
                ),
            ) {
                Text(text = NavigationRouteDriver.BottomNavMonthly.name, color = textColor)
            }
        }
        createHorizontalChain(OneRoutes, TwoRoutes , ThreeRoutes , chainStyle = ChainStyle.Packed)
    }
}
