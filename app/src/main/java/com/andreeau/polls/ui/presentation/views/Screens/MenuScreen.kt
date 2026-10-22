package com.andreeau.polls.ui.presentation.views.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andreeau.polls.ui.theme.PollsAppTheme
import com.amethyst.polls.R
import com.andreeau.pollscomponents.components.buttons.LargeButton
import com.andreeau.pollscomponents.components.miscelanous.PollHeader

@Composable
@Preview
fun MenuScreen(onCreatePoll: () -> Unit = {}, onPollClosed: (String) -> Unit = {},onVotePoll: (String) -> Unit = {}) {
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
                PollHeader(imageResource = R.drawable.banner_blank, title = stringResource(R.string.app_name), "")
                Spacer(Modifier.height(50.dp))

                LargeButton(text = stringResource(R.string.create_poll)) { onCreatePoll() }
                LargeButton(text = stringResource(R.string.vote_poll)) { showPollCodeDialog = true }

                if (showPollCodeDialog) {
                    PollCodeDialog(
                        onCodeEntered = { code->

                            showPollCodeDialog = false

                            onVotePoll(code)
                        },
                        onPollClosed = { code ->
                            showPollCodeDialog = false
                            onPollClosed(code)
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