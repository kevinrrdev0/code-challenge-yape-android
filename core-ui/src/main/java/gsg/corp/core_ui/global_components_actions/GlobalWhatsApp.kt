package gsg.corp.core_ui.global_components_actions

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Icon
import androidx.compose.material.IconButton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import gsg.corp.core_ui.R

@Composable
fun GlobalWhatsApp(isEnable: Boolean = true,phone:String,modifier: Modifier) {
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=51$phone"))
    val callResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()) {
    }

    IconButton(
        onClick = {
            callResultLauncher.launch(intent)
                  },
        enabled = isEnable,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_whatsapp_message),
            contentDescription = "WhatsApp",
            tint = Color(0xFF5CEE3C)
        )
    }
}