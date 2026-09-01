package com.andreeau.pollcore.useCases

import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollcore.repositories.PollRepository


class CreatePollUseCase(private val pollRepository: PollRepository)  {
    fun execute(poll: Poll) = pollRepository.createPoll(poll);
}