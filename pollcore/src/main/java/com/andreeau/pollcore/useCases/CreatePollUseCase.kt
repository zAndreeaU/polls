package com.andreeau.pollcore.useCases

import android.R
import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollcore.repositories.PollRepository
import com.andreeau.pollcore.repositories.callBacks.MainPollCallback
import javax.inject.Inject


class CreatePollUseCase @Inject constructor(
    private val pollRepository: PollRepository
) {
    fun execute(poll: Poll, pollId: String, callback: MainPollCallback<Unit>) =
        pollRepository.createPoll(poll, pollId, callback);
}