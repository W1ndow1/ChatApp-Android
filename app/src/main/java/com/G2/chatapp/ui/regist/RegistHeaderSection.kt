package com.G2.chatapp.ui.regist

import android.graphics.drawable.shapes.RoundRectShape
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.G2.chatapp.viewModel.LoginViewModel


@Composable
fun RegistHeaderSection(
    viewModel: LoginViewModel
) {
    var isImagePicked by remember { mutableStateOf(false) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if(uri != null) {
            viewModel.image = uri
            isImagePicked = true
        }
    }

    Box(
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
            .clickable {
                imagePickerLauncher.launch("image/*")
            },
        contentAlignment = Alignment.Center
    ) {
        if (viewModel.image != null) {
            Image(
                painter = rememberAsyncImagePainter(viewModel.image),
                contentDescription = "이미지를 선택하세요",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(4.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "기본이미지",
                tint = Color.White,
                modifier = Modifier.size(64.dp)
            )
        }
    }
}