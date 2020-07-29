package com.lawlett.youtubeparcer.ui.vidio_detail

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.util.SparseArray
import android.view.Window
import android.widget.Button
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.exoplayer2.Player
import com.lawlett.youtubeparcer.R
import com.lawlett.youtubeparcer.extension.showToast
import com.lawlett.youtubeparcer.model.PlaylistItem
import com.lawlett.youtubeparcer.utils.CallBacks
import com.lawlett.youtubeparcer.utils.PlayerManager
import com.lawlett.youtubeparcer.utils.YoutubeVideo
import kotlinx.android.synthetic.main.activity_video_detail.*
import kotlinx.android.synthetic.main.detail_play_list_tool_bar.*
import org.koin.android.ext.android.inject


class DetailVideoActivity : AppCompatActivity(), CallBacks {
    private val STORAGE_PERMISSION_CODE:Int=1000
    private val viewModel by inject<DetailVideoViewModel>()
    var list = mutableListOf<PlaylistItem>()
    private lateinit var player: Player
    private lateinit var playerManager: PlayerManager
    private var listOfFormatVideo = mutableListOf<YoutubeVideo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_detail)
        setupToSubscribe()
        setupExoPlayer()
        backPressedClick()
        downloadClick()
    }

    private fun downloadClick() {
        download_view.setOnClickListener {
            showDialog()

        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        val firstClick = dialog.findViewById(R.id.first_q) as RadioButton
        firstClick.setLinkTextColor(Color.RED)
        val secondCLick = dialog.findViewById(R.id.second_q) as RadioButton
        val thirdClick = dialog.findViewById(R.id.third_q) as RadioButton
        val downloadClick = dialog.findViewById(R.id.download_button) as Button
        secondCLick.setOnClickListener {
            showToast(this, "720p")
            dialog.setCancelable(true)
        }
        thirdClick.setOnClickListener {
            showToast(this, "480p")

        }
        firstClick.setOnClickListener {
            showToast(this, "1080p")
        }
        downloadClick.setOnClickListener {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==
                        PackageManager.PERMISSION_GRANTED){

                    requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),STORAGE_PERMISSION_CODE)
                }else{
startDownloading()
                }
            }else{

            }
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun startDownloading() {
        val url = listOfFormatVideo.last().videoFile?.url
        val request =DownloadManager.Request(Uri.parse(url))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle("Download")
        request.setDescription("The file is downloading...")

        request.allowScanningByMediaScanner()
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"${System.currentTimeMillis()}")

        val manager=getSystemService(Context.DOWNLOAD_SERVICE)as DownloadManager
        manager.enqueue(request)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
       when(requestCode){
           STORAGE_PERMISSION_CODE->{
               if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                   startDownloading()
               }else{
                   showToast(this,"Permission denied!")
               }
           }
       }
    }

    private fun backPressedClick() {
        back.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        var myVideoId: String? = null
        fun instanceVideoDetail(activity: Activity?, id: String?) {
            val intent = Intent(activity, DetailVideoActivity::class.java)
            activity?.startActivity(intent)
            this.myVideoId = id
        }
    }


    private fun setupToSubscribe() {
        myVideoId?.let {
            viewModel.fetchVideoById(it).observe(this, Observer {
                list.addAll(it!!.items)
                title_video.text = list[0].snippet.title
                video_sub_title.text = list[0].snippet.description
                getActualUrl(myVideoId)
            })
        }
    }

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
                Log.e("videoUrl", "onExtractionComplete: ${listOfFormatVideo.last()}")
                playVideo(videoUrl?.videoFile?.url)
            }

        }.extract(url, true, true)
    }

    private fun setupExoPlayer() {
        playerManager = PlayerManager.getSharedInstance(this)
        player = playerManager.playerView.player
    }

    private fun addFormatToList(ytFile: YtFile, ytFiles: SparseArray<YtFile>) {
        var height = ytFile.format.height
        if (height != -1) {
            for (video in listOfFormatVideo) {
                if (video?.height == height && (video?.videoFile == null || video.videoFile!!.format.fps ==
                            ytFile.format.fps)
                ) {
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

    override fun onItemClickOnItem(albumId: Int) {

    }

    override fun onPlayingEnd() {
    }

}
