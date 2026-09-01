package com.andreeau.pollcore.useCases

import com.andreeau.pollcore.repositories.PollRepository

class GetPollStatusUseCase(private val pollRepository: PollRepository) {
    fun execute(id: String) = pollRepository.getPollStatus(id);

}