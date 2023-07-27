package com.meet.project.easylogin.ui.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.meet.project.easylogin.R
import com.meet.project.easylogin.ui.theme.ThemeColor
import com.meet.project.easylogin.ui.theme.ThemeTypography

@Composable
fun HomeActivityScreen(
    onLogoutClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ThemeColor.Secondary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.logout_text),
                style = ThemeTypography.subtitle2,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 32.dp),
                color = ThemeColor.Primary,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    onLogoutClick()
                },
                modifier = Modifier.wrapContentWidth(),
                shape = RoundedCornerShape(12.dp),
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ThemeColor.Primary,
                    contentColor = ThemeColor.Grey20
                )
            ) {
                Text(
                    text = stringResource(id = R.string.logout),
                    style = ThemeTypography.button,
                    modifier = Modifier
                        .wrapContentSize()
                        .background(Color.Transparent),
                    color = ThemeColor.Secondary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}