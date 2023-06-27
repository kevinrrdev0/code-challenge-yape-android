package gsg.corp.ruterito

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import gsg.corp.core.domain.preferences.Preferences
import gsg.corp.core_ui.navigation.NavigationRouteModule
import gsg.corp.driver_presentation.navigation.NavigationDriver.navigateDriver
import gsg.corp.onboarding_presentation.navigation.NavigationOnBoarding.navigateOnBoarding
import gsg.corp.ruterito.ui.theme.RuteritoTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RuteritoTheme {
                val navController = rememberNavController()
                val startDestination = NavigationRouteModule.ModuleOnBoarding.route
                NavHost(
                    navController = navController,
                    route= NavigationRouteModule.ModuleRoot.route,
                    startDestination = startDestination
                )
                {
                    navigateOnBoarding(navController = navController)
                }
            }
        }
    }
}


