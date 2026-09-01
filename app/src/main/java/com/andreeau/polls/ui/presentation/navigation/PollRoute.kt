package com.andreeau.polls.ui.presentation.navigation

sealed class PollRoute(val route: String) {

    data object Splash : PollRoute("splash")

    data object Menu : PollRoute("menu")

    data object CreatePoll : PollRoute("create_poll")

    data object CreateResults : PollRoute("create_results")

    data object VoteCode : PollRoute("vote_code")

    data object VotePoll : PollRoute("vote_poll")

    data object VoteResults : PollRoute("vote_results")
}