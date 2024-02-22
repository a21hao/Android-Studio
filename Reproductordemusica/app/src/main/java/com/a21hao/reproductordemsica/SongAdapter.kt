package com.a21hao.reproductordemsica

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(private val songs: List<Song>) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    private var mediaPlayer: MediaPlayer? = null
    private var currentPlayingPosition: Int = -1
    private var isPaused: Boolean = false
    private var isUserSeeking: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]

        holder.titleTextView.text = song.title

        holder.playButton.setOnClickListener {
            val adapterPosition = holder.adapterPosition
            if (adapterPosition != currentPlayingPosition) {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer.create(holder.itemView.context, song.resourceId)
                mediaPlayer?.start()
                holder.seekBar.max = mediaPlayer?.duration ?: 0
                updateSeekBar(holder)
                currentPlayingPosition = adapterPosition
            } else {
                if (isPaused) {
                    mediaPlayer?.start()
                    isPaused = false
                } else {
                    mediaPlayer?.pause()
                    isPaused = true
                }
            }
        }

        holder.pauseButton.setOnClickListener {
            mediaPlayer?.pause()
            isPaused = true
        }

        holder.stopButton.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            currentPlayingPosition = -1
            holder.seekBar.progress = 0
        }

        holder.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                isUserSeeking = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                isUserSeeking = false
            }
        })
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    private fun updateSeekBar(holder: ViewHolder) {
        val mediaPlayer = mediaPlayer ?: return
        Thread {
            while (mediaPlayer.isPlaying || isUserSeeking) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                if (!isUserSeeking) {
                    holder.seekBar.post {
                        holder.seekBar.progress = mediaPlayer.currentPosition
                    }
                }
            }
        }.start()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val playButton: Button = itemView.findViewById(R.id.playButton)
        val pauseButton: Button = itemView.findViewById(R.id.pauseButton)
        val stopButton: Button = itemView.findViewById(R.id.stopButton)
        val seekBar: SeekBar = itemView.findViewById(R.id.seekBar)
    }
}
