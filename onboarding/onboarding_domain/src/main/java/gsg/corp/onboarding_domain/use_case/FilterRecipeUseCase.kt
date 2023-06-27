package gsg.corp.onboarding_domain.use_case

import android.util.Log
import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.onboarding_domain.model.Recipe

class FilterRecipeUseCase {
    operator fun invoke(
        query: String,
        recipes: List<Recipe>
    ): Resource<List<Recipe>> {
        if (query.isBlank()) {
            return Resource.Success(recipes)
        }
        val filterList = recipes.filter { recipe ->
            recipe.ingredients.contains(query, ignoreCase = true) ||
                    recipe.nameRecipe.contains(query, ignoreCase = true)
        }
        return Resource.Success(filterList)
    }
}