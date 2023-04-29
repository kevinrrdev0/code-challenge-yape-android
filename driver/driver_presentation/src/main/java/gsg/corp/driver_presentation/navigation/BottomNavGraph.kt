package gsg.corp.driver_presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import gsg.corp.core_ui.navigation.NavigationRouteDriver
import gsg.corp.driver_presentation.screens.home.HomeScreen
import gsg.corp.driver_presentation.screens.news.NewsScreen
import gsg.corp.driver_presentation.screens.profile.ProfileScreen
import gsg.corp.driver_presentation.screens.routesv2.RouteScreen
import gsg.corp.driver_presentation.screens.routesv2.RouteViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    onClickRouteDetail : (Int)->Unit,
    onClickUpdateStatus:(Int) ->Unit
) {
    NavHost(
        navController = navController,
        route = NavigationRouteDriver.BottomNavigation.route,
        startDestination = NavigationRouteDriver.BottomNavHome.route
    ) {

        composable(
            route = NavigationRouteDriver.BottomNavHome.route
        ) {
            HomeScreen()
        }
        composable(
            route = NavigationRouteDriver.BottomNavRoutes.route
        ) {
            val viewModel = hiltViewModel<RouteViewModel>()
            val state = viewModel.state

            LaunchedEffect(Unit) {
                viewModel.getRoutes()
            }

            RouteScreen(
                state,
                onEvent = viewModel::onEvent,
                onGoDetail = {
                    onClickRouteDetail(it)
                },
                onGoUpdateStatus = {
                    onClickUpdateStatus(it)
                }
            )
        }
        composable(
            route = NavigationRouteDriver.BottomNavNews.route
        ) {
            NewsScreen()
        }
        composable(
            route = NavigationRouteDriver.BottomNavProfile.route
        ) {
            ProfileScreen()
        }



    }
}