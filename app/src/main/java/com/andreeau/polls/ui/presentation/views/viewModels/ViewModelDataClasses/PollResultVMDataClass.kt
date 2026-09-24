package com.andreeau.polls.ui.presentation.views.viewModels.ViewModelDataClasses

import com.andreeau.pollcore.entities.Poll

data class PollResultVMDataClass(
    val pollCode: String = "",
    val poll: Poll = Poll("","", 0,emptyList()),
    val showError: Boolean = false
)
