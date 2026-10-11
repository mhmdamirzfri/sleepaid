package com.example.sleepaid

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepaid.ui.theme.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun SleepCycleScreen(
    onBack: () -> Unit,
    onHome: () -> Unit
) {

    var mode by rememberSaveable { mutableIntStateOf(0) }
    var hour by rememberSaveable { mutableIntStateOf(7) }
    var minute by rememberSaveable { mutableIntStateOf(0) }

    val context = LocalContext.current
    val timeFormat = remember { DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH) }

    val chosenTime = LocalTime.of(hour, minute)
    val results = if (mode == 0) {
        SleepCalculator.bedtimesFor(chosenTime)
    } else {
        SleepCalculator.wakeTimesFor(chosenTime)
    }

    fun useCurrentTime() {
        val now = LocalTime.now()
        hour = now.hour
        minute = now.minute
    }

    fun pickTime() {
        TimePickerDialog(
            context,
            { _, pickedHour, pickedMinute ->
                hour = pickedHour
                minute = pickedMinute
            },
            hour,
            minute,
            false
        ).show()
    }

    SleepAidScaffold(title = "Sleep Cycle", onBack = onBack, onHome = onHome) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            SectionTitle("I want to...")

            // Mode chips
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ChoiceChip(
                    label = "Wake up at",
                    selected = mode == 0,
                    onClick = { mode = 0 }
                )
                ChoiceChip(
                    label = "Go to bed at",
                    selected = mode == 1,
                    onClick = {
                        mode = 1
                        useCurrentTime()
                    }
                )
            }

            Button(
                onClick = { pickTime() },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
            ) {
                Icon(Icons.Filled.Schedule, contentDescription = null)
                Spacer(Modifier.width(10.dp))
                Text(chosenTime.format(timeFormat), fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }

            if (mode == 1) {
                TextButton(onClick = { useCurrentTime() }) {
                    Text("Use current time", color = PeriwinkleDark, fontWeight = FontWeight.SemiBold)
                }
            }

            SectionTitle(if (mode == 0) "Try going to bed at:" else "Try waking up at:")

            results.forEach { suggestion ->
                InfoCard(color = if (suggestion.isRecommended) Color.White else CreamLight) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = suggestion.time.format(timeFormat),
                            color = Navy,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        if (suggestion.isRecommended) {
                            Text(
                                "Recommended",
                                color = SuccessGreen,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Text(
                        "${suggestion.cycles} cycles · ${formatHours(suggestion.hoursOfSleep)} of sleep",
                        color = Navy.copy(alpha = 0.8f)
                    )
                }
            }

            InfoCard {
                Text("How does this work?", color = Navy, fontWeight = FontWeight.Bold)
                Text(
                    "Sleep happens in cycles of about 90 minutes. Waking up at the end of a " +
                            "cycle, instead of in the middle of one, helps you feel less groggy. " +
                            "It takes most people about 14 minutes to fall asleep, so that's " +
                            "included. Adults should aim for 5–6 cycles (7.5–9 hours).",
                    color = Navy
                )
            }
        }
    }
}

private fun formatHours(hours: Double): String =
    if (hours % 1.0 == 0.0) "${hours.toInt()} hours" else "$hours hours"