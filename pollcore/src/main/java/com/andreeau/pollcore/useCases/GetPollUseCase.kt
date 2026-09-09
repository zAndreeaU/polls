package com.andreeau.pollcore.useCases

import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollcore.repositories.PollRepository
import com.andreeau.pollcore.repositories.callBacks.MainPollCallback
import javax.inject.Inject


class GetPollUseCase @Inject constructor(
    private val pollRepository: PollRepository
) {
    fun execute(
        id: String,
        callback: MainPollCallback<Poll>
    ) = pollRepository.getPoll(id, callback)
}