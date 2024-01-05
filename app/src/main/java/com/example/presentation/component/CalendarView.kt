package com.example.presentation.component

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.geometry.Size
import android.widget.CalendarView
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.presentation.ui.theme.Black
import com.example.presentation.ui.theme.Label
import com.example.presentation.utils.Models.CalendarInput
import kotlinx.coroutines.launch
import java.util.Calendar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CalendarView(modifier: Modifier = Modifier) {

    val today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

    var date by remember {
        mutableStateOf(today.toString())
    }

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = CenterVertically
            ) {
                AndroidView(
                    modifier = Modifier
                        .align(CenterVertically)
                        .fillMaxWidth(),
                    factory = { CalendarView(it) },
                    update = {
                    it.setOnDateChangeListener { _, year, month, day ->
                        date = "$day - ${month + 1} - $year"
                    }
                })
            }
        }
    )
}

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    onDayClick: (Int) -> Unit,
    strokeWidth: Float = 6f,
    month: String,
    calendarInput: List<CalendarInput>,
    day: String
) {
    var canvasSize by remember {
        mutableStateOf(Size.Zero)
    }

    var clickAnimationOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    var animationRadius by remember {
        mutableStateOf(0f)
    }

    val scope = rememberCoroutineScope()

    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectTapGestures (
                        onTap = { offset ->
                            val column = (offset.x / canvasSize.width * 7).toInt() + 1
                            val row = (offset.y / canvasSize.height * 5).toInt() + 1
                            val day = column + (row - 1) * 7

                            if(day <= calendarInput.size) {
                                onDayClick(day)
                                clickAnimationOffset = offset
                                scope.launch {
                                    animate(0f, 225f, animationSpec = tween(300)) { value, _ ->
                                        animationRadius = value
                                    }
                                }
                            }
                        }
                    )
                }
        ) {
            val canvasHeight = size.height
            val canvasWidth = size.width

            canvasSize = Size(canvasWidth, canvasHeight)
            val ySteps = canvasHeight / 5
            val xSteps = canvasWidth / 7

            val column = (clickAnimationOffset.x / canvasSize.width * 7).toInt() + 1
            val row = (clickAnimationOffset.y / canvasSize.height * 5).toInt() + 1

            val path = Path().apply {
                moveTo((column-1)*xSteps,(row-1)*ySteps)
                lineTo(column*xSteps,(row-1)*ySteps)
                lineTo(column*xSteps,row*ySteps)
                lineTo((column-1)*xSteps,row*ySteps)
                close()
            }

            clipPath(path) {
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(Black.copy(0.5f), Black.copy(0.1f)),
                        center = clickAnimationOffset,
                        radius = animationRadius + 0.1f
                    ),
                    radius = animationRadius + 0.1f,
                    center = clickAnimationOffset
                )
            }

            drawRoundRect(
                Black,
                cornerRadius = CornerRadius(12f, 12f),
                style = Stroke(
                    width = strokeWidth
                )
            )

            for(i in 1 until 5){
                drawLine(
                    color = Black,
                    start = Offset(0f,ySteps*i),
                    end = Offset(canvasWidth, ySteps*i),
                    strokeWidth = strokeWidth
                )
            }

            for(i in 1 until 7){
                drawLine(
                    color = Black,
                    start = Offset(xSteps*i,0f),
                    end = Offset(xSteps*i, canvasHeight),
                    strokeWidth = strokeWidth
                )
            }

            val textHeight = 16.dp
            for(i in calendarInput.indices) {
                val textPositionX = xSteps * (i % 7) + strokeWidth
                val textPositionY = (i / 7) * ySteps + textHeight.toPx() + strokeWidth / 2
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        "${i + 1}",
                        textPositionX,
                        textPositionY,
                        Paint().apply {
                            textSize = textHeight.toPx()
                            color = Label.toArgb()
                            isFakeBoldText = true
                        }
                    )
                }
            }
        }
    }
}