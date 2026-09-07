package com.andreeau.polls.ui.presentation.views
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.amethyst.polls.R
import com.andreeau.polls.ui.theme.PollsAppTheme
import com.andreeau.pollscomponents.components.miscelanous.PollHeader


@Composable
@Preview
fun VotePollScreen(pollCode: String = "AB12CD", onVoteCompleted: () -> Unit = {}) {
    PollsAppTheme() {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            innerPadding
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Top
            ) {
                PollHeader(
                    imageResource = R.drawable.banner_blank,
                    title = stringResource(R.string.create_poll),
                    subtitle = pollCode
                )

            }
        }
    }
}