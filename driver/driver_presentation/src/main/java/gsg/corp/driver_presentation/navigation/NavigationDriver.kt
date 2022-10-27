package gsg.corp.driver_presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import gsg.corp.core_ui.navigation.NavigationRouteDriver
import gsg.corp.core_ui.navigation.NavigationRouteModule
import gsg.corp.core_ui.navigation.ROUTE_ID
import gsg.corp.driver_presentation.screens.dashboard.DashBoardScreen
import gsg.corp.driver_presentation.screens.route.RouteScreen
import gsg.corp.driver_presentation.screens.route_detail.RouteDetailScreen

object NavigationDriver {
    fun NavGraphBuilder.navigateDriver(navController: NavHostController) {
        navigation(
            route = NavigationRouteModule.ModuleDriver.route,
            startDestination = NavigationRouteDriver.DashBoard.route

        ) {
            composable(
                route = NavigationRouteDriver.DashBoard.route
            ) {
                DashBoardScreen(navController,onNextClick = { navController.navigate(NavigationRouteDriver.Route.route) })
            }

            composable(
                route = NavigationRouteDriver.Route.route
            ) {
                RouteScreen(onGoDetail = { id ->
                    navController.navigate(NavigationRouteDriver.RouteDetail.passId(id))
                })
            }

            composable(
                route = NavigationRouteDriver.RouteDetail.route,
                arguments = listOf(
                    navArgument(ROUTE_ID) {
                        type = NavType.IntType
                        defaultValue = 0
                    }
                )
            ) {
                //val id = it.arguments?.getInt(ROUTE_DETAIL_ID)!!
                RouteDetailScreen()
            }
        }
    }

}