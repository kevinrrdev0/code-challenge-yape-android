package gsg.corp.onboarding_data.remote

import gsg.corp.core.data.network.BaseResponse
import gsg.corp.onboarding_data.remote.dto.login.LoginDto
import gsg.corp.onboarding_data.remote.dto.login.RecipeDto
import gsg.corp.onboarding_data.remote.dto.login.RecipesDto
import gsg.corp.onboarding_data.remote.request.VerificationRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface OnBoardingApi {

    @POST(LOGIN_ROUTE_V2)
    suspend fun verificationUser(@Body verificationRequest: VerificationRequest): Response<BaseResponse<LoginDto>>

    @GET(GET_RECIPES)
    suspend fun getFoodRecipes() : Response<BaseResponse<RecipesDto>>

    @GET(GET_RECIPE_DETAIL)
    suspend fun getRecipeDetail(@Path("id") idRecipe: Int) : Response<BaseResponse<RecipeDto>>

    companion object {
        const val LOGIN_ROUTE_V2 = "auth/login"
        const val GET_RECIPES = "getFoodRecipes"
        const val GET_RECIPE_DETAIL = "recipe/{id}"
    }
}