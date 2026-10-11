package com.example.sleepaid

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepaid.ui.theme.*

@Composable
fun WaterIntakeScreen(
    onBack: () -> Unit,
    onHome: () -> Unit
) {
    var weightText by rememberSaveable { mutableStateOf("") }               // what's typed
    var activity by rememberSaveable { mutableStateOf(ActivityLevel.LOW) }
    var hotWeather by rememberSaveable { mutableStateOf(true) }             // on by default
    var resultMl by rememberSaveable { mutableIntStateOf(0) }               // 0 = not calculated
    var showError by rememberSaveable { mutableStateOf(false) }
    var glassesDrunk by rememberSaveable { mutableIntStateOf(0) }

    val focusManager = LocalFocusManager.current   // used to hide the keyboard

    fun calculate() {
        val weight = WaterCalculator.parseWeight(weightText)
        if (weight == null) {
            showError = true
            resultMl = 0
        } else {
            showError = false
            resultMl = WaterCalculator.dailyIntakeMl(weight, activity, hotWeather)
            focusManager.clearFocus()   // hide the keyboard
        }
    }

    SleepAidScaffold(title = "Water Intake", onBack = onBack, onHome = onHome) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding()                 // move content above the keyboard
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Weight
            SectionTitle("Your weight")
            OutlinedTextField(
                value = weightText,
                onValueChange = {
                    weightText = it
                    showError = false
                },
                label = { Text("Weight (kg)") },
                singleLine = true,
                isError = showError,
                supportingText = {
                    if (showError) Text("Enter a weight between 20 and 300 kg")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,   // number keyboard
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { calculate() }),
                modifier = Modifier.fillMaxWidth()
            )

            // Activity level
            SectionTitle("Activity level")
            Column(Modifier.selectableGroup()) {
                ActivityLevel.entries.forEach { level ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = activity == level,
                                onClick = { activity = level },
                                role = Role.RadioButton
                            )
                            .padding(vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = activity == level, onClick = null)
                        Spacer(Modifier.width(8.dp))
                        Text(level.label, color = Navy)
                    }
                }
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier.weight(1f)) {
                    Text("Hot or humid weather", color = Navy, fontWeight = FontWeight.SemiBold)
                    Text("Adds 500 ml", color = Navy.copy(alpha = 0.7f), fontSize = 13.sp)
                }
                Switch(checked = hotWeather, onCheckedChange = { hotWeather = it })
            }

            // Calculate
            Button(
                onClick = { calculate() },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Calculate", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            // Result
            if (resultMl > 0) {
                val goal = WaterCalculator.glassesFor(resultMl)

                InfoCard(color = Color.White) {
                    Text("You need about", color = Navy)
                    Text(
                        "${resultMl / 1000.0} litres",
                        color = Navy,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text("per day ≈ $goal glasses (250 ml each)", color = Navy.copy(alpha = 0.8f))
                }
                }


            InfoCard {
                Text("Water and sleep", color = Navy, fontWeight = FontWeight.Bold)
                Text(
                    "Not drinking enough can leave you with a dry mouth, headaches or cramps " +
                            "that disturb your sleep. Drink steadily through the day, and have less " +
                            "in the last hour before bed so you don't wake up to use the toilet.",
                    color = Navy
                )
                Text(
                    "This is a general estimate, not medical advice. If you have kidney or " +
                            "heart problems, or are pregnant, follow your doctor's advice.",
                    color = Navy.copy(alpha = 0.7f),
                    fontSize = 12.sp
                )
            }
        }
    }
}