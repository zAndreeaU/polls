package com.andreeau.pollcore.repositories.impl

import com.andreeau.pollcore.api.Constants
import com.andreeau.pollcore.entities.AnswerOption
import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollcore.repositories.PollRepository
import com.andreeau.pollcore.repositories.callBacks.MainPollCallback
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ServerValue
import com.google.firebase.database.ValueEventListener
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

                val _poll = snapshot.getValue(Poll::class.java)

                val answers = snapshot.children
                    .filter { it.key != Constants.QUESTION && it.key != Constants.COUNT }
                    .mapNotNull { it.getValue(AnswerOption::class.java) }

                val poll = _poll?.copy(
                    answers = answers)

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

    override fun getPollResults(id: String, callback: MainPollCallback<Poll>){
        database
            .child(Constants.POLLS)
            .child(id)
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val totalCount =
                        snapshot.child(Constants.COUNT).getValue(Int::class.java) ?: 0

                    val question =
                        snapshot.child(Constants.QUESTION).getValue(String::class.java) ?: ""

                    val answers = mutableListOf<AnswerOption>()

                    for (answerSnapshot in snapshot.children) {

                        // Ignore "question" and "count"
                        if (answerSnapshot.key == Constants.QUESTION ||
                            answerSnapshot.key == Constants.COUNT
                        ) {
                            continue
                        }

                        val name =
                            answerSnapshot.child(Constants.NAME)
                                .getValue(String::class.java) ?: ""

                        val count =
                            answerSnapshot.child(Constants.COUNT)
                                .getValue(Int::class.java) ?: 0

                        answers.add(
                            AnswerOption(
                                name = name,
                                count = count
                            )
                        )
                    }

                    val poll = Poll(
                        question = question,
                        count = totalCount,
                        answers = answers
                    )

                    callback.onSuccess(poll)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback.onError()
                }
            })
    }

    override fun vote(
        pollId: String,
        optionId: Int,
        response: MainPollCallback<Unit>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val pollRef = database
                    .child(Constants.POLLS)
                    .child(pollId)

                val updates = mapOf(
                    Constants.COUNT to ServerValue.increment(1),
                    "$optionId/${Constants.COUNT}" to ServerValue.increment(1)
                )

                pollRef.updateChildren(updates).await()

                withContext(Dispatchers.Main) {
                    response.onSuccess(Unit)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    response.onError()
                }
            }
        }
    }

}