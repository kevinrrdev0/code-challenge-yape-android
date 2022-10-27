package gsg.corp.onboarding_presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gsg.corp.core.util.UiEvent
import gsg.corp.core.util.UiText
import gsg.corp.core.R
import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.core.domain.model.UserInfo
import gsg.corp.core.domain.preferences.Preferences
import gsg.corp.onboarding_domain.use_case.OnBoardingUseCases
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val onBoardingUseCases: OnBoardingUseCases,
    private val pref: Preferences,
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var state by mutableStateOf(LoginState())
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnLogin -> onLogin()
            is LoginEvent.OnPasswordEnter -> state = state.copy(password = event.password)
            is LoginEvent.OnUserNameEnter -> state = state.copy(username = event.username)
            is LoginEvent.OnCheckRememberPress -> state = state.copy(saveCredentials = event.check)
            is LoginEvent.OnHideError -> state = state.copy(messageError = MessageError(isVisible = false))
        }
    }

    init {
        if (pref.loadSaveCredentials()) {
            state = state.copy(
                username = pref.loadUserInfo().username,
                saveCredentials = pref.loadSaveCredentials()
            )
        }
    }

    fun onLogin() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {

            val result = onBoardingUseCases
                .verificationUser(userName = state.username, userPassword = state.password)
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        if (it.id > 0) {
                            pref.saveUserName(it.user)
                            pref.saveUser(
                                UserInfo(
                                    it.id,
                                    it.name,
                                    it.telephone,
                                    it.user,
                                    it.role,
                                    it.imageUrl
                                )
                            )
                            pref.saveToken(it.token)
                            pref.saveCredentials(state.saveCredentials)
                            _uiEvent.send(UiEvent.Success)
                        }
                    }
                    state = state.copy(isLoading = false)
                }
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
            }

        }
    }

}