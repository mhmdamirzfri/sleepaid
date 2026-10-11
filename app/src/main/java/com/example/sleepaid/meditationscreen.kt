package com.example.sleepaid

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepaid.data.meditationSessions
import com.example.sleepaid.ui.theme.*

@Composable
fun MeditationScreen(
    onBack: () -> Unit,
    onHome: () -> Unit
) {
    var sessionIndex by rememberSaveable { mutableIntStateOf(0) }
    var stepIndex by rememberSaveable { mutableIntStateOf(0) }

    val session = meditationSessions[sessionIndex]
    val step = session.steps[stepIndex]
    val isFirst = stepIndex == 0
    val isLast = stepIndex == session.steps.lastIndex

    SleepAidScaffold(title = "Meditation", onBack = onBack, onHome = onHome) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Session chips
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                meditationSessions.forEachIndexed { index, s ->
                    ChoiceChip(
                        label = s.shortName,
                        selected = sessionIndex == index,
                        onClick = {
                            sessionIndex = index
                            stepIndex = 0
                        }
                    )
                }
            }

            Text(session.title, color = Navy, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("About ${session.minutes} min", color = Navy.copy(alpha = 0.7f))
            Text(session.intro, color = Navy)

            LinearProgressIndicator(
                progress = { (stepIndex + 1f) / session.steps.size },
                modifier = Modifier.fillMaxWidth()
            )
            Text("Step ${stepIndex + 1} of ${session.steps.size}", color = Navy.copy(alpha = 0.7f))

            // Current instruction
            InfoCard(color = Color.White) {
                Text(step.text, color = Navy, fontSize = 20.sp, lineHeight = 28.sp)
            }

            if (isLast) {
                InfoCard {
                    Text("Session complete. Sleep well!", color = Navy, fontWeight = FontWeight.Bold)
                }
            }

            // Back & Next
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = { stepIndex-- },
                    enabled = !isFirst,
                    modifier = Modifier.weight(1f)
                ) { Text("Back") }

                Button(
                    onClick = {
                        if (isLast) stepIndex = 0   // start again
                        else stepIndex++
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (isLast) "Start again" else "Next", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}