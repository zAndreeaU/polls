package com.andreeau.polls.ui.presentation.views.viewModels.viewModelDataClasses

import com.andreeau.pollcore.entities.Poll

data class PollResultsFromCreateVMDataClass(
    val pollCode: String = "",
    val poll: Poll = Poll("", "", 0, emptyList()),
    val showError: Boolean = false,
    val isClosed: Boolean = false
)