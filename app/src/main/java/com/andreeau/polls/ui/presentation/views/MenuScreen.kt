package com.andreeau.polls.ui.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andreeau.polls.ui.theme.PollsAppTheme
import com.amethyst.polls.R
import com.andreeau.pollscomponents.components.buttons.LargeButton

@Composable
@Preview
fun MenuScreen(onCreatePoll: () -> Unit = {}, onVotePoll: (String) -> Unit = {}) {
    PollsAppTheme() {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            var showPollCodeDialog by remember {
                mutableStateOf(false)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(R.drawable.banner),
                    contentDescription = stringResource(R.string.banner_voiceover),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 50.dp),
                    contentScale = ContentScale.FillWidth
                )
                LargeButton(text = stringResource(R.string.create_poll)) { onCreatePoll() }
                LargeButton(text = stringResource(R.string.vote_poll)) { showPollCodeDialog = true }

                if (showPollCodeDialog) {
                    PollCodeDialog(
                        onCodeEntered = { code ->

                            showPollCodeDialog = false

                            onVotePoll(code)
                        },

                        onDismiss = {
                            showPollCodeDialog = false
                        }
                    )
                }
            }
        }
    }
}