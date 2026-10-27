package com.andreeau.polls.ui.presentation.views.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amethyst.polls.R
import com.andreeau.polls.ui.presentation.views.viewModels.CreatePollScreenViewModel
import com.andreeau.polls.ui.theme.PollsAppTheme
import com.andreeau.pollscomponents.components.buttons.SmallButton
import com.andreeau.pollscomponents.components.miscelanous.PollHeader
import com.andreeau.pollscomponents.components.textFields.BasicTextField
import com.andreeau.pollscomponents.components.textFields.OutlinedTextField


@Composable
fun CreatePollScreen(
    onPollCreated: (pollId: String) -> Unit = {},
    viewModel: CreatePollScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val pollDataErrorText = stringResource(R.string.poll_data_error)
    val pollCreationError = stringResource(R.string.poll_creation_error)
    val state by viewModel.createPollState.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }
    PollsAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .imePadding()
            ) {
                PollHeader(
                    imageResource = R.drawable.banner_blank,
                    title = stringResource(R.string.create_poll),
                    subtitle = ""
                )
                // Scrollable content
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp)
                        .verticalScroll(rememberScrollState())
                ) {


                    Spacer(Modifier.height(30.dp))

                    BasicTextField(
                        value = state.pollquestion,
                        onValueChange = { viewModel.updateQuestion(it) },
                        label = stringResource(R.string.Type_question_label)
                    )
                    Spacer(Modifier.height(30.dp))
                    state.pollAnswers.forEachIndexed { index, answer ->
                        LaunchedEffect(state.answerToFocus) {
                            if (state.answerToFocus == index) {
                                focusRequester.requestFocus()
                            }
                        }
                        OutlinedTextField(
                            value = answer,
                            onValueChange = { newAnswer ->
                                viewModel.updateAnswer(index, newAnswer)
                            },
                            label = stringResource(R.string.Type_answer_label)
                        )
                        Spacer(Modifier.height(3.dp))
                    }
                }

                // Fixed bottom button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    SmallButton(modifier = Modifier.weight(1f),
                        text = stringResource(R.string.add_answer_btn_text),
                        onClick = {
                            viewModel.addAnswer()
                        })

                    SmallButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.finish_btn_text),
                        onClick = {
                            viewModel.createPoll()
                        })

                    LaunchedEffect(state.pollCreated) {
                        if(state.pollCreated){
                            onPollCreated(state.pollId)
                        }
                    }
                    LaunchedEffect(state.showError) {
                        if(state.showError){
                            Toast.makeText(
                                context,
                                pollDataErrorText,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    LaunchedEffect(state.pollCreationError) {
                        if(state.pollCreationError){
                            Toast.makeText(
                                context,
                                pollCreationError,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }
        }
    }
}