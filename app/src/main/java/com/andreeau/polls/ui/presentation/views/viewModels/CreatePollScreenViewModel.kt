package com.andreeau.polls.ui.presentation.views.viewModels

import androidx.lifecycle.ViewModel
import com.andreeau.pollcore.api.Constants
import com.andreeau.pollcore.entities.AnswerOption
import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollcore.repositories.callBacks.MainPollCallback
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
                it.copy(
                    pollAnswers = it.pollAnswers + "",
                    answerToFocus = newIndex
                )
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

    fun createPoll() {
        checkAnswers()
        if(_createPollState.value.canProceed){
            _createPollState.update { it.copy(pollId = generatePollId())}

            var answers = _createPollState.value.pollAnswers.map { answer ->
                AnswerOption(name = answer, count = 0) }

            var poll: Poll = Poll(
                Constants.STATUS_OPEN, _createPollState.value.pollquestion,
                0,answers)

            createPollUseCase.execute(poll,_createPollState.value.pollId,object : MainPollCallback<Unit> {
                override fun onSuccess(data: Unit) {
                    _createPollState.update { it.copy(pollCreated = true,pollCreationError = false) }
                }

                override fun onError() {
                    _createPollState.update { it.copy(pollCreationError = true, pollCreated = false) }
                }
            })
        }
    }

    fun checkAnswers() {
        if (_createPollState.value.pollAnswers.take(2).all {
                it.isNotBlank()
            } && _createPollState.value.pollquestion.isNotBlank()) {
            _createPollState.update { it.copy(canProceed = true,showError = false) }
        }else{
            _createPollState.update { it.copy(showError = true) }
        }
    }

    fun generatePollId(): String {
        val chars = "abcdefghijklmnopqrstuvwxyz0123456789"

        return (1..6)
            .map { chars.random() }
            .joinToString("")
    }

}



