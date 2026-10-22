package com.andreeau.polls.ui.presentation.views.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amethyst.polls.R
import com.andreeau.polls.ui.presentation.views.viewModels.PollCodeDdialogViewModel
import com.andreeau.pollscomponents.components.buttons.SmallButton
import com.andreeau.pollscomponents.components.textFields.CenteredTextField
import com.andreeau.pollscomponents.components.textLabels.MediumLabel


@Composable
@Preview
fun PollCodeDialog(
    onCodeEntered: (String) -> Unit = {},
    onPollClosed: (String) -> Unit = {},
    onDismiss: () -> Unit = {},
    viewModel: PollCodeDdialogViewModel = hiltViewModel()
) {
    val pollCodeViewState by viewModel.pollCodeState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val invalidPollCodeMessage = stringResource(R.string.poll_code_error_message)
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.background)
        ) {
            IconButton(
                onClick = { onDismiss() },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_close_24),
                    contentDescription = stringResource(R.string.dismiss_dialog),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                MediumLabel(labelText = stringResource(R.string.enter_poll_code))
                Spacer(modifier = Modifier.height(16.dp))
                CenteredTextField(
                    value = pollCodeViewState.pollCode,
                    onValueChange = { viewModel.onPollCodeChanged(it.take(6))},
                    label = stringResource(R.string.poll_code)
                )

                SmallButton(
                    onClick = {
                        if(pollCodeViewState.isPollClosed){
                            onDismiss()
                            onPollClosed(pollCodeViewState.pollCode)
                        }else if(pollCodeViewState.canProceedToVote){
                            onDismiss()
                            onCodeEntered(pollCodeViewState.pollCode)
                        }else{
                            Toast.makeText(
                                context,
                                invalidPollCodeMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    },
                    text = stringResource(R.string.next)
                )

            }

        }
    }
}
