package gsg.corp.core_ui.global_components_actions

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import gsg.corp.core_ui.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    label: String = "",
    onPhotoIsTaken: (String?) -> Unit
) {

    val context = LocalContext.current

    // camera variables
    var hasImage by remember {
        mutableStateOf(false)
    }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var imageZoom by remember {
        mutableStateOf(false)
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            hasImage = success
            if (hasImage) {
                val cacheDir = context.cacheDir
                val path = imageUri?.path?.let {
                    if (it.startsWith("/")) {
                        (cacheDir.parentFile?.absolutePath ?: "") + it
                    } else {
                        cacheDir.absolutePath + "/" + it
                    }
                }

                onPhotoIsTaken(path)
            }
        }
    )

    //permissions
    val cameraPermissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        arrayOf(Manifest.permission.CAMERA)
    } else {
        arrayOf(
            Manifest.permission.CAMERA,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) Manifest.permission.READ_EXTERNAL_STORAGE
            else Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }


    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.all { it.value }) {
            val uri = getUri(context)
            imageUri = uri
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied!", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = modifier,
    ) {

        PhotoOrder(label, hasImage, imageUri, onTakePhoto = {
            hasImage = false
            imageUri = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val activity = context as? ComponentActivity
                if (!Environment.isExternalStorageManager()) {
                    val uri = Uri.parse("package:" + (activity?.packageName ?: "gsg.corp.ruterito"))
                    activity?.startActivity(
                        Intent(
                            Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                            uri
                        )
                    )
                } else {
                    permissionLauncher.launch(cameraPermissions)
                }
            } else {
                if (cameraPermissions.all {
                        ContextCompat.checkSelfPermission(
                            context,
                            it
                        ) == PackageManager.PERMISSION_GRANTED
                    }) {
                    val uri = getUri(context)
                    imageUri = uri
                    cameraLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(cameraPermissions)
                }
            }
        },
            onSelectPhoto = {
                hasImage = false
                imageUri = null
                imagePicker.launch("image/*")
            }, onZoomPhoto = {
                if (hasImage && imageUri != null) {
                    imageZoom = it
                }
            })
        if (imageZoom) {
            imageUri?.let {
                ImageDialog(it, onDismiss = {
                    imageZoom = false
                })
            }
        }

    }
}


@Composable
fun PhotoOrder(
    label: String,
    hasImage: Boolean,
    imageUri: Uri?,
    onTakePhoto: () -> Unit,
    onSelectPhoto: () -> Unit,
    onZoomPhoto: (Boolean) -> Unit
) {

    Column(
        modifier = Modifier
    ) {
        Text(
            text = label
        )
        Spacer(
            modifier = Modifier
                .height(6.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (hasImage && imageUri != null) {
                AsyncImage(
                    model = imageUri,
                    modifier = Modifier
                        .size(74.dp)
                        .clickable {
                            onZoomPhoto(true)
                        },
                    contentDescription = "Image Taken",
                    contentScale = ContentScale.Crop

                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.image),
                    contentDescription = "Image Default",
                    modifier = Modifier.size(74.dp)
                )
            }

            Column(
                modifier = Modifier.padding(4.dp, 0.dp, 0.dp, 0.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        onTakePhoto()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = null
                    )
                    Text(
                        text = " Foto",
                        fontWeight =
                        FontWeight.SemiBold
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        onSelectPhoto()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.galleryexport),
                        contentDescription = null
                    )
                    Text(
                        text = " Galeria",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

private fun getUri(context: Context): Uri {
    val cacheDir = File(context.cacheDir, "images")
    if (!cacheDir.exists()) {
        cacheDir.mkdirs()
    }
    val tmpFile = File.createTempFile(
        SimpleDateFormat(
            "yyyy-MM-dd-HH-mm-ss-SSS",
            Locale("es", "PE")
        ).format(System.currentTimeMillis()), ".jpg",
        cacheDir
    )

    val authority = "${context.packageName}.provider"

    return FileProvider.getUriForFile(
        context.applicationContext,
        authority,
        tmpFile
    )
}


@Composable
fun ImageDialog(imageUri: Uri, onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .background(Color.Black)
                .clickable(onClick = onDismiss)
        ) {
            AsyncImage(
                model = imageUri,
                modifier = Modifier.fillMaxWidth(),
                contentDescription = "Image Taken",
                contentScale = ContentScale.Crop
            )
        }
    }
}
