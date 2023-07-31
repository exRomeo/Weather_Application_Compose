package com.trianglz.weatherapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SlantShape(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .aspectRatio(16 / 9f),
        onDraw = {
            drawPath(
                slantShape(),
                brush = Brush.horizontalGradient(listOf(Color(0xFF5A37B6), Color(0xFF382B86))),
                style = Fill
            )
        })

}



@Preview(device = "id:pixel_4", showSystemUi = true, showBackground = true)
@Composable
fun SlantShapePreview() {
    Column {
        SlantShape(Modifier.padding(8.dp))
    }
}

fun DrawScope.slantShape(): Path {
    val path = Path()
    path.moveTo(50.dp.toPx(), 12.dp.toPx())
    path.lineTo(size.width + -30.dp.toPx(), (size.height / 2.5f) + 0.dp.toPx())

    path.cubicTo(
        size.width + -30.dp.toPx(), (size.height / 2.5f) + 0.dp.toPx(),
        size.width + 3.dp.toPx(), size.height + (-size.height / 1.73f),
        size.width + 0.dp.toPx(), (size.height / 2.5f) + 50.dp.toPx()
    )

    path.lineTo(size.width + 0.dp.toPx(), size.height + -30.dp.toPx())

    path.cubicTo(
        size.width + 0.dp.toPx(), size.height - 10.dp.toPx(),
        size.width + 0.dp.toPx(), size.height + 0.dp.toPx(),
        size.width + -25.dp.toPx(), size.height + 0.dp.toPx()
    )

    path.lineTo(50.dp.toPx(), size.height + 0.dp.toPx())

    path.cubicTo(
        30.dp.toPx(), size.height + -0.dp.toPx(),
        0.dp.toPx(), size.height + 0.dp.toPx(),
        0.dp.toPx(), size.height + -30.dp.toPx()
    )

    path.lineTo(0.dp.toPx(), 50.dp.toPx())

    path.cubicTo(
        0.dp.toPx(), 30.dp.toPx(),
        0.dp.toPx(), 0.dp.toPx(),
        50.dp.toPx(), 12.dp.toPx()
    )

    path.close()
    return path
}