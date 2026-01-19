package com.example.musicapp.navigation

import android.app.Activity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.screens.ComponentSample
import com.example.musicapp.screens.DetailScreen
import com.example.musicapp.screens.HomeScreen
import com.example.musicapp.screens.PlayerScreen
import com.example.musicapp.screens.SplashScreen
import com.example.musicapp.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicNavGraph(startDestination: String = Destination.SPLASH) {
    val navController = rememberNavController()
    val sharedVm: SharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = startDestination) {

        val onBack: () -> Unit = {
            if (navController.currentDestination?.route == Destination.HOME) {
                (navController.context as? Activity)?.finish()
            } else {
                navController.navigateUp()
            }
        }

        composable(Destination.SPLASH) {
            SplashScreen(
                onNavigateHome = { navController.navigate(Destination.COMP) }
            )
        }
        composable(Destination.HOME) {
            HomeScreen(
                sharedViewModel = sharedVm,
                    openComp={navController.navigate(Destination.COMP)},
                onNavPlayer = {
                    navController.navigate(Destination.PLAYER)
                }

                )
        }
        composable (Destination.DETAIL){
            DetailScreen(
                sharedViewModel = sharedVm,
                onBack = onBack
            )
        }

        composable(Destination.PLAYER) {
            PlayerScreen(
                onBack = onBack,
                sharedViewModel = sharedVm
            )
        }


        composable (Destination.COMP){
            ComponentSample(
                sharedViewModel = sharedVm,
                onNavHome={
                    navController.navigate(Destination.PLAYER)},
                onBack=onBack
            )
        }
    }
}
//        composable(Destination.HOME) {
//
//            HomeScreen(
//                name = "Don't Go",
//                duration = "30 min",
//
//                onNavDetail1 = {
//
//                    navController.navigate(
//                        Destination.detail(
//                           "Don't Go",
//                            "30 min",
//                            "Smoove & Turrell"
//                        )
//                    )
//                }
//            )
//        }
//        composable(
//            Destination.DETAIL,
//                arguments=listOf(
//                    navArgument("name"){
//                    type= NavType.StringType
//                },
//                    navArgument("duration"){
//                        type= NavType.StringType
//                    },
//                    navArgument("singer"){
//                        type= NavType.StringType
//                    }
//                )
//
//            ) {
//                    entry->
//                val name=entry.arguments?.getString("name")?:""
//                val duration=entry.arguments?.getString("duration")?:""
//                val singer=entry.arguments?.getString("singer")?:""
//            DetailScreen(
//                        name, duration, singer, onBack = onBack
//            )




