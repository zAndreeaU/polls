package com.andreeau.polls.ui.presentation.views.viewModels.ViewModelDataClasses

import com.andreeau.pollcore.entities.AnswerOption
import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollcore.entities.Question

data class PollCodeVMDataclass(
    val pollCode: String = "",
    val poll: Poll = Poll("", "",0,emptyList()),
    val canProceed: Boolean = false
)

