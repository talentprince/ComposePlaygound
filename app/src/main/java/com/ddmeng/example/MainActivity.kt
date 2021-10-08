package com.ddmeng.example

import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ddmeng.example.profile.Profile
import com.ddmeng.example.profile.ProfileViewModel
import com.ddmeng.example.ui.theme.AndroidComposeHelloWorldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidComposeHelloWorldTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "friendslist") {
                    composable("friendslist") {
                        val viewModel = hiltViewModel<MainViewModel>()
                        FriendsList(viewModel = viewModel, navHostController = navController)
                    }
                    composable(
                        "profile/{userId}/{userName}",
                        arguments = listOf(
                            navArgument("userId") { type = NavType.IntType },
                            navArgument("userName") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val viewModel = hiltViewModel<ProfileViewModel>()
                        Profile(
                            viewModel = viewModel,
                            navHostController = navController,
                            backStackEntry.arguments?.getInt("userId"),
                            backStackEntry.arguments?.getString("userName")
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


@Composable
private fun FriendsList(viewModel: MainViewModel, navHostController: NavHostController) {
    val state by viewModel.collectAsState()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            state.friends
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navHostController.navigate("profile/${it.id}/${it.name}")
                    },
                text = "Friend id: ${it.id}, name: ${it.name}",
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidComposeHelloWorldTheme {
        Greeting("Android")
    }
}
