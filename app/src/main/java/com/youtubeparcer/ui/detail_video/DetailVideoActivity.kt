package com.youtubeparcer.ui.detail_video

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.SparseArray
import androidx.lifecycle.Observer
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.exoplayer2.Player
import com.youtubeparcer.R
import com.youtubeparcer.base.BaseActivity
import com.youtubeparcer.model.Playlist
import com.youtubeparcer.model.PlaylistItem
import com.youtubeparcer.utils.CallBacks
import com.youtubeparcer.utils.PlayerManager
import com.youtubeparcer.utils.YoutubeVideo
import kotlinx.android.synthetic.main.activity_detail_video.*
import org.koin.android.ext.android.inject

class DetailVideoActivity : BaseActivity(R.layout.activity_detail_video), CallBacks {


    private val viewModel by inject<DetailVideoViewModel>()
    private lateinit var player: Player
    private lateinit var playerManager: PlayerManager
    private var listOfFormatVideo = mutableListOf<YoutubeVideo>()

    override fun setupUI() {
        setupExoPlayer()
        downloadAction()
        //setupToolbar()
    }

    private fun setupExoPlayer() {
        playerManager = PlayerManager.getSharedInstance(this)
        player = playerManager.playerView.player
    }

    private  fun downloadAction(){
        download.setOnClickListener {  }
    }

    override fun setupLiveData() {
        subscribeToDetailVideo()
    }


    //подписка на получение видео
    private fun subscribeToDetailVideo() {
        videoId?.let {
            viewModel.fetchVideoById(it).observe(this, Observer { playlist ->
                setupViews(getNeededVideo(playlist))

            })
        }
    }


    private fun getNeededVideo(dto: Playlist?): PlaylistItem {
        if (dto == null) return PlaylistItem()
        for (i in dto.items) {
            if (i.id == videoId) {
                return i
            }
        }
        return PlaylistItem()
    }


    private fun setupViews(dto: PlaylistItem) {

        video_title.text = dto.snippet?.title
        video_subtitle.text = dto.snippet?.description
        getActualUrl(dto.id)
    }


    //этот метод получает весь список видео
    @SuppressLint("StaticFieldLeak")
    private fun getActualUrl(url: String?) {
        object : YouTubeExtractor(this) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                var i = 0
                var itag: Int
                if (ytFiles != null) {
                    while (i < ytFiles.size()) {
                        itag = ytFiles.keyAt(i)
                        var ytFile = ytFiles.get(itag)
                        if (ytFile.format.height == -1 || ytFile.format.height >= 360) {
                            addFormatToList(ytFile, ytFiles)

                        }

                        i++

                    }

                }
                val videoUrl: YoutubeVideo? = listOfFormatVideo.last()
                playVideo(videoUrl?.videoFile?.url)
            }
        }.extract(url, true, true)

    }

    private fun addFormatToList(ytFile: YtFile, ytFiles: SparseArray<YtFile>) {
        var height = ytFile.format.height
        if (height != -1) {
            for (video in listOfFormatVideo) {
                if (video?.height == height && (video.videoFile == null || video.videoFile!!.format.fps == ytFile.format.fps)) {
                    return
                }

            }
            val yVideo = YoutubeVideo()
            yVideo.height = height
            if (ytFile.format.isDashContainer) {
                if (height > 0) {
                    yVideo.videoFile = ytFile
                    yVideo.audioFile = ytFiles.get(140)
                } else {
                    yVideo.audioFile = ytFile
                }
            }

            listOfFormatVideo.add(yVideo)
        }
    }


    private fun playVideo(url: String?) {
        player_view?.player = player
        PlayerManager.getSharedInstance(this).playStream(url)
        PlayerManager.getSharedInstance(this).setPlayerListener(this)


    }


    companion object {
        private var videoId: String? = null
        fun instance(activity: Activity, id: String?) {
            val intent = Intent(activity, DetailVideoActivity::class.java)
            activity?.startActivity(intent)
            this.videoId = id
        }

    }

    override fun onItemClickOnItem(albumId: Int) {
    }

    override fun onPlayingEnd() {
    }
}