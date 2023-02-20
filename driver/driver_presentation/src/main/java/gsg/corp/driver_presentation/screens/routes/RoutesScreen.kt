package gsg.corp.driver_presentation.screens.routes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import gsg.corp.core_ui.ColorGray
import gsg.corp.core_ui.ColorTextButton
import gsg.corp.core_ui.ColorWhite
import gsg.corp.core_ui.RedGsg
import gsg.corp.core_ui.navigation.NavigationRouteDriver
import gsg.corp.driver_presentation.screens.routes.navigation.ButtonsRoutesNavGraph

@Composable
fun RoutesScreen(
    viewModel: RoutesViewModel = hiltViewModel()
) {

    viewModel.onGetRoutesTypes()

    val routesList = viewModel.routeType.value
    val navController = rememberNavController()

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.size(15.dp))
        ButtonRoutes(navController)
        Spacer(modifier = Modifier.size(15.dp))
        LazyColumn() {
            itemsIndexed(
                items = routesList
            ) {index, r ->
                CardsRoutes(
                    ruteText = r.codeTracking,
                    district = r.clientAddressDistrict,
                    client = r.clientFullName,
                    cell = r.clientPhoneFirst,
                    onClick = {navController.navigate(NavigationRouteDriver.CardButtonNavHarvest.route)}
                )
            }
        }
        ButtonsRoutesNavGraph(navController = navController)
    }
}
@Composable
fun CardsRoutes(
    ruteText: String,
    district: String,
    client: String,
    cell: String,
    onClick: () -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(130.dp)
        .padding(11.dp),
        elevation = 7.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = ColorGray)
        ) {
            Icon(
                painter = painterResource(id = gsg.corp.driver_presentation.R.drawable.group_2 ),
                contentDescription = null ,
                modifier = Modifier
                    .size(90.dp)
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight()
                    .padding(top = 15.dp)
            ) {
                Text(text = "Rutas: $ruteText", fontWeight = FontWeight.SemiBold)
                Text(text = "Distrito: $district")
                Text(text = "Cliente: $client")
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = "Tlf: $cell")
            }
            Column(
                Modifier
                    .align(Alignment.CenterVertically)) {
                Icon(
                    painter = painterResource(id = gsg.corp.driver_presentation.R.drawable.ic_baseline_content_copy_24 ),
                    contentDescription = null ,
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.End)
                        .clickable { }
                )
            }
            Column(
                Modifier
                    .align(Alignment.CenterVertically)){
                IconButton(onClick = onClick, modifier = Modifier.align(Alignment.End)) {
                    Icon(
                        painter = painterResource(id = gsg.corp.driver_presentation.R.drawable.vector ),
                        contentDescription = null ,
                        modifier = Modifier
                            .padding()
                            .size(28.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ButtonRoutes( navController: NavHostController) {

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