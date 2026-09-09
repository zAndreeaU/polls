package com.andreeau.pollcore.entities


data class Question(
    val question: String = "",
    val answers: List<AnswerOption> = emptyList()
)