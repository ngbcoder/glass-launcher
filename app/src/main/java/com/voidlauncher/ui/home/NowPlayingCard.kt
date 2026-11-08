package com.voidlauncher.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.voidlauncher.data.Tracks
import com.voidlauncher.ui.components.GlassSurface
import kotlinx.coroutines.delay

@Composable
fun NowPlayingCard(modifier: Modifier = Modifier) {
    val tracks = Tracks.list
    var index by remember { mutableIntStateOf(0) }
    var playing by remember { mutableStateOf(false) }
    var progress by remember { mutableIntStateOf(0) }
    val track = tracks[index]

    LaunchedEffect(playing, index) {
        if (playing) {
            while (progress < track.durationSec) {
                delay(1000)
                progress++
            }
            // auto-advance
            progress = 0
            index = (index + 1) % tracks.size
        }
    }

    GlassSurface(modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(26.dp)) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Brush.linearGradient(track.gradient.toList()))
            )
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(track.title, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Text(track.artist, color = Color.White.copy(alpha = 0.6f), fontSize = 13.sp)
                Spacer(Modifier.height(8.dp))
                // progress bar (scrubbable)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                        .pointerInput(track.durationSec) {
                            detectTapGestures { offset ->
                                val frac = (offset.x / size.width).coerceIn(0f, 1f)
                                progress = (frac * track.durationSec).toInt()
                            }
                        }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction = (progress.toFloat() / track.durationSec).coerceIn(0f, 1f))
                            .height(4.dp)
                            .background(Color.White)
                    )
                }
                Spacer(Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(formatTime(progress), color = Color.White.copy(alpha = 0.6f), fontSize = 10.sp)
                    Text(formatTime(track.durationSec), color = Color.White.copy(alpha = 0.6f), fontSize = 10.sp)
                }
            }
            Spacer(Modifier.width(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                IconButton(onClick = {
                    if (progress > 3) { progress = 0 } else {
                        index = (index - 1 + tracks.size) % tracks.size
                        progress = 0
                    }
                }) { Icon(Icons.Filled.SkipPrevious, contentDescription = "Previous", tint = Color.White) }
                IconButton(onClick = { playing = !playing }) {
                    Icon(if (playing) Icons.Filled.Pause else Icons.Filled.PlayArrow, contentDescription = "Play/Pause", tint = Color.White)
                }
                IconButton(onClick = {
                    index = (index + 1) % tracks.size
                    progress = 0
                }) { Icon(Icons.Filled.SkipNext, contentDescription = "Next", tint = Color.White) }
            }
        }
    }
}

private fun formatTime(sec: Int): String {
    val m = sec / 60
    val s = sec % 60
    return "%d:%02d".format(m, s)
}
