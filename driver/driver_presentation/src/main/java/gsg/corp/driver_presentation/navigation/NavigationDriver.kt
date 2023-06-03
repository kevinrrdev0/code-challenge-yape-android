package gsg.corp.driver_presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import gsg.corp.core_ui.navigation.NavigationRouteDriver
import gsg.corp.core_ui.navigation.NavigationRouteModule
import gsg.corp.core_ui.navigation.ROUTE_ID
import gsg.corp.driver_presentation.screens.BottomNavigationScreen
import gsg.corp.driver_presentation.screens.routesv2.routes_detail.RouteDetailScreen
import gsg.corp.driver_presentation.screens.routesv2.routes_detail.RouteDetailViewModel
import gsg.corp.driver_presentation.screens.routesv2.update_route.RouteDetailScreenV2
import gsg.corp.driver_presentation.screens.routesv2.update_route.UpdateStateOrderScreen
import gsg.corp.driver_presentation.screens.routesv2.update_route.UpdateStateOrderViewModel


object NavigationDriver {
    fun NavGraphBuilder.navigateDriver(navController: NavHostController) {
        navigation(
            route = NavigationRouteModule.ModuleDriver.route,
            startDestination = NavigationRouteDriver.BottomNavigation.route

        ) {
            composable(
                route = NavigationRouteDriver.BottomNavigation.route
            ) {
                BottomNavigationScreen(onClickRouteDetail = { id ->
                    navController.navigate(NavigationRouteDriver.UpdateStateOrder.passId(id))
                }
                )
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
                val viewModel = hiltViewModel<RouteDetailViewModel>()
                val state = viewModel.state
                RouteDetailScreen(state = state, onEvent = viewModel::onEvent)
            }

            composable(
                route = NavigationRouteDriver.UpdateStateOrder.route,
                arguments = listOf(
                    navArgument(ROUTE_ID) {
                        type = NavType.IntType
                        defaultValue = 0
                    }
                )
            ) {
                val viewModel = hiltViewModel<UpdateStateOrderViewModel>()
                val state = viewModel.state
                val uiEvent = viewModel.uiEvent
                RouteDetailScreenV2(
                    state = state,
                    uiEvent = uiEvent,
                    onEvent = viewModel::onEvent,
                    onNavigateUp = {
                        navController.popBackStack()
                    })
            }

        }
    }
}