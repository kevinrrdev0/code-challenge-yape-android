package gsg.corp.driver_data.mapper

import gsg.corp.driver_data.remote.dto.route.RouteDetailDto
import gsg.corp.driver_data.remote.dto.route.RouteDto
import gsg.corp.driver_data.remote.dto.route.RouteTypeDto
import gsg.corp.driver_data.remote.dto.route.Routes
import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_domain.model.RouteDetail
import gsg.corp.driver_domain.model.RouteType

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

fun RouteDetailDto.toRouteDetail(): RouteDetail {
    return RouteDetail(
        id = route.id,
        codeTracking = route.code_tracking,
        idBusiness = route.id_business,
        idRouteType = route.id_route_type,
        codeCollectPoint = route.code_collect_point,
        clientFullName = route.client_full_name,
        clientPhoneFirst = route.client_telephone_1,
        clientPhoneSecond = route.client_telephone_2,
        clientAddress = route.client_address,
        clientAddressReference = route.client_address_reference,
        clientAddressDistrict = route.client_address_district,
        clientAddressComments = route.client_address_comments,
        dateRegister = route.date_register,
        dateRoute = route.date_route,
        product = route.product,
        productDetail = route.product_detail,
        numberPackages = route.number_packages,
        numberProducts = route.number_products,
        codePayMethod = route.code_pay_method,
        payAmount = route.pay_amount,
        payComments = route.pay_comments,
        flkActive = route.flk_active
    )
}