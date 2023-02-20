package gsg.corp.driver_presentation.screens.routes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import gsg.corp.core_ui.navigation.NavigationRouteDriver
import gsg.corp.driver_presentation.screens.routes.screens.RouteExpressScreen
import gsg.corp.driver_presentation.screens.routes.screens.RoutesCollectionScreen
import gsg.corp.driver_presentation.screens.routes.screens.RoutesZonesScreen

@Composable
fun ButtonsRoutesNavGraph(
    navController: NavHostController,
    onCollectionScreen: () -> Unit,
    onExpressScreen: () -> Unit,
    onZonesScreen: () -> Unit
) {
    NavHost(
        navController = navController,
        route = NavigationRouteDriver.BottomNavRoutes.route,
        startDestination = NavigationRouteDriver.BottomNavHarvest.route
        ){
        composable(
            route = NavigationRouteDriver.BottomNavHarvest.route
        ){
            RoutesCollectionScreen(
                onClick = onCollectionScreen
            )
        }
        composable(
            route = NavigationRouteDriver.BottomNavExpress.route
        ){
            RouteExpressScreen(
                onClick = onExpressScreen
            )
        }
        composable(
            route = NavigationRouteDriver.BottomNavZones.route
        ){
            RoutesZonesScreen(
                onClick = onZonesScreen
            )
        }
    }
}
