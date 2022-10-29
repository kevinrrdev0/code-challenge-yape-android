package gsg.corp.driver_presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import gsg.corp.core_ui.navigation.NavigationRouteDriver
import gsg.corp.core_ui.navigation.NavigationRouteModule
import gsg.corp.driver_presentation.screens.BottomNavigationScreen
import gsg.corp.driver_presentation.screens.route.RouteScreen


object NavigationDriver {
    fun NavGraphBuilder.navigateDriver(navController: NavHostController) {
        navigation(
            route = NavigationRouteModule.ModuleDriver.route,
            startDestination = NavigationRouteDriver.BottomNavigation.route

        ) {
            composable(
                route = NavigationRouteDriver.BottomNavigation.route
            ) {
                BottomNavigationScreen()
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