package com.andreeau.pollcore.repositories

import com.andreeau.pollcore.entities.Poll


interface PollRepository {
    fun createPoll(poll: Poll)
    fun getPoll(id: String)
    fun getPollStatus(id: String)
    fun getPollResults(id: String)
    fun vote(pollId: String, optionId: Int)

}