package com.example.sleepaid

import android.content.Context
import android.media.MediaPlayer

class SoundPlayer(private val context: Context) {

    private var player: MediaPlayer? = null
    private var volume = 0.7f

    fun play(resId: Int) {
        stop()
        player = MediaPlayer.create(context, resId)?.apply {
            isLooping = true
            setVolume(volume, volume)
            start()
        }
    }

    fun setVolume(value: Float) {
        volume = value
        player?.setVolume(value, value)
    }

    fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}