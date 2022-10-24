package gsg.corp.driver_presentation.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gsg.corp.core.util.UiEvent
import gsg.corp.core.util.UiText
import gsg.corp.driver_domain.use_case.DriverUseCases
import gsg.corp.core.R
import gsg.corp.core.data.network.model.response.Resource
import gsg.corp.core.domain.model.UserInfo
import gsg.corp.core.domain.preferences.Preferences
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val driverUseCases: DriverUseCases,
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

    init {
        if (pref.loadSaveCredentials()) {
            username = pref.loadUserInfo().username
            saveCredentials = pref.loadSaveCredentials()
        }
    }

    fun onLogin() {
        isLoading = true
        viewModelScope.launch {

            val result = driverUseCases
                .verificationUser(userName = username, userPassword = password)

            when (result) {
                is Resource.Success -> {
                    result.data?.let{
                        if (it.id > 0) {
                            pref.saveUserName(it.user)
                            pref.saveUser(UserInfo(it.id,it.name,it.telephone,it.user,it.role,it.image))
                            pref.saveToken(it.token)
                            pref.saveCredentials(saveCredentials)
                            _uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    UiText.StringResource(R.string.success_login)
                                )
                            )
                            _uiEvent.send(UiEvent.Success)
                        }
                    }
                    isLoading = false
                }
                is Resource.Error -> {
                    isLoading = false
                    Log.d("kevinrrdev", "onLogin: ${result.message}")
                    _uiEvent.send(
                        UiEvent.ShowSnackBar(
                            result.message  ?: UiText.StringResource(R.string.error_login)
                        )
                    )
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


}