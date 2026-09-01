package com.andreeau.polls.ui.views

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amethyst.polls.R
import com.andreeau.polls.ui.theme.PollsAppTheme
import com.andreeau.pollscomponents.components.buttons.LargeButton
import com.andreeau.pollscomponents.components.buttons.RegularButton
import com.andreeau.pollscomponents.components.buttons.SmallButton
import com.andreeau.pollscomponents.components.progress.PollResultItem
import com.andreeau.pollscomponents.components.textFields.BasicTextField
import com.andreeau.pollscomponents.components.textFields.CenteredTextField
import com.andreeau.pollscomponents.components.textFields.OutlinedTextField
import com.andreeau.pollscomponents.components.textLabels.LargeLabel
import com.andreeau.pollscomponents.components.textLabels.MediumLabel
import com.andreeau.pollscomponents.components.textLabels.SmallLabel
import com.andreeau.pollscomponents.ui.SoftYellow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PollsAppTheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    innerPadding

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(
                                rememberScrollState()
                            )
                    ) {
                        LargeButton(onClick = {}, text = "Create poll")
                        Row(
                            Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            SmallButton(onClick = {}, "add", Modifier.weight(1f))
                            SmallButton(onClick = {}, "finish", Modifier.weight(1f))

                        }


                        var question by remember {
                            mutableStateOf("This is an example")
                        }

                        var question2 by remember {
                            mutableStateOf("")
                        }
                        var question3 by remember {
                            mutableStateOf("")
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Transparent)
                                .padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(40.dp)
                        ) {

                            BasicTextField(
                                value = question2,
                                onValueChange = { question2 = it },
                                label = "Type your question"
                            )

                            OutlinedTextField(
                                value = question,
                                onValueChange = { question = it },
                                label = "Type your question"
                            )
                            CenteredTextField(
                                value = question3,
                                onValueChange = { question3 = it },
                                label = "Type your question"
                            )
                        }

                        Box(Modifier.padding(16.dp)) {
                            PollResultItem()
                        }

                        Box(Modifier.padding(16.dp)) {
                            PollResultItem(
                                progressColor = SoftYellow,
                                title = "Variant 2",
                                variantVotes = 7,
                                totalVotes = 10
                            )
                        }

                        RegularButton(onClick = {}, text = "variant 1")
                        RegularButton(onClick = {}, text = "variant 1")
                        RegularButton(onClick = {}, text = "variant 1")

                        LargeLabel("Title")
                        MediumLabel("This is an exapmple for medium label")
                        SmallLabel("this is a small label example")

                    }
                }
            }
        }
    }
}