package com.andreeau.pollcore.entities

data class Poll(
    val question: String = "",
    val count: Int = 0,
    val answers: List<AnswerOption> = emptyList()
)