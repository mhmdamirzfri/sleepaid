package com.example.sleepaid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepaid.data.breathingPatterns
import com.example.sleepaid.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun BreathingScreen(
    onBack: () -> Unit,
    onHome: () -> Unit
) {
    // ---------- State ----------
    var selected by rememberSaveable { mutableIntStateOf(0) }   // which pattern
    var running by remember { mutableStateOf(false) }
    var phaseText by remember { mutableStateOf("Press start") }
    var secondsLeft by remember { mutableIntStateOf(0) }
    var rounds by remember { mutableIntStateOf(0) }

    val pattern = breathingPatterns[selected]


    LaunchedEffect(running, selected) {
        if (!running) {
            phaseText = "Press start"
            secondsLeft = 0
            return@LaunchedEffect
        }

        rounds = 0
        while (true) {
            for (phase in breathingPatterns[selected].phases) {
                phaseText = phase.label
                for (s in phase.seconds downTo 1) { // count down
                    secondsLeft = s
                    delay(1000)
                }
            }
            rounds++
        }
    }

    SleepAidScaffold(title = "Breathing", onBack = onBack, onHome = onHome) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                breathingPatterns.forEachIndexed { index, p ->
                    ChoiceChip(
                        label = p.name,
                        selected = selected == index,
                        onClick = { selected = index },
                        enabled = !running
                    )
                }
            }

            InfoCard {
                Text(pattern.description, color = Navy)
                Text(
                    "Stop if you feel dizzy and breathe normally.",
                    color = Navy.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
            }


            Box(
                modifier = Modifier
                    .size(220.dp)
                    .clip(CircleShape)
                    .background(Brush.radialGradient(listOf(Periwinkle, PeriwinkleDark))),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(phaseText, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    if (running) {
                        Text("$secondsLeft", color = Color.White, fontSize = 48.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Text("Rounds completed: $rounds", color = Navy, fontWeight = FontWeight.SemiBold)

            Button(
                onClick = { running = !running },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(if (running) "Stop" else "Start", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}