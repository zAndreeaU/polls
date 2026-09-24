package com.andreeau.pollcore.entities

data class Poll(
    val status : String= "",
    val question: String = "",
    val count: Int = 0,
    val answers: List<AnswerOption> = emptyList()

)