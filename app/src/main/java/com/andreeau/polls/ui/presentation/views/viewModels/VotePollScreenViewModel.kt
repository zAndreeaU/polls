package com.andreeau.polls.ui.presentation.views.viewModels

import androidx.lifecycle.ViewModel
import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollcore.repositories.callBacks.MainPollCallback
import com.andreeau.pollcore.useCases.GetPollUseCase
import com.andreeau.pollcore.useCases.VoteUseCase
import com.andreeau.polls.ui.presentation.views.viewModels.viewModelDataClasses.VotePollVMDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class VotePollScreenViewModel @Inject constructor(
    private val getPollUseCase: GetPollUseCase,
    private val voteUseCase: VoteUseCase
) : ViewModel() {
    private val _votePollState = MutableStateFlow(VotePollVMDataClass())

    val votePollState = _votePollState.asStateFlow()

    fun retrieveData(code: String) {

        getPollUseCase.execute(
            code,
            object : MainPollCallback<Poll> {

                override fun onSuccess(poll: Poll) {
                    _votePollState.update { it.copy(pollCode = code, poll = poll) }

                }

                override fun onError() {
                    _votePollState.update { it.copy(showError = true) }
                }

            }
        )
    }

    fun voteAnswer(
        pollId: String,
        optionId: Int
    ) {
        voteUseCase.execute(
            pollId = pollId,
            optionId = optionId,
            callback = object : MainPollCallback<Unit> {

                override fun onSuccess(data: Unit) {
                    _votePollState.update { it.copy(canProceed = true) }
                }

                override fun onError() {
                    // vote failed
                }
            }
        )
    }
}



