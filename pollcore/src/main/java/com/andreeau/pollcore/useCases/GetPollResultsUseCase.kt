package com.andreeau.pollcore.useCases

import android.R
import com.andreeau.pollcore.repositories.PollRepository

class GetPollResultsUseCase(private val pollRepository: PollRepository) {
    fun execute(id: String) = pollRepository.getPollResults(id)

}