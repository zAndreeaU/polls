package com.andreeau.polls.ui.presentation.views.viewModels.ViewModelDataClasses

data class CreatePollVMDataClass(
    val newPollCode : String="",
    val pollquestion: String="",
    val pollId: String="",
    val pollAnswers: List<String> =listOf("", ""),
    val showError : Boolean=false,
    val answerToFocus : Int? = null,
    val canAddMore : Boolean=true,
    val canProceed : Boolean = false,
    val pollCreated : Boolean=false,
    val pollCreationError : Boolean = false
    )
