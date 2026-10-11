package com.example.sleepaid

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepaid.data.quizQuestions
import com.example.sleepaid.ui.theme.*
import androidx.compose.ui.draw.clip

@Composable
fun QuizScreen(
    onBack: () -> Unit,
    onHome: () -> Unit
) {

    var round by rememberSaveable { mutableIntStateOf(0) }

    val questions = remember(round) { quizQuestions.shuffled().take(10) } //shuffles questions
    var index by remember(round) { mutableIntStateOf(0) }
    var chosen by remember(round) { mutableStateOf<Int?>(null) }
    var score by remember(round) { mutableIntStateOf(0) }
    var finished by remember(round) { mutableStateOf(false) }

    SleepAidScaffold(title = "Quiz", onBack = onBack, onHome = onHome) {
        if (finished) {
            QuizResult(
                score = score,
                total = questions.size,
                onTryAgain = { round++ },
                onHome = onHome
            )
        } else {
            val q = questions[index]
            val answered = chosen != null
            val isLast = index == questions.lastIndex

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Progress
                Text("Question ${index + 1} of ${questions.size}", color = Navy.copy(alpha = 0.7f))
                LinearProgressIndicator(
                    progress = { (index + 1f) / questions.size },
                    modifier = Modifier.fillMaxWidth()
                )

                // Question
                Text(q.question, color = Navy, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                // Options
                q.options.forEachIndexed { i, option ->
                    val isCorrect = i == q.answerIndex
                    val isChosen = i == chosen

                    val background = when {
                        answered && isCorrect -> SuccessGreen
                        answered && isChosen -> ErrorRed
                        else -> Color.White
                    }
                    val textColor = if (background == Color.White) Navy else Color.White

                    Surface(
                        onClick = {
                            chosen = i
                            if (isCorrect) score++
                        },
                        enabled = !answered,
                        shape = RoundedCornerShape(14.dp),
                        color = background,
                        shadowElevation = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(option, color = textColor, fontSize = 16.sp, modifier = Modifier.weight(1f))
                            if (answered && isCorrect) {
                                Icon(Icons.Filled.CheckCircle, contentDescription = "Correct", tint = Color.White)
                            } else if (answered && isChosen) {
                                Icon(Icons.Filled.Cancel, contentDescription = "Wrong", tint = Color.White)
                            }
                        }
                    }
                }

                if (answered) {
                    InfoCard {
                        Text(
                            if (chosen == q.answerIndex) "Correct!" else "Not quite.",
                            color = Navy,
                            fontWeight = FontWeight.Bold
                        )
                        Text(q.explanation, color = Navy)
                    }

                    Button(
                        onClick = {
                            if (isLast) {
                                finished = true
                            } else {
                                index++
                                chosen = null
                            }
                        },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Text(
                            if (isLast) "See my score" else "Next question",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

//results
@Composable
fun QuizResult(
    score: Int,
    total: Int,
    onTryAgain: () -> Unit,
    onHome: () -> Unit
) {
    val percent = score * 100 / total
    val message = when {
        score == total -> "Perfect score!"
        percent >= 70 -> "Great job!"
        percent >= 40 -> "Not bad!"
        else -> "Keep learning!"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.sleepaidlogo),
            contentDescription = "Sleep Aid mascot",
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(24.dp))
        )
        Text("Your score", color = Navy.copy(alpha = 0.7f), fontSize = 18.sp)
        Text("$score / $total", color = Navy, fontSize = 48.sp, fontWeight = FontWeight.Bold)
        Text(message, color = Navy, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(
            "Read the Sleep Information articles to learn more.",
            color = Navy.copy(alpha = 0.8f)
        )

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = onTryAgain,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Try again", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        OutlinedButton(
            onClick = onHome,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Home", fontSize = 18.sp)
        }
    }
}