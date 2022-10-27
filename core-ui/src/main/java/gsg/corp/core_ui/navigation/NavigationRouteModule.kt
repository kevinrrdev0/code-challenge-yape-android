package gsg.corp.core_ui.navigation


sealed class NavigationRouteModule(val route: String) {
    object ModuleOnBoarding : NavigationRouteModule("module_onboarding")
    object ModuleDriver : NavigationRouteModule("module_drive")
}
