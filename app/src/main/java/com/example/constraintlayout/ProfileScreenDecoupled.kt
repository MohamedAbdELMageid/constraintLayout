package com.example.constraintlayout

//package com.example.constraintlayout

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Preview
@Composable
fun ProfileScreenDecoupled() {
    Card(
        elevation = CardDefaults.cardElevation(7.dp),
        modifier = Modifier
            .background(color = Color.LightGray)
            .wrapContentSize()
            .padding(top = 50.dp, start = 16.dp, end = 16.dp, bottom = 50.dp),
        shape = RoundedCornerShape(15),
    ) {
        BoxWithConstraints {
            val configuration = LocalConfiguration.current
            val constraint = when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> landScapeMood()
                else -> portraitMood()
            }

            ConstraintLayout(constraint) {
                Image(
                    painter = painterResource(id = R.drawable.blurphoto),
                    contentDescription = " ",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(width = 4.dp, shape = CircleShape, color = Color.Blue)
                        .layoutId("image")
                )
                Text(
                    text = "Mohamed Ahmed",
                    color = Color.Black,
                    fontSize = 30.sp,
                    modifier = Modifier.layoutId("name")
                )
                Text(
                    text = "Egyptian",
                    color = Color.Black,
                    fontSize = 30.sp,
                    modifier = Modifier.layoutId("nationality")
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .layoutId("rowStats"),
                ) {
                    ProfileStat(count = "150", name = "Followers")
                    ProfileStat(count = "200", name = "Following")
                    ProfileStat(count = "15", name = "Posts")
                }
                Button(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .wrapContentWidth()
                        .padding(16.dp)
                        .layoutId("followButton")
                ) {
                    Text(text = "follow")
                }
                Button(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .wrapContentWidth()
                        .padding(16.dp)
                        .layoutId("messageButton")
                ) {
                    Text(text = "message")
                }
            }
        }
    }
}

fun portraitMood(): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val name = createRefFor("name")
        val nationality = createRefFor("nationality")
        val rowStats = createRefFor("rowStats")
        val followButton = createRefFor("followButton")
        val messageButton = createRefFor("messageButton")

        constrain(image) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(name) {
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(nationality) {
            top.linkTo(name.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(rowStats) {
            top.linkTo(nationality.bottom)
        }
        constrain(followButton) {
            top.linkTo(rowStats.bottom)
            start.linkTo(parent.start)
            end.linkTo(messageButton.start)
        }
        constrain(messageButton) {
            top.linkTo(rowStats.bottom)
            start.linkTo(followButton.end)
            end.linkTo(parent.end)
        }
    }
}

fun landScapeMood(): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val name = createRefFor("name")
        val nationality = createRefFor("nationality")
        val rowStats = createRefFor("rowStats")
        val followButton = createRefFor("followButton")
        val messageButton = createRefFor("messageButton")

        constrain(image) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        }
        constrain(name) {
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
        }
        constrain(nationality) {
            top.linkTo(name.bottom)
            start.linkTo(parent.start)
        }
        constrain(rowStats) {
            top.linkTo(parent.top)
            start.linkTo(image.end)
            end.linkTo(parent.end)
        }
        constrain(followButton) {
            top.linkTo(parent.top)
            start.linkTo(nationality.end)
            end.linkTo(messageButton.start)
            bottom.linkTo(parent.bottom)
        }
        constrain(messageButton) {
            top.linkTo(parent.top)
            start.linkTo(followButton.end)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }
    }
}