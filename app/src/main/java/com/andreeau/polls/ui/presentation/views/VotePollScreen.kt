package com.andreeau.polls.ui.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amethyst.polls.R
import com.andreeau.polls.ui.presentation.views.viewModels.VotePollScreenViewModel
import com.andreeau.polls.ui.theme.PollsAppTheme
import com.andreeau.pollscomponents.components.buttons.RegularButton
import com.andreeau.pollscomponents.components.buttons.SmallButton
import com.andreeau.pollscomponents.components.miscelanous.PollHeader
import com.andreeau.pollscomponents.components.textLabels.LargeLabel
import com.andreeau.pollscomponents.components.textLabels.MediumLabel

@Composable
@Preview
fun VotePollScreen(
    pollCode: String = "", onVoteCompleted: () -> Unit = {},
    viewModel: VotePollScreenViewModel = hiltViewModel()
) {
    PollsAppTheme() {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            viewModel.retrieveData(pollCode)
            val votePollViewState by viewModel.votePollState.collectAsStateWithLifecycle()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top
            ) {
                if (votePollViewState.showError) {

                } else {
                    PollHeader(
                        imageResource = R.drawable.banner_blank,
                        title = stringResource(R.string.vote_poll),
                        subtitle = pollCode
                    )
                    MediumLabel(votePollViewState.poll.question)
                    Spacer(Modifier.height(50.dp))

                    votePollViewState.poll.answers.forEachIndexed { index, answerOption ->
                        RegularButton(onClick = {
                            viewModel.voteAnswer(pollCode, index + 1)
                        }, text = answerOption.name)

                    }
                    if (votePollViewState.canProceed) {
                        onVoteCompleted()
                    }

                }
            }
        }
    }
}