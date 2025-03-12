package com.example.androidcoffeshop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcoffeshop.ui.theme.C1

@Composable
fun Home(){

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Box(modifier = Modifier.fillMaxWidth(),) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

                Text(
                    modifier = Modifier.fillMaxWidth().padding(top = 517.dp),
                    text = "Fall in Love with",
                    color = Color.White,
                    fontSize = 32.sp,  // Increased font size
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

        }
        Box() {

            Image(
                painter = painterResource(id = R.drawable.shadowcontainer),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp), // Adjust side padding
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Coffee in Blissful",
                    color = Color.White,
                    fontSize = 32.sp,  // Slightly bigger for emphasis
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Delight!",
                    color = Color.White,
                    fontSize = 32.sp,  // Slightly bigger for emphasis
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )


                Spacer(modifier = Modifier.height(20.dp)) // More spacing between title & subtitle

                Text(
                    text = "Welcome to our cozy coffee corner,",
                    color = Color.Gray,
                    fontSize = 14.sp,  // Slightly smaller
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "where every cup is a delightful for you.",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal

                )
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 24.dp, end = 24.dp, bottom = 11.dp), // Fixed button padding
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp), // Adjust height if needed
                    colors = ButtonDefaults.buttonColors(
                        containerColor = C1,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp), // Adjust the radius for rounded corners
                    onClick = { /* TODO */ }
                ) {
                    Text(text = "Get Started")
                }

            }

        }

    }

}


@Preview(showSystemUi = true)
@Composable
fun HomePreview(){
    Home()
}
