package com.example.sleepaid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepaid.data.SleepTips
import com.example.sleepaid.ui.theme.*
import java.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material3.OutlinedButton

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    val greeting = remember {
        greetingFor(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
    }
    val tip = remember { SleepTips.tipOfTheDay() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .verticalScroll(rememberScrollState())
    ) {
        //Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                .background(Periwinkle)
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(top = 16.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTitle(text = "Sleep Aid", fontSize = 38.sp)
            Spacer(Modifier.height(8.dp))
            Image(
                painter = painterResource(R.drawable.sleepaidlogo),
                contentDescription = "Sleepy moon mascot",
                modifier = Modifier.size(150.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Build healthier sleeping habits",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    // soft shadow so white text reads on light blue
                    shadow = Shadow(Navy.copy(alpha = 0.5f), Offset(0f, 2f), blurRadius = 4f)
                )
            )
        }

        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(greeting, color = Navy, fontSize = 20.sp, fontWeight = FontWeight.Bold)

            MenuButton(
                title = "Sleep Tools",
                subtitle = "Sleep cycle and water intake calculators",
                icon = Icons.Filled.Bedtime,
                onClick = { onNavigate(Routes.SLEEP_TOOLS) }
            )
            MenuButton(
                title = "Relaxation Tools",
                subtitle = "Breathing, meditation and sounds",
                icon = Icons.Filled.SelfImprovement,
                onClick = { onNavigate(Routes.RELAXATION_TOOLS) }
            )
            MenuButton(
                title = "Sleep Information",
                subtitle = "Learn how sleep works",
                icon = Icons.AutoMirrored.Filled.MenuBook,
                onClick = { onNavigate(Routes.SLEEP_INFO) }
            )
            MenuButton(
                title = "Quiz",
                subtitle = "Test your sleep knowledge",
                icon = Icons.Filled.Quiz,
                onClick = { onNavigate(Routes.QUIZ) }
            )

            //Tip
            InfoCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Lightbulb, contentDescription = null, tint = PeriwinkleDark)
                    Spacer(Modifier.width(8.dp))
                    Text("Tip of the day", color = Navy, fontWeight = FontWeight.Bold)

                }
                Text(tip, color = Navy, fontSize = 15.sp)
            }

            //Help button
            OutlinedButton(
                onClick = { onNavigate(Routes.HELP) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.AutoMirrored.Outlined.HelpOutline, contentDescription = null, tint = Navy)
                Spacer(Modifier.width(8.dp))
                Text("Help", color = Navy, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
    }
}

private fun greetingFor(hour: Int): String = when (hour) {
    in 5..11 -> "Good morning!"
    in 12..17 -> "Good afternoon!"
    in 18..21 -> "Good evening!"
    else -> "It's late. Time to wind down."
}