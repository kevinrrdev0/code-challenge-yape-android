package gsg.corp.onboarding_domain.repository

import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.onboarding_domain.model.Recipe
import gsg.corp.onboarding_domain.model.UserInfo

interface OnBoardingRepository {
    suspend fun verificationUser(user:String,password:String): Resource<UserInfo>
    suspend fun getFoodRecipes(): Resource<List<Recipe>>
    suspend fun getRecipeDetail(idRecipe:Int): Resource<Recipe>

}