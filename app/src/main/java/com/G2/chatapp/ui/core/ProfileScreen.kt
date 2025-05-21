package com.G2.chatapp.ui.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.G2.chatapp.viewModel.FriendListViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: FriendListViewModel
) {
    Scaffold(
        content = { padding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(padding)
            ) {
                //원하는 뷰 값 넣기
                body(viewModel)
            }
        }
    )
}
@Composable
fun body(viewModel: FriendListViewModel) {

}