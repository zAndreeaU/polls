package com.andreeau.pollcore.useCases

import com.andreeau.pollcore.repositories.PollRepository
import com.andreeau.pollcore.repositories.callBacks.MainPollCallback
import javax.inject.Inject

class ClosePollUseCase @Inject constructor(
    private val pollRepository: PollRepository
) {
    fun execute(pollId: String, callback: MainPollCallback<Unit>) {
        pollRepository.closePoll(pollId, callback)
    }
}