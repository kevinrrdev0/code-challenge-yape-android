package gsg.corp.core_ui.navigation

const val ROUTE_MODULE_ROOT = "module_root"
const val ROUTE_MODULE_ONBOARDING= "module_onboarding"
const val ROUTE_MODULE_DRIVER = "module_drive"

sealed class NavigationRouteModule(val route: String) {
    object ModuleRoot : NavigationRouteModule(ROUTE_MODULE_ROOT)
    object ModuleOnBoarding : NavigationRouteModule(ROUTE_MODULE_ONBOARDING)
    object ModuleDriver : NavigationRouteModule(ROUTE_MODULE_DRIVER)
}
