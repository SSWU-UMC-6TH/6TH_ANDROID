package com.example.flo.ui.main.locker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
// import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flo.R
import com.example.flo.data.entities.Song

class LockerAlbumRVAdapter : RecyclerView.Adapter<LockerAlbumRVAdapter.ViewHolder>() {

    private var songs: ArrayList<Song> = ArrayList()

    // ViewHolder 클래스 정의
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // val albumImage: ImageView = itemView.findViewById(R.id.item_album_img_iv)
        val albumTitle: TextView = itemView.findViewById(R.id.item_album_title_tv)
        val albumSinger: TextView = itemView.findViewById(R.id.item_album_singer_tv)
        /*val albumInfo: TextView = itemView.findViewById(R.id.item_album_music_title_info_tv)
        val playButton: ImageView = itemView.findViewById(R.id.item_album_play_iv)
        val moreButton: ImageView = itemView.findViewById(R.id.item_album_more_iv)*/

        fun bind(song: Song) {
            // albumImage.setImageResource(song.imageResource) // 이미지는 Song 클래스에 있는 리소스를 사용한다고 가정
            albumTitle.text = song.title
            albumSinger.text = song.singer
            // albumInfo.text = song.albumInfo // 예시를 위해 추가, 필요에 따라 변경
        }
    }

    // onCreateViewHolder: 새로운 뷰를 생성할 때 호출됩니다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_locker_album, parent, false)
        return ViewHolder(view)
    }

    // onBindViewHolder: 각 항목을 데이터와 바인딩할 때 호출됩니다.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(songs[position])
    }

    // getItemCount: 항목의 총 개수를 반환합니다.
    override fun getItemCount(): Int {
        return songs.size
    }

    // addSongs: 데이터를 추가하고 어댑터에 변경을 알립니다.
    fun addSongs(songs: ArrayList<Song>) {
        this.songs = songs
        notifyDataSetChanged()
    }
}
