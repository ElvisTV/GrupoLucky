package com.elvistezen.grupolucky

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val videoView: VideoView = findViewById(R.id.splashVideoView)
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.fondocielo)
        videoView.setVideoURI(videoUri)
        videoView.setOnCompletionListener {
            navigateToMain()
        }

        val mediaPlayer = MediaPlayer.create(this, R.raw.fondocielo)
        mediaPlayer.start()

        videoView.start()
    }

    private fun navigateToMain() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
