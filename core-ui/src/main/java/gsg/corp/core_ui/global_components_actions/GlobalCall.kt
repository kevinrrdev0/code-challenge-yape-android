package gsg.corp.core_ui.global_components_actions

import android.Manifest
import android.content.Intent
import android.content.Intent.ACTION_CALL
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import gsg.corp.core_ui.Orange
import gsg.corp.core_ui.R
import kotlinx.coroutines.launch

@Composable
fun GlobalCall(isEnable: Boolean = true,phone:String,modifier: Modifier) {
    val context = LocalContext.current
    val intent = Intent(ACTION_CALL, Uri.parse("tel:$phone"))
    val callResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()) {

    }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            callResultLauncher.launch(intent)

        } else {
            //Toast.makeText(context, "Permission Denied!", Toast.LENGTH_SHORT).show()
        }
    }

        IconButton(
            modifier = modifier,
            onClick = {
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        context, Manifest.permission.CALL_PHONE
                    ),
                    -> {
                        callResultLauncher.launch(intent)
                    }
                    else ->{
                        permissionLauncher.launch(Manifest.permission.CALL_PHONE)
                    }
                }
            },
            enabled = isEnable
        ) {
            Icon(
                imageVector = Icons.Rounded.Call,
                contentDescription = "Cel1"
            )
        }




}