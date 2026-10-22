package com.andreeau.polls.ui.presentation.views.Screens

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amethyst.polls.R
import com.andreeau.polls.ui.presentation.views.viewModels.PollResultScreenViewModel
import com.andreeau.polls.ui.theme.Blue
import com.andreeau.polls.ui.theme.Brown
import com.andreeau.polls.ui.theme.Coral
import com.andreeau.polls.ui.theme.Gray
import com.andreeau.polls.ui.theme.Green
import com.andreeau.polls.ui.theme.Lavender
import com.andreeau.polls.ui.theme.Pink

import com.andreeau.polls.ui.theme.PollsAppTheme
import com.andreeau.polls.ui.theme.SoftYellow
import com.andreeau.pollscomponents.components.miscelanous.PollHeader
import com.andreeau.pollscomponents.components.progress.PollResultItem
import com.andreeau.pollscomponents.components.textLabels.MediumLabel
import com.andreeau.pollscomponents.components.textLabels.SmallLabel


@Composable
fun ResultsScreen(
    pollCode: String = "",
    onBackToMenu: () -> Unit,
    viewModel: PollResultScreenViewModel = hiltViewModel()
) {
    val resultsState by viewModel.pollResultState.collectAsStateWithLifecycle()
    LaunchedEffect(pollCode) {
        viewModel.observePollResults(pollCode)
    }
    PollsAppTheme() {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top
            ) {
                if (resultsState.showError) {

                } else {
                    PollHeader(
                        imageResource = R.drawable.banner_blank,
                        title = pollCode,
                        subtitle = stringResource(
                            R.string.total_votes_label, resultsState.poll.count
                        )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, start = 16.dp)
                    ) {
                        SmallLabel(resultsState.poll.status)
                    }
                    Spacer(Modifier.height(20.dp))
                    MediumLabel(resultsState.poll.question)
                    Spacer(Modifier.height(50.dp))
                    Column (Modifier.padding(start = 16.dp, end = 16.dp)) {
                        resultsState.poll.answers.forEachIndexed { index, answer ->

                            PollResultItem(
                                progressColor = getPollResultColor(index),
                                title = answer.name,
                                variantVotes = answer.count,
                                totalVotes = resultsState.poll.count
                            )
                            Spacer(Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}

private fun getPollResultColor(index: Int): Color {
    val pollResultColors = listOf(Lavender, SoftYellow, Coral, Blue, Green, Gray, Pink, Brown)
    return pollResultColors[index % pollResultColors.size]
}