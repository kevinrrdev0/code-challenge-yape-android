package gsg.corp.driver_presentation.screens.routes.routes_detail

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gsg.corp.driver_presentation.R

@Preview
@Composable
fun CollectionScreen() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Recolección - RUT-REC1A",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Spacer(
                modifier = Modifier
                    .height(30.dp)
            )
            DataDetail()
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 18.dp)) {
                Divider(
                    Modifier
                        .height(1.dp)
                        .background(Color(0xFFA5A4A4))
                )
            }
            PhotoCollect()
        }
    }
    Column(modifier = Modifier.fillMaxSize(), Arrangement.Bottom) {
        ButtonCollected()
    }
}

@Composable
fun DataDetail() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Datos del recojo"
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Row {
            Icon(
                painter = painterResource(id = R.drawable.carbon_enterprise),
                contentDescription = null,
                modifier = Modifier.padding(end = 7.dp)
            )
            Text(text = "Empresa:")
        }
        Row {
            Text(
                text = "FRIS SPORTRS",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 21.dp)
            )
            Spacer(
                modifier = Modifier
                    .width(170.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.vector__1_),
                contentDescription = null,
                tint = Color(0xFF5CEE3C)
            )
            Spacer(
                modifier = Modifier
                    .width(7.dp)
            )
            Box(
                Modifier
                    .background(Color(0xFF25D2D8))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_phone_24),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Row {
            Icon(
                painter = painterResource(id = R.drawable.vector__2_),
                contentDescription = null,
                modifier = Modifier.padding(end = 7.dp)
            )
            Text(text = "Direccion:")
        }
        Spacer(
            modifier = Modifier
                .height(3.dp)
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Av. la marina 245 curze con choristar",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 15.dp)
            )
            Spacer(
                modifier = Modifier.width(40.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_content_copy_24),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .height(3.dp)
        )
        Column(
            modifier = Modifier.padding(start = 21.dp)
        ) {
            Text(
                text = "Referencia:"
            )
            Text(
                text = "Fuente al parque de la libertad",
                fontWeight = FontWeight.SemiBold
            )
            Spacer(
                modifier = Modifier
                    .height(3.dp)
            )
            Text(
                text = "Producto:"
            )
            Text(
                text = "Zapatillas nike",
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier
                .padding(start = 21.dp)
        ) {
            Text(
                text = "Cantidad:"
            )
            Spacer(
                modifier = Modifier
                    .width(3.dp)
            )
            Text(text = "4", fontWeight = FontWeight.SemiBold)
        }
    }
}
@Composable
fun ButtonCollected() {

    Row(modifier = Modifier.padding(15.dp)) {
        TextButton(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color.Red
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_close_24),
                contentDescription = null
            )
            Text(text = "No Recolectado")
        }
        Spacer(modifier = Modifier.width(20.dp))
        TextButton(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = Color(0xFF41DA26)
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_check_24),
                contentDescription = null
            )
            Text(text = "Recolectado")
        }
    }

}

@Composable
fun PhotoCollect() {
    var selectedImage by remember { mutableStateOf(true) }
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember{ mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()){
        imageUri = it
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 47.dp, top = 16.dp)
    ){
        Text(
            text = "Foto del Recojo"
        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )
        Row {
            Box(modifier = Modifier.size(120.dp)){
                if(selectedImage) {
                    Image(painter =painterResource(id = R.drawable.image) , contentDescription = null)
                }
                imageUri?.let {
                    bitmap = if(Build.VERSION.SDK_INT < 28){
                        MediaStore.Images.Media.getBitmap(context.contentResolver,it)
                    }else {
                        selectedImage = false
                        val source = ImageDecoder.createSource(context.contentResolver,it)
                        ImageDecoder.decodeBitmap(source)

                    }
                    if(!selectedImage) {
                        Image(bitmap = bitmap?.asImageBitmap()!!, contentDescription = "" , modifier = Modifier.size(200.dp))
                    }
                }
            }

            Column(
                Modifier,
                Arrangement.SpaceBetween,
                Alignment.CenterHorizontally
            ){
                Row(
                    Modifier
                        .padding(start = 25.dp, top = 24.dp)
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = null
                    )
                    Spacer(
                        modifier = Modifier
                            .width(12.dp)
                    )
                    Text(
                        text = "Tomar foto",
                        fontWeight =
                        FontWeight.SemiBold
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(15.dp)
                )
                Row(
                    Modifier
                        .padding(start = 25.dp, end = 24.dp)
                        .clickable { launcher.launch("image/*") }
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.galleryexport),
                        contentDescription = null
                    )
                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )
                    Text(
                        text = "Galeria",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}