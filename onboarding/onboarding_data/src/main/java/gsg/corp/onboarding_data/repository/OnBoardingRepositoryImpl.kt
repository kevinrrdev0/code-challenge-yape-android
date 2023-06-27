package gsg.corp.onboarding_data.repository

import gsg.corp.core.data.network.BaseNetwork
import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.core.util.ConnectionUtils
import gsg.corp.core.util.UiText
import gsg.corp.onboarding_data.mapper.toRecipeDomain
import gsg.corp.onboarding_data.mapper.toRecipesDomain
import gsg.corp.onboarding_data.mapper.toUserInfo
import gsg.corp.onboarding_data.remote.OnBoardingApi
import gsg.corp.onboarding_data.remote.request.VerificationRequest
import gsg.corp.onboarding_domain.model.Recipe
import gsg.corp.onboarding_domain.model.UserInfo
import gsg.corp.onboarding_domain.repository.OnBoardingRepository

class OnBoardingRepositoryImpl(private val api: OnBoardingApi, connectionUtils: ConnectionUtils) :
    OnBoardingRepository, BaseNetwork(connectionUtils) {

    override suspend fun verificationUser(user: String, password: String): Resource<UserInfo> {
        return try {
            executeWithConnection {
                val loginDto = api.verificationUser(VerificationRequest(user, password))
                if (loginDto.isSuccessful) {
                    // get headers
                    val headers = loginDto.headers().get("Set-Cookie")
                    val headerValues = headers?.split(";")
                    var token: String? = null
                    if (headerValues != null) {
                        for (value in headerValues) {
                            if (value.startsWith("TokenGSG=")) {
                                token = value.substringAfter("=")
                                break
                            }
                        }
                    }
                    Resource.Success(data = loginDto.body()?.data?.toUserInfo(token?:""))
                } else {
                    val errorMessage =
                        loginDto.errorBody()?.string()?.let {
                            parseException(
                                it
                            )?.message?.description
                        } ?: run {
                            "Error server"
                        }
                    Resource.Error(
                        message = UiText.DynamicString(errorMessage)
                    )
                }
            }
        } catch (e: Exception) {
            Resource.Error(message = UiText.DynamicString(getConnectionException(e)))
        }
    }

    override suspend fun getFoodRecipes(): Resource<List<Recipe>> {
        return try {
            executeWithConnection {
                val recipesDto = api.getFoodRecipes()
                if (recipesDto.isSuccessful) {

                    Resource.Success(data = recipesDto.body()?.data?.toRecipesDomain())
                } else {
                    val errorMessage =
                        recipesDto.errorBody()?.string()?.let {
                            parseException(
                                it
                            )?.message?.description
                        } ?: run {
                            "Error server"
                        }
                    Resource.Error(
                        message = UiText.DynamicString(errorMessage)
                    )
                }
            }
        } catch (e: Exception) {
            Resource.Error(message = UiText.DynamicString(getConnectionException(e)))
        }
    }

    override suspend fun getRecipeDetail(idRecipe:Int): Resource<Recipe> {
        return try {
            executeWithConnection {
                val recipesDto = api.getRecipeDetail(idRecipe)
                if (recipesDto.isSuccessful) {

                    Resource.Success(data = recipesDto.body()?.data?.toRecipeDomain())
                } else {
                    val errorMessage =
                        recipesDto.errorBody()?.string()?.let {
                            parseException(
                                it
                            )?.message?.description
                        } ?: run {
                            "Error server"
                        }
                    Resource.Error(
                        message = UiText.DynamicString(errorMessage)
                    )
                }
            }
        } catch (e: Exception) {
            Resource.Error(message = UiText.DynamicString(getConnectionException(e)))
        }
    }
}