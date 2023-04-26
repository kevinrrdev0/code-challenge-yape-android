package gsg.corp.core.global_models

import gsg.corp.core.util.UiText

data class MessageError(
    val isVisible: Boolean = false,
    val title: UiText = UiText.DynamicString(""),
    val description: UiText = UiText.DynamicString("")
)
