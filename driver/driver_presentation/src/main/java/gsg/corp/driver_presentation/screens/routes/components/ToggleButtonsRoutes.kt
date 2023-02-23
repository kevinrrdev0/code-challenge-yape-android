package gsg.corp.driver_presentation.screens.routes.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import gsg.corp.core_ui.ColorTextButton
import gsg.corp.core_ui.ColorWhite
import gsg.corp.core_ui.RedGsg
import gsg.corp.core_ui.navigation.NavigationRouteDriver
import gsg.corp.driver_presentation.R

@Composable
fun ToggleButtonsRoutes( navController: NavHostController) {

    val backStackEntry = navController.currentBackStackEntryAsState()

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val(OneRoutes, TwoRoutes , ThreeRoutes, IconMap) = createRefs()
        Box(
            modifier = Modifier
                .height(35.dp)
                .constrainAs(OneRoutes) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(TwoRoutes.start)
                }
        ){
            val selectHarvest =  NavigationRouteDriver.BottomNavHarvest.route == backStackEntry.value?.destination?.route
            val backgroundHarvest = if (selectHarvest) RedGsg else ColorWhite
            val textColorHarvest = if (selectHarvest) ColorWhite else ColorTextButton
            Button(
                onClick =  {
                    navController.navigate(NavigationRouteDriver.BottomNavHarvest.route)
                },
                border = BorderStroke(1.dp, Color(0xFF79747E)),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = backgroundHarvest
                ),
                shape = RoundedCornerShape(
                    50,
                    0,
                    0,
                    50
                ),
            ) {
                Text(text = NavigationRouteDriver.BottomNavHarvest.name, color = textColorHarvest)
            }
        }

        Box(
            modifier = Modifier
                .height(35.dp)
                .constrainAs(TwoRoutes) {
                    top.linkTo(parent.top)
                    start.linkTo(OneRoutes.end)
                    end.linkTo(ThreeRoutes.start)
                }
        ){
            val selectExpress =  NavigationRouteDriver.BottomNavExpress.route == backStackEntry.value?.destination?.route

            val backgroundExpress = if (selectExpress) RedGsg else ColorWhite
            val textColorExpress = if (selectExpress) ColorWhite else ColorTextButton
            Button(
                onClick = {
                    navController.navigate(NavigationRouteDriver.BottomNavExpress.route)
                },
                border = BorderStroke(1.dp, Color(0xFF79747E)),
                shape = RoundedCornerShape(
                    0,
                    0,
                    0,
                    0
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = backgroundExpress,
                ),
            ){
                Text(text = NavigationRouteDriver.BottomNavExpress.name , color = textColorExpress)
            }
        }
        Box(
            modifier = Modifier
                .height(35.dp)
                .constrainAs(ThreeRoutes) {
                    top.linkTo(parent.top)
                    start.linkTo(TwoRoutes.end)
                    end.linkTo(IconMap.start)
                }
        ){
            val selectZones =  NavigationRouteDriver.BottomNavZones.route == backStackEntry.value?.destination?.route

            val backgroundZones = if (selectZones) RedGsg else ColorWhite
            val textColorZones = if (selectZones) ColorWhite else ColorTextButton
            TextButton(
                onClick = {
                    navController.navigate(NavigationRouteDriver.BottomNavZones.route)
                },
                border = BorderStroke(1.dp, Color(0xFF79747E)),
                shape = RoundedCornerShape(
                    0,
                    50,
                    50,
                    0
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = backgroundZones,
                ),
            ) {
                Text(text = NavigationRouteDriver.BottomNavZones.name, color = textColorZones)
            }
        }
        Box(modifier = Modifier
            .width(41.dp)
            .height(32.dp)
            .constrainAs(IconMap) {
                top.linkTo(parent.top)
                start.linkTo(ThreeRoutes.end)
                end.linkTo(parent.end)
            }
        ) {
            Icon(
                painter = painterResource(id = gsg.corp.driver_presentation.R.drawable.group),
                contentDescription = null,
                modifier = Modifier

            )
        }
        createHorizontalChain(OneRoutes, TwoRoutes , ThreeRoutes ,IconMap, chainStyle = ChainStyle.Packed)
    }
}