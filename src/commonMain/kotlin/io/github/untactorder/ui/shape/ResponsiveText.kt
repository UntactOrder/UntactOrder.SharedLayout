package io.github.untactorder.myapplication.ui.shape

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

private const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

/**
 * @see <a href="URL">https://medium.com/tech-takeaways/responsive-auto-resizing-text-with-jetpack-compose-b8238aaf0e09</a>
 */
@Composable
fun ResponsiveText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
    textAlign: TextAlign = TextAlign.Center,
    textStyle: TextStyle,
    targetTextSizeHeight: TextUnit = textStyle.fontSize,
    maxLines: Int = 1
) {
    var textSize by remember { mutableStateOf(targetTextSizeHeight) }
    var lineHeight by remember { mutableStateOf(textStyle.lineHeight) }

    Text(
        modifier = modifier,
        text = text,
        color = color,
        textAlign = textAlign,
        fontSize = textSize,
        fontFamily = textStyle.fontFamily,
        fontStyle = textStyle.fontStyle,
        fontWeight = textStyle.fontWeight,
        lineHeight = lineHeight,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        onTextLayout = { textLayoutResult ->
            val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1

            if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
            }
        },
    )
}
