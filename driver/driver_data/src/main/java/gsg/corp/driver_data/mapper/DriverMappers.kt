package gsg.corp.driver_data.mapper

import gsg.corp.driver_data.remote.dto.route.*
import gsg.corp.driver_data.remote.request.MetadataRequest
import gsg.corp.driver_domain.model.Route
import gsg.corp.driver_domain.model.RouteDetail
import gsg.corp.driver_domain.model.RoutePayment
import gsg.corp.driver_domain.model.RouteStateRequest
import gsg.corp.driver_domain.model.RouteType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun RoutesDto.toRoutes(): List<Route> {

    return routes.map { r ->
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
            observation = r.observation,
            id = r.id,
            id_provider = r.id_provider,
            name_business = r.name_business,
            number_packages = r.number_packages,
            other_telephone = r.other_telephone,
            pay_amount = r.pay_amount,
            product = r.product,
            productDetail = r.product_detail,
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
        addressReference = route.address_reference,
        advance = route.advance,
        codePayMethod = route.code_pay_method,
        codeTracking = route.code_tracking,
        cost = route.cost,
        dateRoute = route.date_route,
        district = route.district,
        driver = route.driver,
        fullName = route.full_name,
        id = route.id,
        idProvider = route.id_provider,
        nameBusiness = route.name_business,
        numberPackages = route.number_packages,
        otherTelephone = route.other_telephone,
        payAmount = route.pay_amount,
        product = route.product,
        size = route.size,
        stId = route.st_id,
        stCode = route.st_code,
        telephone = route.telephone,
        pathPhotoState = route.path_photo_state,
        otherPathPhotoState = route.other_path_photo_state,
        dateRescheduled = route.date_rescheduled,
        comment = route.comment,
        RoutePayments = route.RoutePayments.map {
            RoutePayment(
                it.code_pay_method,
                it.flk_pay_gsg,
                it.id_route,
                it.id_route_payment,
                it.other_path_photo_pay,
                it.path_photo_pay,
                it.pay_amount,
                it.pay_detail
            )
        }

    )
}

fun RouteStateRequest.toMetadataRequest(): MetadataRequest {
    val dateRescheduleFinal = if (dateRescheduled.isNotEmpty()) {
        // Convertir la cadena en un objeto LocalDate
        val date = LocalDate.parse(dateRescheduled, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        // Formatear la fecha en la cadena deseada
        val formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        formattedDate
    } else {
        dateRescheduled
    }


    return MetadataRequest(
        id_route = idRoute,
        id_user = idUser,
        id_state_tracking = idStateTracking,
        comment = comment,
        date_rescheduled = dateRescheduleFinal,
        RoutePayments = routesPayments
    )
}