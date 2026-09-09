package com.andreeau.pollcore.repositories

import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollcore.repositories.callBacks.MainPollCallback


interface PollRepository {
    fun createPoll(poll: Poll)
    fun getPoll(id: String,response: MainPollCallback<Poll>)
    fun getPollStatus(id: String)
    fun getPollResults(id: String,)
    fun vote(pollId: String, optionId: Int,response: MainPollCallback<Unit>)

}