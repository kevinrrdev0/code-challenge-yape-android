package gsg.corp.core_ui.global_components_inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import gsg.corp.core_ui.LightGray
import gsg.corp.core_ui.RedGsg
import gsg.corp.core_ui.TextGray
import gsg.corp.core_ui.global_components_texts.TextBody
import gsg.corp.core_ui.global_components_ui.GlobalErrorCaption

@Composable
fun GlobalInput(
                text: String,
                hint:String,
                modifier: Modifier = Modifier,
                msgError:String ="",
                action: ImeAction = ImeAction.Next,
                type: KeyboardType = KeyboardType.Text,
                minLines: Int = 1,
                maxLines: Int = 1,
                maxChar: Int = 100,
                isError: Boolean = false,
                isVisible:Boolean = true,
                focusDir: FocusDirection = FocusDirection.Down,
                onAction: () -> Unit = { /* TODO */ },
                onValueChange: (String) -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
    if (isVisible){
        Column(modifier = modifier
            .fillMaxWidth()) {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    if (it.length <= maxChar) {
                        onValueChange(it)
                    }
                },
                isError = isError,
                label = { Text(text=hint) },
                modifier = modifier
                    .fillMaxWidth(),
                textStyle = MaterialTheme.typography.body1.copy(),
                minLines = minLines,
                maxLines = maxLines,
                keyboardOptions = KeyboardOptions(keyboardType = type,
                    imeAction = action),
                keyboardActions = KeyboardActions(onNext = {
                    localFocusManager.moveFocus(focusDir)
                }, onDone = {
                    localFocusManager.clearFocus()
                    onAction()
                    defaultKeyboardAction(action)
                })
            )
            if (isError) {
                GlobalErrorCaption(msgError)
            }
        }
    }

}