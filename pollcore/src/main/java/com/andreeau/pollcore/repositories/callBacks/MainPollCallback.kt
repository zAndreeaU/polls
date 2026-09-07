package com.andreeau.pollcore.repositories.callBacks

import kotlin.jvm.Throws

interface MainPollCallback<T> {
    fun onSuccess(): T
    fun onError()
}