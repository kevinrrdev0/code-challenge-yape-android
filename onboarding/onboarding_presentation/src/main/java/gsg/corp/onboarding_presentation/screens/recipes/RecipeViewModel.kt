package gsg.corp.onboarding_presentation.screens.recipes

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gsg.corp.core.R
import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.core.global_models.MessageError
import gsg.corp.core.util.UiText
import gsg.corp.onboarding_domain.model.Recipe
import gsg.corp.onboarding_domain.use_case.OnBoardingUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val onBoardingUseCases: OnBoardingUseCases
) : ViewModel() {

    var state by mutableStateOf(RecipeState())
        private set

    init {
        getRecipes()
    }

    fun onEvent(event: RecipeEvent) {
        when (event) {
            is RecipeEvent.OnHideError -> state =
                state.copy(messageError = MessageError(isVisible = false))

            is RecipeEvent.OnQueryChange -> {
                state = state.copy(searchQuery = event.query)
                filterRecipes()
            }

            is RecipeEvent.OnToggleRouteClick -> state = state.copy(
                listRecipesShow = state.listRecipesShow.map {
                    if (it.recipe.id == event.recipeUiState.recipe.id) {
                        it.copy(isExpanded = !it.isExpanded)
                    } else it
                }
            )
        }
    }

    private fun filterRecipes() {
        val currentList = state.listRecipes
        if (state.searchQuery.isNotEmpty()) {
            when (val result =
                onBoardingUseCases.filterRecipeUseCase(state.searchQuery, currentList)) {
                is Resource.Error -> TODO()
                is Resource.Success -> {
                    result.data?.let { filterList ->
                        Log.d(
                            "kevinrrdev",
                            " resultado del caso de uso ${state.searchQuery} filterRecipes: ${filterList.size}"
                        )
                        state = state.copy(
                            listRecipesShow = filterList.map { RecipeUiState(recipe = it) }
                        )
                    }
                }
            }
        } else {
            state = state.copy(
                loading = false,
                listRecipesShow = currentList.map { RecipeUiState(recipe = it) }
            )
        }
    }

    private fun getRecipes() {
        state = state.copy(loading = true)
        viewModelScope.launch {
            when (val result = onBoardingUseCases.getFoodRecipesUseCase()) {
                is Resource.Error -> {
                    state = state.copy(
                        loading = false,
                        messageError = MessageError(
                            isVisible = true,
                            description = result.message
                                ?: UiText.StringResource(R.string.error_login)
                        )
                    )
                }

                is Resource.Success -> {
                    result.data?.let { recipes ->
                        state = state.copy(
                            listRecipes = recipes,
                            listRecipesShow = recipes.map { RecipeUiState(recipe = it) },
                            loading = false
                        )
                    }
                }
            }
        }
    }
}