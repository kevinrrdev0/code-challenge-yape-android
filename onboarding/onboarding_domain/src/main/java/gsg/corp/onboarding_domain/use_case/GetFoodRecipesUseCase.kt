package gsg.corp.onboarding_domain.use_case

import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.onboarding_domain.model.Recipe
import gsg.corp.onboarding_domain.repository.OnBoardingRepository

class GetFoodRecipesUseCase(
    private val repository: OnBoardingRepository
) {
    suspend operator fun invoke(): Resource<List<Recipe>> {
        return repository.getFoodRecipes()
    }
}