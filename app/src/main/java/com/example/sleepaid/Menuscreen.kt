package com.example.sleepaid

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepaid.ui.theme.Navy

// Sleep Tools menu
@Composable
fun SleepToolsScreen(
    onBack: () -> Unit,
    onHome: () -> Unit,
    onNavigate: (String) -> Unit
) {
    SleepAidScaffold(title = "Sleep Tools", onBack = onBack, onHome = onHome) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "Tools to help you plan your sleep and look after your body.",
                color = Navy,
                fontSize = 16.sp
            )
            MenuButton(
                title = "Sleep Cycle Calculator",
                subtitle = "Find the best time to sleep or wake up",
                icon = Icons.Filled.Schedule,
                onClick = { onNavigate(Routes.SLEEP_CYCLE) }
            )
            MenuButton(
                title = "Water Intake Calculator",
                subtitle = "How much water you need each day",
                icon = Icons.Filled.WaterDrop,
                onClick = { onNavigate(Routes.WATER_INTAKE) }
            )
        }
    }
}


// Relaxation Tools menu

@Composable
fun RelaxationToolsScreen(
    onBack: () -> Unit,
    onHome: () -> Unit,
    onNavigate: (String) -> Unit
) {
    SleepAidScaffold(title = "Relaxation Tools", onBack = onBack, onHome = onHome) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "Calm your mind and body before sleep.",
                color = Navy,
                fontSize = 16.sp
            )
            MenuButton(
                title = "Breathing Exercises",
                subtitle = "Slow your breathing to relax",
                icon = Icons.Filled.Air,
                onClick = { onNavigate(Routes.BREATHING) }
            )
            MenuButton(
                title = "Guided Meditation",
                subtitle = "Step-by-step sessions to unwind",
                icon = Icons.Filled.SelfImprovement,
                onClick = { onNavigate(Routes.MEDITATION) }
            )
            MenuButton(
                title = "Relaxing Sounds",
                subtitle = "White noise, rain, ocean and more",
                icon = Icons.Filled.Headphones,
                onClick = { onNavigate(Routes.SOUNDS) }
            )
        }
    }
}