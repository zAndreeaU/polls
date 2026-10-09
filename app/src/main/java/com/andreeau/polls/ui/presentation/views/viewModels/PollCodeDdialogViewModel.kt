package com.andreeau.polls.ui.presentation.views.viewModels

import androidx.lifecycle.ViewModel
import com.andreeau.pollcore.api.Constants
import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollcore.repositories.callBacks.MainPollCallback
import com.andreeau.pollcore.useCases.GetPollUseCase
import com.andreeau.polls.ui.presentation.views.viewModels.viewModelDataClasses.PollCodeVMDataclass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class PollCodeDdialogViewModel @Inject constructor(
    private val getPollUseCase: GetPollUseCase
) : ViewModel() {

    private val _pollCodeState = MutableStateFlow(PollCodeVMDataclass())

    val pollCodeState = _pollCodeState.asStateFlow()

    fun onPollCodeChanged(code: String) {
        _pollCodeState.update {
            it.copy(
                pollCode = code.lowercase(),
            )
        }
        val count = code.length
        if(code.length == 6){
            onPollCodeAccepted(code)
        }
    }

    fun onPollCodeAccepted(code: String) {

        getPollUseCase.execute(
            code,
            object : MainPollCallback<Poll> {

                override fun onSuccess(poll: Poll) {
                    if (poll.status == Constants.STATUS_CLOSED) {
                        _pollCodeState.update {
                            it.copy(poll = poll,isPollClosed = true,canProceedToVote = true)
                        }
                        return
                    }
                    _pollCodeState.update {
                        it.copy(poll = poll, canProceedToVote = true,isPollClosed = false)
                    }
                }

                override fun onError() {
                    _pollCodeState.update {
                        it.copy(canProceedToVote = false)
                    }
                }

            }
        )
    }
}