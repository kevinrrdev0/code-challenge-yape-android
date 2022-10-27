package gsg.corp.onboarding_data.remote

import gsg.corp.core.data.network.BaseResponse
import gsg.corp.onboarding_data.remote.dto.login.LoginDto
import gsg.corp.onboarding_data.remote.request.VerificationRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OnBoardingApi {

    @POST(LOGIN_ROUTE_V2)
    suspend fun verificationUser(@Body verificationRequest: VerificationRequest): Response<BaseResponse<LoginDto>>

    companion object {
        const val LOGIN_ROUTE_V2 = "auth/login"
    }
}