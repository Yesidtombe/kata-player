package com.dojo.globant.mycustomplayer.common.util

import com.dojo.globant.mycustomplayer.feature.home.domain.models.Album
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Artist
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Track
import org.json.JSONObject

fun Track.toJson() : JSONObject {
    val json = JSONObject()
    json.put("id", id)
    json.put("title", title)
    json.put("artist", artist.name)
    json.put("cover", album.cover)
    json.put("preview", preview)
    return json
}

fun JSONObject.toTrack() : Track {
    return Track(
        id = getLong("id"),
        title = getString("title"),
        duration = 0,
        artist = Artist(0, getString("artist"), null, ""),
        album = Album(0, "", getString("cover"), ""),
        preview = getString("preview"),
        favorite = true
    )
}

fun Float.toMinSecTime() : Pair<Int, Int> {
    val min = (this / 60000F).toInt()
    val sec = (this.mod(60000F)/1000).toInt()
    return Pair(min, sec)
}