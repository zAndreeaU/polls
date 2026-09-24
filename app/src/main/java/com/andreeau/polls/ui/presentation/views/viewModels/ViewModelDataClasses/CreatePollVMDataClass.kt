package com.andreeau.polls.ui.presentation.views.viewModels.ViewModelDataClasses

data class CreatePollVMDataClass(
    val newPollCode : String="",
    val pollquestion: String="",
    val pollAnswers: List<String> = emptyList(),
    val showError : Boolean=false,
    val answerToFocus : Int? = null,
    val canAddMore : Boolean=true,
    )
