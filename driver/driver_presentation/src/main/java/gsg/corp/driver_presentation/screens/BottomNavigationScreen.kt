package gsg.corp.driver_presentation.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import gsg.corp.core_ui.Orange
import gsg.corp.core_ui.RedGsg
import gsg.corp.core_ui.navigation.NavigationRouteDriver
import gsg.corp.driver_presentation.navigation.BottomNavGraph
import gsg.corp.driver_presentation.screens.dashboard.components.BottomNavItem

@Composable
fun BottomNavigationScreen(
    navController: NavHostController = rememberNavController(),
    onClickRouteDetail: (Int) -> Unit
) {
    Scaffold(bottomBar = {
        BottomNavigationBar(items = listOf(
            BottomNavItem(
                NavigationRouteDriver.BottomNavHome.name,
                NavigationRouteDriver.BottomNavHome.route,
                Icons.Outlined.Home
            ),
            BottomNavItem(
                NavigationRouteDriver.BottomNavRoutes.name,
                NavigationRouteDriver.BottomNavRoutes.route,
                Icons.Outlined.LocationOn
            ),
            BottomNavItem(
                NavigationRouteDriver.BottomNavNews.name,
                NavigationRouteDriver.BottomNavNews.route,
                Icons.Outlined.Newspaper
            ),
            BottomNavItem(
                NavigationRouteDriver.BottomNavProfile.name,
                NavigationRouteDriver.BottomNavProfile.route,
                Icons.Outlined.Person
            )
        ), navController = navController, onItemClick = {
            navController.navigate(it.route)

        })
    }) {
        Column(modifier = Modifier.padding(it)) {
            BottomNavGraph(
                navController = navController,
                onClickRouteDetail
            )
        }
    }

}


@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(modifier = modifier, backgroundColor = RedGsg, elevation = 5.dp) {
        items.forEach { item ->

            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(selected = selected, onClick = { onItemClick(item) },
                selectedContentColor = Orange, unselectedContentColor = Color.White, icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgedBox(badge = { Badge { Text(item.badgeCount.toString()) } }) {
                                Icon(imageVector = item.icon, contentDescription = item.name)
                            }
                        } else {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        }
                        if (selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }

                })
        }
    }
}