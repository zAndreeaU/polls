package com.andreeau.polls.ui.presentation.views

import android.content.res.Resources
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.amethyst.polls.R

import com.andreeau.polls.ui.theme.PollsAppTheme
import com.andreeau.pollscomponents.components.buttons.SmallButton
import com.andreeau.pollscomponents.components.textLabels.MediumLabel

@Composable
@Preview

fun OfflineScreen(onQuitApp: () -> Unit={}) {

    val offlineText= stringResource(R.string.offline_text)
    val exitText= stringResource(R.string.quit_btn_text)

    PollsAppTheme() {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Spacer(Modifier.padding(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_no_internet),
                    contentDescription = offlineText,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                    modifier = Modifier
                        .size(160.dp)
                )

                Spacer(Modifier.padding(20.dp))

                MediumLabel(labelText = offlineText)
                Spacer(Modifier.padding(20.dp))

                SmallButton(onClick = {onQuitApp()},exitText)


            }


        }
    }

}