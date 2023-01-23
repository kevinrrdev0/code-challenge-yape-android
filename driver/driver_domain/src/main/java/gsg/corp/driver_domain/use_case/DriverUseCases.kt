package gsg.corp.driver_domain.use_case

data class DriverUseCases(
    val getRoutes: GetRoutes,
    val updateRoute: UpdateRoute,
    val getRoutesTypes: GetRoutesTypes,
    val getRoutesDetail: GetRouteDetail
)
