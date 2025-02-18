package com.party.presentation.screens.login.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import com.mmk.kmpauth.google.GoogleButtonUiContainer
import com.party.core.presentation.EXTRA_LARGE_BUTTON_HEIGHT2
import com.party.core.presentation.GRAY200
import com.party.core.presentation.HeightSpacer
import com.party.core.presentation.KakaoLoginColor
import com.party.core.presentation.LARGE_CORNER_SIZE
import com.party.core.presentation.MEDIUM_PADDING_SIZE
import com.party.core.presentation.Resources
import com.party.core.presentation.WHITE
import com.party.core.presentation.WidthSpacer
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginButtonArea(
    onGoogleLoginSuccess: (String) -> Unit,
) {
    var authReady by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        GoogleAuthProvider.create(
            credentials = GoogleAuthCredentials(
                serverId = "697659482179-ish7bgg19g5le6b1urrss97tjgasi91f.apps.googleusercontent.com"
            )
        )
        authReady = true
    }

    /*SocialLoginButton(
        text = stringResource(Resources.String.Login3),
        containerColor = KakaoLoginColor,
        borderColor = KakaoLoginColor,
        painterImage = painterResource(Resources.Icon.Icon_Kakao),
        contentDescription = "kakao",
        onClick = {
        }
    )*/

    //HeightSpacer(heightDp = 8.dp)

    if(authReady){
        GoogleButtonUiContainer(
            onGoogleSignInResult = { googleUser ->
                val tokenId = googleUser?.idToken ?: ""
                val email = googleUser?.email ?: ""
                println("TOKEN: $tokenId")
                println("TOKEN: $email")
                onGoogleLoginSuccess(tokenId)
            }
        ){
            SocialLoginButton(
                text = stringResource(Resources.String.Login4),
                containerColor = WHITE,
                borderColor = GRAY200,
                painterImage = painterResource(Resources.Icon.Icon_google),
                contentDescription = "google",
                onClick = {
                    this.onClick()
                }
            )
        }
    }

    HeightSpacer(heightDp = 24.dp)
}

@Composable
private fun SocialLoginButton(
    text: String,
    containerColor: Color,
    borderColor: Color,
    painterImage: Painter,
    contentDescription: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(EXTRA_LARGE_BUTTON_HEIGHT2)
            .background(Color.White),
        shape = RoundedCornerShape(LARGE_CORNER_SIZE),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        border = BorderStroke(1.dp, borderColor),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = MEDIUM_PADDING_SIZE)
        ) {
            Icon(
                painter = painterImage,
                contentDescription = contentDescription,
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified
            )

            WidthSpacer(widthDp = 12.dp)

            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
    }
}