package gsg.corp.onboarding_presentation.screens.recipes.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gsg.corp.core.R
import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.core.global_models.MessageError
import gsg.corp.core.util.UiText
import gsg.corp.core_ui.navigation.RECIPE_ID
import gsg.corp.onboarding_domain.use_case.OnBoardingUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val onBoardingUseCases: OnBoardingUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(RecipeDetailState())
        private set

    init {
        val idRoute = savedStateHandle.get<Int>(RECIPE_ID) ?: 0
        if (idRoute != 0) {
            getRecipeDetail(idRoute)
        }
    }

    private fun getRecipeDetail(id: Int) {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            when (val result = onBoardingUseCases.getRecipeDetailUseCase(id)) {
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        messageError = MessageError(
                            isVisible = true,
                            description = result.message
                                ?: UiText.StringResource(R.string.error_login)
                        )
                    )
                }

                is Resource.Success -> {
                    result.data?.let {
                        state = state.copy(isLoading = false, recipe = it)
                    }
                }
            }
        }

    }

}