package gsg.corp.onboarding_presentation.screens.recipes

import gsg.corp.core.global_models.MessageError
import gsg.corp.onboarding_domain.model.Recipe

data class RecipeState(
    val searchQuery:String = "",
    val listRecipes: List<Recipe> = emptyList(),
    val listRecipesShow: List<RecipeUiState> = emptyList(),
    val loading: Boolean = false,
    val messageError: MessageError = MessageError()
)