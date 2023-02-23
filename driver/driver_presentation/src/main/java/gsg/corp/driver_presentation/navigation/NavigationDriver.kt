package gsg.corp.driver_presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import gsg.corp.core_ui.navigation.NavigationRouteDriver
import gsg.corp.core_ui.navigation.NavigationRouteModule
import gsg.corp.driver_presentation.screens.BottomNavigationScreen
import gsg.corp.driver_presentation.screens.routes.routes_detail.CollectionScreen
import gsg.corp.driver_presentation.screens.routes.routes_detail.ExpressScreen
import gsg.corp.driver_presentation.screens.routes.routes_detail.ZoneScreen
import gsg.corp.driver_presentation.screens.routes.routes_detail.undelivered.ExpressUndeliveredScreen


object NavigationDriver {
    fun NavGraphBuilder.navigateDriver(navController: NavHostController) {
        navigation(
            route = NavigationRouteModule.ModuleDriver.route,
            startDestination = NavigationRouteDriver.BottomNavigation.route

        ) {
            composable(
                route = NavigationRouteDriver.BottomNavigation.route
            ) {
                BottomNavigationScreen(
                    onCollectionScreen = { navController.navigate(NavigationRouteDriver.CardButtonNavHarvest.route) },
                    onExpressScreen = { navController.navigate(NavigationRouteDriver.CardButtonNavExpress.route) },
                    onZonesScreen = { navController.navigate(NavigationRouteDriver.CardButtonNavZones.route) }
                )
            }
            composable(
                route = NavigationRouteDriver.CardButtonNavHarvest.route
            ) {
                CollectionScreen(
                    onNoCollectedScreen = { navController.navigate(NavigationRouteDriver.NoCollectedNav.route) }
                )
            }
            composable(
                route = NavigationRouteDriver.CardButtonNavExpress.route
            ) {
                ExpressScreen(
                    onUndeliveredScreen = { navController.navigate(NavigationRouteDriver.UndeliveredNav.route) }
                )
            }
            composable(
                route = NavigationRouteDriver.CardButtonNavZones.route
            ) {
                ZoneScreen(
                    onUndeliveredScreen = { navController.navigate(NavigationRouteDriver.UndeliveredNav.route) }
                )
            }
            composable(
                route = NavigationRouteDriver.NoCollectedNav.route
            ) {

            }
            composable(
                route = NavigationRouteDriver.UndeliveredNav.route
            ) {
                ExpressUndeliveredScreen()
            }
        }
    }
}

//   composable(
//                route = NavigationRouteDriver.Home.route
//            ) {
//                DashBoardScreen(navController,onNextClick = { navController.navigate(NavigationRouteDriver.Routes.route) })
//            }
//
//            composable(
//                route = NavigationRouteDriver.Routes.route
//            ) {
//                RouteScreen(onGoDetail = { id ->
//                    navController.navigate(NavigationRouteDriver.RouteDetail.passId(id))
//                })
//            }
//
//            composable(
//                route = NavigationRouteDriver.RouteDetail.route,
//                arguments = listOf(
//                    navArgument(ROUTE_ID) {
//                        type = NavType.IntType
//                        defaultValue = 0
//                    }
//                )
//            ) {
//                //val id = it.arguments?.getInt(ROUTE_DETAIL_ID)!!
//                RouteDetailScreen()
//            }