package gsg.corp.driver_data.mapper

import gsg.corp.driver_data.remote.dto.route.RouteDto
import gsg.corp.driver_data.remote.dto.route.Routes
import gsg.corp.driver_domain.model.Route

fun RouteDto.toRoutes(): List<Route> {

    return routes.map { r->
        Route(
        id = r.id,
        route_type = r.route_type,
        driver = r.driver,
        telephone1 = r.telephone1,
        client = r.client,
        cel1 = r.cel1,
        cel2 = r.cel2,
        district = r.district,
        address = r.address,
        address_ref = r.address_ref,
        product_name = r.product_name,
        product_detail = r.product_detail,
        pay_method = r.pay_method,
        amount_collect = r.amount_collect,
        detail_payment = r.detail_payment,
        date_route = r.date_route,
        state = r.state,
        cost_service = r.cost_service
        )
    }
}