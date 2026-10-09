package com.andreeau.polls.ui.presentation.views.viewModels

import androidx.lifecycle.ViewModel
import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollcore.repositories.callBacks.MainPollCallback
import com.andreeau.pollcore.useCases.ClosePollUseCase
import com.andreeau.pollcore.useCases.GetPollResultsUseCase

import com.andreeau.polls.ui.presentation.views.viewModels.viewModelDataClasses.PollResultsFromCreateVMDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PollResultsFromCreateScreenViewModel @Inject constructor(
    private val getPollResultsUseCase: GetPollResultsUseCase,
    private val closePollUseCase: ClosePollUseCase
) :
    ViewModel() {
    private val _pollResultState = MutableStateFlow(PollResultsFromCreateVMDataClass())

    val pollResultState = _pollResultState.asStateFlow()

    fun observePollResults(pollId: String) {
        getPollResultsUseCase.execute(pollId, object : MainPollCallback<Poll> {
            override fun onSuccess(data: Poll) {
                _pollResultState.update { it.copy(poll = data) }
            }

            override fun onError() {
                _pollResultState.update { it.copy(showError = true) }
            }

        })
    }

    fun closePoll(pollId: String) {
        closePollUseCase.execute(pollId, object : MainPollCallback<Unit> {
            override fun onSuccess(data: Unit) {
                _pollResultState.update { it.copy(isClosed = true) }

            }

            override fun onError() {
                _pollResultState.update { it.copy(showError = true) }
            }

        })
    }
}