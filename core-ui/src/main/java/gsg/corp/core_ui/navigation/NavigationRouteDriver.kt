package gsg.corp.core_ui.navigation

const val ROUTE_ID = "route_id"

sealed class NavigationRouteDriver(val route:String,val name:String=""){
    object BottomNavigation:NavigationRouteDriver("bottom_navigation")
    object BottomNavHome:NavigationRouteDriver("bottom_nav_home","Inicio")
    object BottomNavRoutes:NavigationRouteDriver("bottom_nav_routes","Rutas")
    object BottomNavNews:NavigationRouteDriver("bottom_nav_news","Noticias")
    object BottomNavProfile : NavigationRouteDriver("bottom_nav_profile","Perfil")
    object RouteDetail : NavigationRouteDriver("route_detail?$ROUTE_ID={$ROUTE_ID}") {
        fun passId(id: Int): String {
            return "route_detail?$ROUTE_ID=$id"
        }
    }

    object BottomNavToday: NavigationRouteDriver("bottom_nav_today", "Hoy")
    object BottomNavWeekly: NavigationRouteDriver("bottom_nav_weekly", "Semanal")
    object BottomNavMonthly: NavigationRouteDriver("bottom_nav_monthly", "Mensual")

    object BottomNavHarvest: NavigationRouteDriver("bottom_nav_harvest", "Recolección")
    object BottomNavExpress: NavigationRouteDriver("bottom_nav_express", "Express")
    object BottomNavZones: NavigationRouteDriver("bottom_nav_zones", "Zonas")

    object CardButtonNavHarvest: NavigationRouteDriver("card_button_nav_harvest")
    object CardButtonNavExpress: NavigationRouteDriver("card_button_nav_express")
    object CardButtonNavZones: NavigationRouteDriver("card_button_nav_zones")
}
