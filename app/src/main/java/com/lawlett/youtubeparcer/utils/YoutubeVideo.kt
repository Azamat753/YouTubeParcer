package com.lawlett.youtubeparcer.utils

import at.huber.youtubeExtractor.YtFile

data class YoutubeVideo(
    var height:Int= 0,
    var audioFile:YtFile?=null,
    var videoFile: YtFile?=null,
    var isSelected:Boolean=false
)