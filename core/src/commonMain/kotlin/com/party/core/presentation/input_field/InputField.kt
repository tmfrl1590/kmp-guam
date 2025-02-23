package com.party.core.presentation.input_field

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.party.core.presentation.BLACK
import com.party.core.presentation.GRAY200
import com.party.core.presentation.GRAY500
import com.party.core.presentation.INPUT_FIELD_HEIGHT
import com.party.core.presentation.LARGE_CORNER_SIZE
import com.party.core.presentation.T3
import com.party.core.presentation.WHITE
import com.party.core.presentation.WidthSpacer

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Next,
    elevation: Dp = 4.dp,
    inputText: String,
    inputTextColor: Color = BLACK,
    containerColor: Color = WHITE,
    borderColor: Color = GRAY200,
    borderCornerSize: Dp = LARGE_CORNER_SIZE,
    readOnly: Boolean = false,
    placeHolder: String,
    placeHolderColor: Color = GRAY500,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable () -> Unit = {},
    icon: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = INPUT_FIELD_HEIGHT) // 최소 높이 설정
            .wrapContentHeight()
            .shadow(
                elevation = elevation,
                shape = RoundedCornerShape(borderCornerSize),
            ),
    ) {
        BasicTextField(
            visualTransformation = visualTransformation,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = INPUT_FIELD_HEIGHT) // 최소 높이 설정
                .wrapContentHeight() // 내용에 따라 동적 높이
                .clip(RoundedCornerShape(LARGE_CORNER_SIZE))
                .border(BorderStroke(1.dp, borderColor), RoundedCornerShape(LARGE_CORNER_SIZE))
                .background(containerColor)
            ,
            value = inputText,
            onValueChange = { onValueChange(it) },
            readOnly = readOnly,
            maxLines = 2,
            singleLine = false,
            textStyle = TextStyle(
                color = inputTextColor,
                fontSize = T3,
            ),
            cursorBrush = SolidColor(Color.Black),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = INPUT_FIELD_HEIGHT) // 최소 높이 설정
                        .wrapContentHeight(), // 내용에 따라 동적 높이,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    WidthSpacer(widthDp = 12.dp)

                    leadingIcon()

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentHeight(),
                    ){
                        innerTextField()
                        if(inputText.isEmpty()){
                            Text(
                                text = placeHolder,
                                fontSize = T3,
                                color = placeHolderColor,
                            )
                        }
                    }
                    icon()
                }
            }
        )
    }
}