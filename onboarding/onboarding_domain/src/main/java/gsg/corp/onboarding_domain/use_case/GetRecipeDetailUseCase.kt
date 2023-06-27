package gsg.corp.onboarding_domain.use_case

import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.onboarding_domain.model.Recipe
import gsg.corp.onboarding_domain.repository.OnBoardingRepository

class GetRecipeDetailUseCase ( private val repository: OnBoardingRepository
) {
    suspend operator fun invoke(idRecipe:Int): Resource<Recipe> {
        return repository.getRecipeDetail(idRecipe)
    }
}