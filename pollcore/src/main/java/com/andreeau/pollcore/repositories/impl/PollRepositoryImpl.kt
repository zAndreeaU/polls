package com.andreeau.pollcore.repositories.impl

import com.andreeau.pollcore.api.Constants
import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollcore.repositories.PollRepository
import com.andreeau.pollcore.repositories.callBacks.MainPollCallback
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PollRepositoryImpl @Inject constructor(
    private val database: DatabaseReference
) : PollRepository {


    override fun createPoll(poll: Poll) {
        TODO("Not yet implemented")
    }

    override fun getPoll(
        id: String,
        response: MainPollCallback<Poll>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val snapshot = database
                    .child(Constants.POLLS)
                    .child(id)
                    .get()
                    .await()

                if (!snapshot.exists()) {
                    withContext(Dispatchers.Main) {
                        response.onError()
                    }
                    return@launch
                }

                val poll = snapshot.getValue(Poll::class.java)

                withContext(Dispatchers.Main) {
                    if (poll != null) {
                        response.onSuccess(poll)
                    } else {
                        response.onError()
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    println("--------------------------------------------------- ")
                    e.printStackTrace()
                    response.onError()
                }
            }
        }
    }

    override fun getPollStatus(id: String) {
        TODO("Not yet implemented")
    }

    override fun getPollResults(id: String) {
        TODO("Not yet implemented")
    }

    override fun vote(pollId: String, optionId: Int) {
        TODO("Not yet implemented")
    }

}