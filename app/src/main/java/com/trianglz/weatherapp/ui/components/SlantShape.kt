package com.trianglz.weatherapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.ui.theme.PurpleIndigoGradient

@Composable
fun SlantShape(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .aspectRatio(342 / 184f),
        onDraw = {
            drawPath(
                slantShape(),
                brush = PurpleIndigoGradient,
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
    val arcWidth = 50.dp.toPx()
    val arcHeight = 50.dp.toPx()

    val leftSideHeightPercentage = 1.6f

    /**
     *  ** Drawing an arc **
     *  to draw an arc you need a "Rect" in which the arc is drawn
     *  to position the rect on the screen you need an "offset" from the starting point of the current canvas which is the top left corner
     *  and a size for that "Rect"
     *  Rect's size controls the Arc's angle
     *  also you need a stat angle from which the arc is drawn
     *  start angles can be represented on a clock as follows
     *   - 0f  is 3 o'clock
     *   - 90f is 6 o'clock
     *   - 180 is 9 o'clock
     *   - 270 is 12 o'clock
     *  and sweep angle is the angle made from two lines
     *   - the first line is the line from the top left corner of the Rect to the start angle
     *   - the second line is the line from the also the left corner of the Rect and the arc's end
     *
     * */

    // Top left Arc
    path.arcTo(
        rect = Rect(
            offset =
            Offset(0.dp.toPx(), 0.dp.toPx()),
            size = Size(arcWidth, arcHeight)
        ),
        startAngleDegrees = 180f,
        sweepAngleDegrees = 100f,
        forceMoveTo = false
    )

    //top line
    path.lineTo(size.width - 22.dp.toPx(), (size.height / leftSideHeightPercentage) - arcHeight)

    //top right arc
    path.arcTo(
        rect = Rect(
            offset = Offset(size.width - arcWidth, (size.height / leftSideHeightPercentage) - arcHeight),
            size = Size(50.dp.toPx(), 50.dp.toPx())
        ),
        startAngleDegrees = 280f,
        sweepAngleDegrees = 80f,
        forceMoveTo = false
    )

    //right line
    path.lineTo(size.width, size.height - arcHeight)

    //bottom right arc
    path.arcTo(
        rect = Rect(
            offset = Offset(size.width - arcWidth, size.height - arcHeight),
            size = Size(
                arcWidth, arcHeight
            )
        ),
        startAngleDegrees = 0f,
        sweepAngleDegrees = 90f,
        forceMoveTo = false
    )

    //bottom line
    path.lineTo(0.dp.toPx() + arcWidth, size.height)

    //bottom left arc
    path.arcTo(
        rect = Rect(
            offset = Offset(
                0.dp.toPx(),
                size.height - arcHeight
            ),
            size = Size(arcWidth, arcHeight),
        ),
        startAngleDegrees = 90f,
        sweepAngleDegrees = 90f,
        forceMoveTo = false
    )

    //left line
    path.lineTo(0.dp.toPx(), arcHeight)
    path.close()

    return path
}

