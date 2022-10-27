package gsg.corp.core_ui.navigation

const val ROUTE_ID = "route_id"

sealed class NavigationRouteDriver(val route:String){
    object DashBoard:NavigationRouteDriver("dashboard")
    object Route : NavigationRouteDriver("route")
    object RouteDetail : NavigationRouteDriver("route_detail?$ROUTE_ID={$ROUTE_ID}") {
        fun passId(id: Int): String {
            return "route_detail?$ROUTE_ID=$id"
        }
    }
}
