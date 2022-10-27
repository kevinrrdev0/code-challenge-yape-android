package gsg.corp.onboarding_presentation.screens.login

sealed class LoginEvent {
    data class OnUserNameEnter(val username: String) : LoginEvent()
    data class OnPasswordEnter(val password: String) : LoginEvent()
    data class OnCheckRememberPress(val check: Boolean) : LoginEvent()
    object OnHideError : LoginEvent()
    object OnLogin : LoginEvent()
}