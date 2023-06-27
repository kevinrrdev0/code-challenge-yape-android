package gsg.corp.onboarding_presentation.screens.recipes.detail

import gsg.corp.core.global_models.MessageError
import gsg.corp.onboarding_domain.model.Recipe

data class RecipeDetailState(
    val isLoading: Boolean = false,
    val messageError: MessageError = MessageError(),
    val recipe : Recipe = Recipe(0,"","","","","","")
)