package com.andreeau.polls.ui.presentation.views.viewModels

import androidx.lifecycle.ViewModel
import com.andreeau.pollcore.api.Constants
import com.andreeau.pollcore.useCases.CreatePollUseCase
import com.andreeau.polls.ui.presentation.views.viewModels.ViewModelDataClasses.CreatePollVMDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class CreatePollScreenViewModel @Inject constructor(
    private val createPollUseCase: CreatePollUseCase
) : ViewModel() {
    private val _createPollState = MutableStateFlow(CreatePollVMDataClass())

    val createPollState = _createPollState.asStateFlow()

    fun updateQuestion(question: String) {
        _createPollState.update { it.copy(pollquestion = question) }
    }

    fun addAnswer() {
        _createPollState.update {
            val newIndex = it.pollAnswers.size
            if (newIndex < Constants.MAX_ANSWERS) {
                it.copy(pollAnswers = it.pollAnswers + "",
                    answerToFocus = newIndex)
            } else {
                it
            }
        }
    }

    fun updateAnswer(index: Int, answer: String) {
        _createPollState.update { state ->

            val updatedAnswers = state.pollAnswers.toMutableList()

            updatedAnswers[index] = answer

            state.copy(
                pollAnswers = updatedAnswers
            )
        }
    }

    fun createPoll(){

    }
}



