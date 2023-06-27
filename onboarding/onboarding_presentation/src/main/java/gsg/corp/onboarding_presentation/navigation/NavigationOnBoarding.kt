package gsg.corp.onboarding_presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.android.gms.maps.model.LatLng
import gsg.corp.core_ui.navigation.LAT
import gsg.corp.core_ui.navigation.LNG
import gsg.corp.core_ui.navigation.NAME_RECIPE
import gsg.corp.core_ui.navigation.NavigationRouteModule
import gsg.corp.core_ui.navigation.NavigationRouteOnBoarding
import gsg.corp.core_ui.navigation.RECIPE_ID
import gsg.corp.onboarding_presentation.screens.map.MapScreen
import gsg.corp.onboarding_presentation.screens.recipes.RecipeScreen
import gsg.corp.onboarding_presentation.screens.recipes.RecipeViewModel
import gsg.corp.onboarding_presentation.screens.recipes.detail.RecipeDetailScreen
import gsg.corp.onboarding_presentation.screens.recipes.detail.RecipeDetailViewModel

object NavigationOnBoarding {
    fun NavGraphBuilder.navigateOnBoarding(
        navController: NavHostController
    ) {
        navigation(
            route = NavigationRouteModule.ModuleOnBoarding.route,
            startDestination = NavigationRouteOnBoarding.Recipe.route
        ) {


            composable(
                route = NavigationRouteOnBoarding.Recipe.route
            ) {
                val viewModel = hiltViewModel<RecipeViewModel>()
                val state = viewModel.state

                RecipeScreen(state = state,
                    onEvent = viewModel::onEvent,
                    onClickDetail = { id ->
                        navController.navigate(NavigationRouteOnBoarding.RecipeDetail.passId(id))
                    }
                )
            }

            composable(
                route = NavigationRouteOnBoarding.RecipeDetail.route,
                arguments = listOf(
                    navArgument(RECIPE_ID) {
                        type = NavType.IntType
                        defaultValue = 0
                    }
                )
            ) {
                val viewModel = hiltViewModel<RecipeDetailViewModel>()
                val state = viewModel.state
                RecipeDetailScreen(state, onNavigateMap = { lat, lng, nameRecipe ->
                    navController.navigate(
                        NavigationRouteOnBoarding.RecipeMapWithLatLng.passLatLng(
                            lat,
                            lng,
                            nameRecipe
                        )
                    )
                }, onNavigateUp = {
                    navController.popBackStack()
                }
                )
            }

            composable(route = NavigationRouteOnBoarding.RecipeMapWithLatLng.route,
                arguments = listOf(
                    navArgument(LAT) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument(LNG) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument(NAME_RECIPE) {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )) {
                val lat = it.arguments?.getString(LAT)!!
                val lng = it.arguments?.getString(LNG)!!
                val nameRecipe = it.arguments?.getString(NAME_RECIPE)!!
                MapScreen(
                    LatLng(lat.toDouble(), lng.toDouble()),
                    nameRecipe = nameRecipe,
                    onNavigateUp = {
                        navController.popBackStack()
                    })
            }


        }

    }
}