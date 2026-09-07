package com.andreeau.pollcore.useCases

import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollcore.repositories.PollRepository
import com.andreeau.pollcore.repositories.callBacks.MainPollCallback


class GetPollUseCase(private val pollRepository: PollRepository)  {
    fun execute(id:String,callback: MainPollCallback<Poll>) = pollRepository.getPoll(id,callback);
}