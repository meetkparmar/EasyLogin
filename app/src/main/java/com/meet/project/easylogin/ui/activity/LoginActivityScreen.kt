package com.meet.project.easylogin.ui.activity

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.meet.project.easylogin.R
import com.meet.project.easylogin.ui.theme.ThemeColor
import com.meet.project.easylogin.ui.theme.ThemeTypography

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginActivityScreen(
    viewModel: MainViewModel,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ThemeColor.Secondary)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "app_logo",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = ThemeTypography.h4,
                modifier = Modifier
                    .wrapContentSize()
                    .background(Color.Transparent),
                color = ThemeColor.Primary,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            val keyboardController = LocalSoftwareKeyboardController.current

            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = viewModel.loginName,
                onValueChange = { newText ->
                    viewModel.onLoginNameTextChange(newText)
                },
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }),
                placeholder = {
                    Text(
                        text = "Full Name",
                        style = ThemeTypography.body1,
                        modifier = Modifier.fillMaxWidth(),
                        color = ThemeColor.Grey50,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = ThemeColor.Grey50,
                    focusedBorderColor = ThemeColor.Grey50,
                    textColor = ThemeColor.Primary,
                    cursorColor = ThemeColor.Primary
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = true
            )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = viewModel.loginPassword,
                onValueChange = { newText ->
                    viewModel.onLoginPasswordTextChange(newText)
                },
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }),
                placeholder = {
                    Text(
                        text = "Password",
                        style = ThemeTypography.body1,
                        modifier = Modifier.fillMaxWidth(),
                        color = ThemeColor.Grey50,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = ThemeColor.Grey50,
                    focusedBorderColor = ThemeColor.Grey50,
                    textColor = ThemeColor.Primary,
                    cursorColor = ThemeColor.Primary
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = true
            )

            if (viewModel.showError) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = viewModel.errorMsg,
                    style = ThemeTypography.caption,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 24.dp),
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (viewModel.loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .wrapContentSize(),
                    strokeWidth = 4.dp,
                    color = ThemeColor.Primary
                )
            } else {
                OutlinedButton(
                    onClick = {
                        onSignUpClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, ThemeColor.Primary),
                    enabled = true,
                ) {
                    Text(
                        text = stringResource(id = R.string.signup),
                        style = ThemeTypography.button,
                        modifier = Modifier
                            .wrapContentSize()
                            .background(Color.Transparent),
                        color = ThemeColor.Primary,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    text = stringResource(id = R.string.or),
                    style = ThemeTypography.caption,
                    modifier = Modifier
                        .wrapContentSize()
                        .background(Color.Transparent),
                    color = ThemeColor.Grey50,
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = {
                        onLoginClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    shape = RoundedCornerShape(12.dp),
                    enabled = true,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ThemeColor.Primary,
                        contentColor = ThemeColor.Grey20
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.login),
                        style = ThemeTypography.button,
                        modifier = Modifier
                            .wrapContentSize()
                            .background(Color.Transparent),
                        color = ThemeColor.Secondary,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}