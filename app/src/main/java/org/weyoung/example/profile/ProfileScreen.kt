package org.weyoung.example.profile

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun Profile(
    viewModel: ProfileViewModel,
    navHostController: NavHostController,
    userId: Int?,
    userName: String?
) {
    Text(text = "Profile $userId $userName")
}
