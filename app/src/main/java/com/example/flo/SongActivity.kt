package com.example.flo

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivitySongBinding
import com.google.gson.Gson
import java.util.Timer



class SongActivity(override: Any) : AppCompatActivity() {

    lateinit var binding : ActivitySongBinding
    lateinit var song : Song
    lateinit var timer : Timer

    private var mediaPlayer : MediaPlayer? = null
    private var gson : Gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var title : String? = null
        var singer : String? = null

        if(intent.hasExtra("title") && intent.hasExtra("singer")){
            title = intent.getStringExtra("title")
            singer = intent.getStringExtra("singer")
            binding.songMusicTitleTv.text = title
            binding.songSingerNameTv.text = singer
        }

        binding.songDownIb.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("message", title + " _ " + singer)
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.songMiniplayerIv.setOnClickListener {
            finish()
        }

        binding.songMiniplayerIv.setOnClickListener {
            setPlayerStatus(true)
        }

        binding.songPauseIv.setOnClickListener {
            setPlayerStatus(false)
        }
        if(intent.hasExtra("title")&& intent.hasExtra("singer")){
            binding.songMusicTitleTv.text=intent.getStringExtra("title")
            binding.songSingerNameTv.text=intent.getStringExtra("singer")
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("message", "뒤로가기 버튼 클릭")
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()
    }

    private fun initSong() {
        if(intent.hasExtra("title") && intent.hasExtra("singer")) {
            song = Song(
                intent.getStringExtra("title")!!,
                intent.getStringExtra("singer")!!,
                intent.getIntExtra("second", 0),
                intent.getIntExtra("playTime", 0),
                intent.getBooleanExtra("isPlaying", false),
                intent.getStringExtra("music")!!
            )
        }
        startTimer()
    }

    private fun setPlayer(song : Song) {
        binding.songMusicTitleTv.text = intent.getStringExtra("title")!!
        binding.songSingerNameTv.text = intent.getStringExtra("singer")!!
        binding.songStartTimeTv.text = String.format("%02d:%02d", song.second / 60, song.second % 60)
        binding.songEndTimeTv.text = String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)
        binding.songProgressSb.progress = (song.second * 1000 / song.playTime)

        val music = resources.getIdentifier(song.music, "raw", this.packageName)
        mediaPlayer = MediaPlayer.create(this, music)
        setPlayerStatus(song.isPlaying)
    }

    private fun startTimer() {
        timer = Timer(song.playTime, song.isPlaying)
        timer.start()
    }

    fun setPlayerStatus (isPlaying : Boolean){
        song.isPlaying = isPlaying
        timer.isPlaying = isPlaying

        if(isPlaying){ // 재생중
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
            mediaPlayer?.start()
        } else { // 일시정지
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE

            // 재생 중이 아닐 때, pause를 호출하면 에러가 나기 때문에 이를 방지하기 위한 조건문
            if(mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            }
        }

        inner class Timer(private var playTime: Int, var isPlaying: Boolean = true) : Thread() {
            private var second : Int = 0
            private var mills : Float = 0F

            override fun run() {
                super.run()
                try{
                    while(true) {
                        if(second >= playTime) {
                            break
                        }

                        if(isPlaying) {
                            sleep(50)
                            mills += 50

                            runOnUiThread {
                                // binding.songProgressSb.progress = ((mills/playTime*1000) * 100).toInt()
                                binding.songProgressSb.progress = ((mills/playTime) * 100).toInt()
                            }

                            if(mills % 1000 == 0F) { // 1초
                                runOnUiThread {
                                    binding.songStartTimeTv.text = String.format("%02d:%02d", second / 60, second % 60)
                                }
                                second++
                            }
                        }
                    }

                }catch(InterruptedException){
                    Log.d("Song","쓰레드가 죽었습니다. $(e.message)")
                }


            }
        }
    }
    override fun onPause() {
        super.onPause()
        setPlayerStatus(false)

        song.second = (song.playTime * binding.songProgressSb.progress) / 100000
        val sharedPreferences = getSharedPreferences("song", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val songToJson = gson.toJson(song)
        editor.putString("songData", songToJson)
        Log.d("songData", songToJson.toString())
        editor.apply()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.interrupt()
        mediaPlayer?.release() // 미디어 플레이어가 갖고 있던 리소스를 해제한다.
        mediaPlayer = null // 미디어 플레이어를 해제한다.
    }

}
