package com.andreeau.pollcore.useCases

import com.andreeau.pollcore.repositories.PollRepository


class VoteUseCase(private val pollRepository: PollRepository)  {
    fun execute(pollId: String, optionId: Int) = pollRepository.vote(pollId, optionId);
}