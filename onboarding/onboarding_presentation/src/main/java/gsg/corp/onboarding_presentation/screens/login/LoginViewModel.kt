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

    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var saveCredentials by mutableStateOf(false)
        private set
    var isLoading by mutableStateOf(false)
        private set
    var dialogErrorShow by mutableStateOf(false)
        private set
    var msgError by mutableStateOf(
        UiEvent.ShowSnackBar(
            UiText.StringResource(R.string.error_login)
        )
    )
        private set


    init {
        if (pref.loadSaveCredentials()) {
            username = pref.loadUserInfo().username
            saveCredentials = pref.loadSaveCredentials()
        }
    }

    fun onLogin() {
        isLoading = true
        viewModelScope.launch {

            val result = onBoardingUseCases
                .verificationUser(userName = username, userPassword = password)

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
                            pref.saveCredentials(saveCredentials)
                            _uiEvent.send(UiEvent.Success)
                        }
                    }
                    isLoading = false
                }
                is Resource.Error -> {
                    isLoading = false
                    dialogErrorShow = true
                    msgError = UiEvent.ShowSnackBar(result.message ?: UiText.StringResource(R.string.error_login))
                }
            }

        }
    }

    fun onUsernameEnter(item: String) {
        username = item
    }

    fun onPasswordEnter(item: String) {
        password = item
    }

    fun onCheckRemember(item: Boolean) {
        saveCredentials = item
    }

    fun hideError(item: Boolean) {
        dialogErrorShow = item
    }


}