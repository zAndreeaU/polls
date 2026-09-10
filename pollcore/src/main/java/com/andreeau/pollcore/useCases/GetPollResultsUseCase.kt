package com.andreeau.pollcore.useCases

import android.R
import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollcore.repositories.PollRepository
import com.andreeau.pollcore.repositories.callBacks.MainPollCallback
import javax.inject.Inject

class GetPollResultsUseCase @Inject constructor(
    private val pollRepository: PollRepository
) {
    fun execute(id: String, callback: MainPollCallback<Poll>) =
        pollRepository.getPollResults(id, callback)

}