package com.etatech.youtubeapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "FOcXmDV_BX0&feature"
const val YOUTUBE_API_KEY = "Youtube  API Key" //TODO: Change To Your Youtube  API Key


class MainActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {


    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youTubePlayer: YouTubePlayer?,
        wasRestored: Boolean
    ) {

        youTubePlayer?.setPlaybackEventListener(playBackEventListener)
        youTubePlayer?.setPlayerStateChangeListener(stateChangedListener)
        if (!wasRestored) {
            youTubePlayer?.loadVideo(YOUTUBE_VIDEO_ID)
        }else{
            youTubePlayer?.play()
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, results: YouTubeInitializationResult?) {

        val RESULT_CODE = 0
        if (results?.isUserRecoverableError == true) {
            results.getErrorDialog(this, RESULT_CODE).show()
        } else {
            val errorMessage = "Initializing Error -> $results"
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

    }

    private val playBackEventListener = object : YouTubePlayer.PlaybackEventListener {
        override fun onSeekTo(p0: Int) {

            Toast.makeText(this@MainActivity,"Seek To $p0",Toast.LENGTH_SHORT).show()

        }

        override fun onBuffering(p0: Boolean) {

            Toast.makeText(this@MainActivity,"Buffering $p0",Toast.LENGTH_SHORT).show()


        }

        override fun onPlaying() {

            Toast.makeText(this@MainActivity,"Video Is Playing",Toast.LENGTH_SHORT).show()

        }

        override fun onStopped() {

            Toast.makeText(this@MainActivity,"Video Stopped",Toast.LENGTH_SHORT).show()

        }

        override fun onPaused() {

            Toast.makeText(this@MainActivity,"Video Paused",Toast.LENGTH_SHORT).show()

        }

    }

    private val stateChangedListener = object : YouTubePlayer.PlayerStateChangeListener{
        override fun onAdStarted() {

            Toast.makeText(this@MainActivity,"Ads Is Playing",Toast.LENGTH_SHORT).show()


        }

        override fun onLoading() {
            Toast.makeText(this@MainActivity,"Ads Is Loading",Toast.LENGTH_SHORT).show()
        }

        override fun onVideoStarted() {
            Toast.makeText(this@MainActivity,"video Is Playing",Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {
            Toast.makeText(this@MainActivity,"video Is loaded",Toast.LENGTH_SHORT).show()
        }

        override fun onVideoEnded() {
            Toast.makeText(this@MainActivity,"Video Ended",Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
            Toast.makeText(this@MainActivity,"Error -> $p0",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = findViewById<ConstraintLayout>(R.id.youtube_layout)

        val player = findViewById<YouTubePlayerView>(R.id.youtube_player)
        player.initialize(YOUTUBE_API_KEY, this)


    }
}
