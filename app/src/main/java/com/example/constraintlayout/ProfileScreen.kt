package com.example.constraintlayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Preview
@Composable
fun ProfileScreen() {
    Card(
        elevation = CardDefaults.cardElevation(7.dp),
        modifier = Modifier
            .background(color = Color.LightGray)
            .wrapContentSize()
            .padding(top = 50.dp, start = 16.dp, end = 16.dp, bottom = 100.dp),
        shape = RoundedCornerShape(15),
    ) {
        ConstraintLayout {
            val (image, name, nationality, rowStats, followButton, messageButton) = createRefs()
            Image(painter = painterResource(id = R.drawable.blurphoto),
                contentDescription = " ",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(16.dp)
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(width = 4.dp, shape = CircleShape, color = Color.Blue)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
            Text(text = "Mohamed Ahmed",
                color = Color.Black,
                fontSize = 30.sp,
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Text(text = "Egyptian",
                color = Color.Black,
                fontSize = 30.sp,
                modifier = Modifier.constrainAs(nationality) {
                    top.linkTo(name.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(rowStats) {
                        top.linkTo(nationality.bottom)
                    }
                    .padding(16.dp),
            ) {
                ProfileStat(count = "150", name = "Followers")
                ProfileStat(count = "200", name = "Following")
                ProfileStat(count = "15", name = "Posts")
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .wrapContentWidth()
                .constrainAs(followButton) {
                    top.linkTo(rowStats.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(messageButton.start)
                }
                .padding(16.dp)) {
                Text(text = "follow")
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .wrapContentWidth()
                .constrainAs(messageButton) {
                    top.linkTo(rowStats.bottom)
                    start.linkTo(followButton.end)
                    end.linkTo(parent.end)
                }
                .padding(16.dp)) {
                Text(text = "message")
            }
        }
    }

}