package gsg.corp.driver_data.mapper

import gsg.corp.driver_data.remote.dto.route.*
import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_domain.model.RouteDetail
import gsg.corp.driver_domain.model.RouteType

fun RoutesDto.toRoutes(): List<Route> {

    return routes.map { r->
         Route(
            address = r.address,
            address_reference = r.address_reference,
            advance = r.advance,
            code_pay_method = r.code_pay_method,
            code_tracking = r.code_tracking,
            cost = r.cost,
            date_route = r.date_route,
            district = r.district,
            driver = r.driver,
            full_name = r.full_name,
            id = r.id,
            id_provider = r.id_provider,
            name_business = r.name_business,
            number_packages = r.number_packages,
            other_telephone = r.other_telephone,
            pay_amount = r.pay_amount,
            product = r.product,
            size = r.size,
            st_code = r.st_code,
            telephone = r.telephone
        )
    }
}

fun RouteTypeDto.toRoutesTypes(): List<RouteType> {
    return routes.map { r ->
        RouteType(
            codeTracking = r.code_tracking,
            clientAddressDistrict = r.client_address_district,
            clientFullName = r.client_full_name,
            clientPhoneFirst = r.client_telephone_1
        )
    }
}

fun ResponseRouteDetailDto.toRouteDetail(): RouteDetail {
    return RouteDetail(
        address = route.address,
        address_reference = route.address_reference,
        advance = route.advance,
        code_pay_method = route.code_pay_method,
        code_tracking = route.code_tracking,
        cost = route.cost,
        date_route = route.date_route,
        district = route.district,
        driver = route.driver,
        full_name = route.full_name,
        id = route.id,
        id_provider = route.id_provider,
        name_business = route.name_business,
        number_packages = route.number_packages,
        other_telephone = route.other_telephone,
        pay_amount = route.pay_amount,
        product = route.product,
        size = route.size,
        st_code = route.st_code,
        telephone = route.telephone
    )
}