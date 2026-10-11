package com.example.sleepaid

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sleepaid.data.natureSounds
import com.example.sleepaid.ui.theme.*

@Composable
fun SoundsScreen(
    onBack: () -> Unit,
    onHome: () -> Unit
) {
    // ---------- State ----------
    var playingId by remember { mutableStateOf<Int?>(null) }   // which sound is on (null = none)
    var volume by remember { mutableFloatStateOf(0.7f) }

    val context = LocalContext.current
    val player = remember { SoundPlayer(context) }             // one player for this screen

    // Stop the sound when you leave the screen
    DisposableEffect(Unit) {
        onDispose { player.stop() }
    }

    // Tap the same sound = stop it. Tap a different sound = switch to it.
    fun toggle(resId: Int) {
        if (playingId == resId) {
            player.stop()
            playingId = null
        } else {
            player.play(resId)
            playingId = resId
        }
    }

    // ---------- Screen ----------
    SleepAidScaffold(title = "Relaxing Sounds", onBack = onBack, onHome = onHome) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                "Pick a sound to play. It repeats until you press stop.",
                color = Navy,
                fontSize = 16.sp
            )

            // Volume
            InfoCard {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.AutoMirrored.Filled.VolumeUp, contentDescription = null, tint = Navy)
                    Spacer(Modifier.width(8.dp))
                    Text("Volume", color = Navy, fontWeight = FontWeight.SemiBold)
                }
                Slider(
                    value = volume,
                    onValueChange = {
                        volume = it
                        player.setVolume(it)
                    }
                )
            }

            // One row per sound
            natureSounds.forEach { sound ->
                val isPlaying = playingId == sound.resId

                InfoCard(color = if (isPlaying) Color.White else CreamLight) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.MusicNote, contentDescription = null, tint = PeriwinkleDark)
                        Spacer(Modifier.width(12.dp))

                        Column(Modifier.weight(1f)) {
                            Text(sound.name, color = Navy, fontWeight = FontWeight.Bold)
                            Text(
                                if (isPlaying) "Playing…" else sound.description,
                                color = Navy.copy(alpha = 0.7f),
                                fontSize = 13.sp
                            )
                        }

                        FilledIconButton(onClick = { toggle(sound.resId) }) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Filled.Stop else Icons.Filled.PlayArrow,
                                contentDescription = if (isPlaying) "Stop" else "Play"
                            )
                        }
                    }
                }
            }

            Text(
                "Sounds from Pixabay.",
                color = Navy.copy(alpha = 0.6f),
                fontSize = 12.sp
            )
        }
    }
}