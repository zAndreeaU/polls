package com.andreeau.pollcore.repositories

import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollcore.repositories.callBacks.MainPollCallback


interface PollRepository {
    fun createPoll(poll: Poll)
    fun getPoll(id: String, callback: MainPollCallback<Poll>)
    fun getPollStatus(id: String)
    fun getPollResults(id: String, callback: MainPollCallback<Poll>)
    fun vote(pollId: String, optionId: Int, callback: MainPollCallback<Unit>)

}