package com.trianglz.weatherapp.presentation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.presentation.ui.theme.PurpleIndigoGradient

@Composable
fun SlantShapeTest(modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
            .aspectRatio(342 / 184f),
        onDraw = {
            drawPath(
                getSlantPath(size, 50.dp.toPx(), 0.625f),
                brush = PurpleIndigoGradient,
                style = Fill
            )
        })
}


@Preview(device = "id:pixel_4", showSystemUi = true, showBackground = true)
@Composable
fun SlantShapePreview() {

    Column(Modifier.padding(8.dp)) {
        SlantShapeTest(Modifier.padding(8.dp))

    }
}


fun getSlantPath(
    size: Size,
    cornerRadius: Float,
    leftSideHeightPercentage: Float
): Path {
    val path = Path()
    path.arcTo(
        rect = Rect(
            offset =
            Offset(0f, 0f),
            size = Size(cornerRadius, cornerRadius)
        ),
        startAngleDegrees = 180f,
        sweepAngleDegrees = 100f,
        forceMoveTo = false
    )

    //top right arc
    path.arcTo(
        rect = Rect(
            offset = Offset(
                size.width - cornerRadius,
                (size.height - cornerRadius) * leftSideHeightPercentage
            ),
            size = Size(cornerRadius, cornerRadius)
        ),
        startAngleDegrees = 290f,
        sweepAngleDegrees = 70f,
        forceMoveTo = false
    )

    //bottom right arc
    path.arcTo(
        rect = Rect(
            offset = Offset(size.width - cornerRadius, size.height - cornerRadius),
            size = Size(
                cornerRadius, cornerRadius
            )
        ),
        startAngleDegrees = 0f,
        sweepAngleDegrees = 90f,
        forceMoveTo = false
    )

    //bottom left arc
    path.arcTo(
        rect = Rect(
            offset = Offset(
                0f,
                size.height - cornerRadius
            ),
            size = Size(cornerRadius, cornerRadius),
        ),
        startAngleDegrees = 90f,
        sweepAngleDegrees = 90f,
        forceMoveTo = false
    )

    path.close()
    return path
}

class SlantShape(
    private val cornerRadius: Float = 50f,
    private val leftSideHeightPercentage: Float = 0.625f
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = getSlantPath(size, cornerRadius, leftSideHeightPercentage)
        )
    }
}