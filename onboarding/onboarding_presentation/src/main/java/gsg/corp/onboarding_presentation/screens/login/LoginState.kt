package gsg.corp.onboarding_presentation.screens.login

import gsg.corp.core.util.UiText

data class LoginState(
    val username: String = "",
    val password: String = "",
    val saveCredentials: Boolean = false,
    val isLoading: Boolean = false,
    val messageError: MessageError = MessageError()
)

data class MessageError(
    val isVisible: Boolean = false,
    val title: UiText = UiText.DynamicString("") ,
    val description: UiText = UiText.DynamicString("")
)
