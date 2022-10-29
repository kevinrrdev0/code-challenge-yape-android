package gsg.corp.driver_presentation.screens.dashboard

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import gsg.corp.core.util.UiEvent
import gsg.corp.core_ui.Orange
import gsg.corp.core_ui.RedGsg
import gsg.corp.driver_presentation.screens.dashboard.components.BottomNavItem

@Composable
fun DashBoardScreen(
    navController: NavHostController,
    onNextClick: () -> Unit,
    viewModel: DashBoardViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                else -> Unit
            }
        }
    }

    Scaffold(bottomBar = {
        BottomNavigationBar(items = listOf(
            BottomNavItem(
                "Inicio",
                "route",
                Icons.Outlined.Home
            ),
            BottomNavItem(
                "Rutas",
                "route",
                Icons.Outlined.LocationOn
            ),
            BottomNavItem(
                "Noticias",
                "route",
                Icons.Outlined.Newspaper
            ),
            BottomNavItem(
                "Perfil",
                "route",
                Icons.Outlined.Person
            )

        ), navController = navController, onItemClick = {
            navController.navigate(it.route)
        })
    }) {
        Column(modifier = Modifier.padding(it.calculateBottomPadding())) {
            BodyDashBoard()
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
            Log.d("xd", "BottomNavigationBar: backStackEntry")
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(selected = selected, onClick = { onItemClick(item) },
                selectedContentColor = Orange, unselectedContentColor = Color.White, icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
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

@Composable
fun BodyDashBoard() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            TitleDashBoard("Kevin")
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                CardButtonDashBoard(
                    "Recojos",
                    Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .clickable { },
                    Icons.Outlined.MapsHomeWork
                )
                CardButtonDashBoard(
                    "Envios",
                    Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .clickable {
                            //                        viewModel.onNavigationClick(1)
                        },
                    Icons.Outlined.TwoWheeler
                )
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                CardButtonDashBoard(
                    "Express",
                    Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .clickable {
                            //                        viewModel.onNavigationClick(1)
                        },
                    Icons.Outlined.LocationOn
                )
                CardButtonDashBoard(
                    "Empresarial",
                    Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .clickable {
                            //                        viewModel.onNavigationClick(1)
                        },
                    Icons.Outlined.BusinessCenter
                )
            }
        }
    }
}

@Composable
fun TitleDashBoard(name: String) {
    Text(
        text = "¡Bienvenido, ${name}!",
        style = MaterialTheme.typography.h5,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    )
}

@Composable
fun CardButtonDashBoard(text: String, modifier: Modifier, icon: ImageVector) {
    Card(
        modifier = modifier,
        backgroundColor = RedGsg,
        elevation = 8.dp
    )//shape = RoundedCornerShape(8.dp)
    {
        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                Modifier.padding(0.dp)
            )
            Text(text = text, Modifier.padding(start = 4.dp))
        }
    }
}


@Preview(widthDp = 500, heightDp = 400, showBackground = true)
@Composable
fun CardRoute() {
    Column {

        BodyDashBoard()

    }


}