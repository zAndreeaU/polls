package com.andreeau.pollcore.useCases

import com.andreeau.pollcore.repositories.PollRepository


class GetPollUseCase(private val pollRepository: PollRepository)  {
    fun execute(id:String) = pollRepository.getPoll(id);
}