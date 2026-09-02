package com.andreeau.polls.ui.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.andreeau.polls.ui.presentation.views.CreatePollScreen
import com.andreeau.polls.ui.presentation.views.MenuScreen
import com.andreeau.polls.ui.presentation.views.ResultsScreen
import com.andreeau.polls.ui.presentation.views.SplashScreen
import com.andreeau.polls.ui.presentation.views.VotePollScreen

@Composable
fun PollsNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PollRoute.Splash.route
    ) {

        // ---------------------------------------------------------
        // Splash
        // ---------------------------------------------------------

        composable(PollRoute.Splash.route) {

            SplashScreen(
                onSplashFinished = {

                    navController.navigate(
                        PollRoute.Menu.route
                    ) {
                        popUpTo(PollRoute.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // ---------------------------------------------------------
        // Main Menu
        // ---------------------------------------------------------

        composable(PollRoute.Menu.route) {

            MenuScreen(

                onCreatePoll = {

                    navController.navigate(
                        PollRoute.CreatePoll.route
                    )
                },

                onVotePoll = { pollCode ->

                    navController.navigate(
                        "${PollRoute.VotePoll.route}/$pollCode"
                    )
                }
            )
        }

        // ---------------------------------------------------------
        // Create Poll
        // ---------------------------------------------------------

        composable(PollRoute.CreatePoll.route) {

            CreatePollScreen(
                onPollCreated = {

                    navController.navigate(
                        PollRoute.CreateResults.route
                    ) {
                        popUpTo(PollRoute.CreatePoll.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // ---------------------------------------------------------
        // Results after creating poll
        // ---------------------------------------------------------

        composable(PollRoute.CreateResults.route) {

            ResultsScreen(
                onBackToMenu = {

                    navController.navigate(
                        PollRoute.Menu.route
                    ) {
                        popUpTo(PollRoute.Menu.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // ---------------------------------------------------------
        // Vote Poll
        // ---------------------------------------------------------

        composable(
            route = "${PollRoute.VotePoll.route}/{pollCode}",
            arguments = listOf(
                navArgument("pollCode") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val pollCode =
                backStackEntry.arguments?.getString("pollCode") ?: ""

            VotePollScreen(
                pollCode = pollCode,

                onVoteCompleted = {

                    navController.navigate(
                        PollRoute.VoteResults.route
                    ) {
                        popUpTo(PollRoute.VotePoll.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // ---------------------------------------------------------
        // Results after voting
        // ---------------------------------------------------------

        composable(PollRoute.VoteResults.route) {

            ResultsScreen(
                onBackToMenu = {

                    navController.navigate(
                        PollRoute.Menu.route
                    ) {
                        popUpTo(PollRoute.VoteResults.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}