package com.andreeau.polls.ui.presentation.views.viewModels

import androidx.lifecycle.ViewModel
import com.andreeau.polls.ui.presentation.views.viewModels.ViewModelDataClasses.PollCodeVMDataclass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class PollCodeDdialogViewModel @Inject constructor() : ViewModel() {

    private val _pollCodeState = MutableStateFlow(PollCodeVMDataclass())

    val pollCodeState = _pollCodeState.asStateFlow()

    fun onPollCodeChanged(code: String) {
        _pollCodeState.update { it.copy(pollCode = code, canProceed = code.length==6) }
    }

}