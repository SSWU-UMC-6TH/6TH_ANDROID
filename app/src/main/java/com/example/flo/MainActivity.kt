package com.example.flo

import HomeFragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.flo.databinding.ActivityMainBinding
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private var song : Song = Song()
    private var gson : Gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_UMCFlo)

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                if (data != null) {
                    val message = data.getStringExtra("message")
                    Log.d("message", message!!)
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.mainPlayerCl.setOnClickListener {
            val intent = Intent(this, SongActivity::class.java)
            intent.putExtra("title", song.title)
            intent.putExtra("singer",song.singer)
            intent.putExtra("second", song.second)
            intent.putExtra("playTime", song.playTime)
            intent.putExtra("isPlaying", song.isPlaying)
            intent.putExtra("music", song.music)
            activityResultLauncher.launch(intent)
        }

        binding.homePannelBtnMemoIv.setOnClickListener {
            val intent = Intent(requireActivity(), MemoActivity::class.java)
            val activity = requireActivity() // fragment에서 SharedPreferences에 접근하려면 context가 필요함.
            val sharedPreferences = activity.getSharedPreferences("memo", AppCompatActivity.MODE_PRIVATE)
            val tempMemo = sharedPreferences.getString("tempMemo", null)

            if(tempMemo != null) {
                val dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog, null)
                val builder = AlertDialog.Builder(activity)
                    .setView(dialogView)
                    .setTitle("메모 복원하기")

                val alertDialog = builder.show()
                val yesBtn = alertDialog.findViewById<Button>(R.id.yes)
                val noBtn = alertDialog.findViewById<Button>(R.id.no)

                yesBtn!!.setOnClickListener {
                    startActivity(intent)
                }

                noBtn!!.setOnClickListener {
                    val editor = sharedPreferences.edit()
                    editor.remove("tempMemo")
                    editor.apply()
                    startActivity(intent)
                }

            } else {
                startActivity(intent)
            }
        }
    }




    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.lookFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LookFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.lockerFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
    override fun onStart() {
        super.onStart()
        val sharedPreferences = getSharedPreferences("song", MODE_PRIVATE)
        val jsonToSong = sharedPreferences.getString("songData", null)
        Log.d("jsonToSong", jsonToSong.toString())
        song = if(jsonToSong == null) { // 최초 실행 시
            Song("라일락", "아이유(IU)", 0, 60, false, "music_lilac")
        } else { // SongActivity에서 노래가 한번이라도 pause 된 경우
            gson.fromJson(jsonToSong, Song::class.java)
        }

        setMiniPlayer(song)
    }

    private fun setMiniPlayer(song : Song) {
        binding.mainMiniplayerTitleTv.text = song.title
        binding.mainMiniplayerSingerTv.text = song.singer
        binding.mainMiniplayerProgressSb.progress = (song.second * 100000 / song.playTime)
    }
}
