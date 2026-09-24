package com.andreeau.pollcore.useCases

import com.andreeau.pollcore.repositories.PollRepository
import com.andreeau.pollcore.repositories.callBacks.MainPollCallback
import javax.inject.Inject


class VoteUseCase @Inject constructor(
    private val pollRepository: PollRepository
) {
    fun execute(pollId: String, optionId: Int, callback: MainPollCallback<Unit>) =
        pollRepository.vote(pollId, optionId, callback)
}