package com.andreeau.polls.ui.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.andreeau.pollcore.entities.Poll
import com.andreeau.pollscomponents.components.buttons.LargeButton
import com.andreeau.pollscomponents.components.miscelanous.PollHeader

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
                PollHeader(imageResource = R.drawable.banner, "", "")
                Spacer(Modifier.height(50.dp))

                LargeButton(text = stringResource(R.string.create_poll)) { onCreatePoll() }
                LargeButton(text = stringResource(R.string.vote_poll)) { showPollCodeDialog = true }

                if (showPollCodeDialog) {
                    PollCodeDialog(
                        onCodeEntered = { code->

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