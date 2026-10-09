package com.andreeau.polls.ui.presentation.views.viewModels.viewModelDataClasses

import com.andreeau.pollcore.entities.Poll

data class PollCodeVMDataclass(
    val pollCode: String = "",
    val poll: Poll = Poll("", "",0,emptyList()),
    val canProceedToVote: Boolean = false,
    val isPollClosed: Boolean = false
)

