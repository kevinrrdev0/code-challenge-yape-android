package gsg.corp.ruterito

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import gsg.corp.core_ui.navigation.NavigationRouteModule
import gsg.corp.driver_presentation.navigation.NavigationDriver.navigateDriver
import gsg.corp.onboarding_presentation.navigation.NavigationOnBoarding.navigateOnBoarding
import gsg.corp.ruterito.ui.theme.RuteritoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RuteritoTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = NavigationRouteModule.ModuleOnBoarding.route
                )
                {
                    navigateOnBoarding(navController = navController)
                    navigateDriver(navController = navController)
                }
            }
        }
    }
}


