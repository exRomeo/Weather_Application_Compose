package com.trianglz.weatherapp.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.presentation.models.result.ResultState
import com.trianglz.weatherapp.presentation.ui.theme.darkPurple
import com.trianglz.weatherapp.presentation.ui.theme.lavender

/**
 * [SearchBarInputField] is a [BasicTextField] without a background customized to look like a search bar
 * */

@Composable
fun TransparentSearchBar(
    status: () -> ResultState<Nothing>,
    placeHolder: () -> String,
    text: () -> String,
    onTextChanged: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    var inFocus by remember { mutableStateOf(false) }

    SearchBarInputField(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .onFocusChanged { state -> inFocus = state.isFocused },
        trailingIcon = {
            AnimatedTrailingIcon(inFocus = inFocus, onCloseClicked)
        },
        leadingIcon = {
            LeadingIconForStatus(status = status, inFocus = { inFocus })
        },
        text = text(),
        textStyle = MaterialTheme
            .typography
            .bodyLarge
            .copy(
                color = if (status() is ResultState.NoResult)
                    MaterialTheme.colorScheme.onErrorContainer
                else
                    MaterialTheme.colorScheme.onPrimaryContainer
            ),
        placeHolder = {
            Text(
                text = placeHolder(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        },
        onTextChanged = onTextChanged
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBarInputField(
    modifier: Modifier = Modifier,
    text: String,
    placeHolder: (@Composable () -> Unit)? = null,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
    shape: CornerBasedShape = MaterialTheme.shapes.small,
    singleLine: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    onTextChanged: (String) -> Unit,
) {
    BasicTextField(modifier = modifier.fillMaxWidth(),
        value = text,
        singleLine = singleLine,
        onValueChange = onTextChanged,
        cursorBrush = SolidColor(lavender),
        textStyle = textStyle,
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = text,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = singleLine,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                contentPadding = PaddingValues(vertical = 7.dp, horizontal = 8.dp),
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                placeholder = placeHolder,
                shape = shape,
            ) { }
        })
}

@Composable
fun LeadingIconForStatus(status: () -> ResultState<Nothing>, inFocus: () -> Boolean) {
    val alphaValue = if (inFocus()) 1f else 0.5f
    val animatedAlpha by animateFloatAsState(
        targetValue = alphaValue,
        animationSpec = tween(150, easing = EaseIn),
        label = "animatedAlpha"
    )
    when (status()) {
        is ResultState.Idle, is ResultState.Success -> Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = animatedAlpha)
        )

        is ResultState.Loading -> CircularProgressIndicator(
            modifier = Modifier.scale(scale = 0.6f),
            strokeWidth = 5.dp,
            strokeCap = StrokeCap.Round,
            trackColor = darkPurple
        )

        is ResultState.NoResult -> Icon(
            imageVector = Icons.Rounded.Warning,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onErrorContainer
        )
    }
}

@Composable
fun AnimatedTrailingIcon(inFocus: Boolean, action: () -> Unit) {
    AnimatedVisibility(
        visible = inFocus,
        enter = fadeIn(animationSpec = tween(150, easing = EaseIn)),
        exit = fadeOut(animationSpec = tween(150, easing = EaseOut))
    ) {
        IconButton(onClick = action) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}