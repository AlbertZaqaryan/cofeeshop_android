package com.example.androidcoffeshop

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.androidcoffeshop.ui.theme.C1
import com.example.androidcoffeshop.ui.theme.C4


//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun DetailsPreview() {
//    Screen3()
//}

@SuppressLint("UnrememberedMutableState")
@Composable
fun Screen3(
    productsViewModel: ProductsViewModel = viewModel(),
    navToScreen2: () -> Unit, id: Int
) {
    val products by productsViewModel.products.observeAsState(initial = emptyList())
    LaunchedEffect(Unit) {
        if (products.isEmpty()) {
            productsViewModel.getProducts()
        }
    }


    val product = products.find { it.id == id }

    val descriptionText = """
        ${product?.description}
    """.trimIndent()


    // State to control whether the full description is shown
    val (isExpanded, onExpandedChange) = remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // --- Top Bar Section ---
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navToScreen2() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go Back")
                }
                Text(
                    text = "Details",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = { /* Handle favorite button click */ }) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
                }
            }
        }

        // --- Coffee Image Section ---
        item {
            if (product != null) {
                AsyncImage(
                    model = product.image,
                    contentDescription = "Caffe Mocha",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Gray),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // --- Coffee Name & Features Section ---
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                if (product != null) {
                    Text(
                        text = product.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF242424),
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (product != null) {
                        Text(
                            text = product.including,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.weight(1f)
                        )
                    }

//                    IconButton(onClick = { /*TODO*/ }) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.icon),
//                            contentDescription = "First Feature",
//                            modifier = Modifier
//                                .width(44.dp)
//                                .height(44.dp)
//                                .padding(start = 8.dp)
//                        )
//                    }
//                    IconButton(onClick = { /*TODO*/ }) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.bag),
//                            contentDescription = "Second Feature",
//                            modifier = Modifier
//                                .width(44.dp)
//                                .height(44.dp)
//                                .padding(start = 8.dp)
//                        )
//                    }
//                    IconButton(onClick = { /*TODO*/ }) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.bag),
//                            contentDescription = "Third Feature",
//                            modifier = Modifier
//                                .width(44.dp)
//                                .height(44.dp)
//                                .padding(start = 8.dp)
//                        )
//                    }
                }
                Row {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFD700),
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    )
                    if (product != null) {
                        Text(
                            text = product.rating.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
//                    Text(
//                        text = "(230)",
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight.Normal,
//                        color = Color(0xFFA2A2A2),
//                        modifier = Modifier.padding(start = 4.dp)
//                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Divider(
                    color = C4,
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(295.dp)
                )
            }
        }

        // --- Spacer ---
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // --- Description Section ---
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Description",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF242424),
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                if (!isExpanded) {
                    Text(
                        text = buildAnnotatedString {
                            append(descriptionText.take(100) + "... ")
                            pushStyle(
                                SpanStyle(
                                    color = C1,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            append("Read More")
                            pop()
                        },
                        fontSize = 14.sp,
                        color = Color(0xFFA2A2A2),
                        modifier = Modifier.clickable { onExpandedChange(true) }
                    )
                } else {
                    Text(
                        text = buildAnnotatedString {
                            append(descriptionText + " ")
                            pushStyle(
                                SpanStyle(
                                    color = C1,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            append("Read Less")
                            pop()
                        },
                        fontSize = 14.sp,
                        color = Color(0xFFA2A2A2),
                        modifier = Modifier.clickable { onExpandedChange(false) }
                    )
                }
            }
        }

        // --- Spacer ---
        item {
            Spacer(modifier = Modifier.padding(24.dp))
        }


        var selectedButton by mutableIntStateOf(2)
        // --- Size Selection Section ---
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 31.dp, start = 24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Size",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF242424),
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier
                            .height(41.dp)
                            .width(96.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedButton == 1) C1 else Color.White,
                            contentColor = Color(0xFF242424)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        onClick = { selectedButton = 1 }
                    ) {
                        Text(text = "S")
                    }
                    Button(
                        modifier = Modifier
                            .height(41.dp)
                            .width(96.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedButton == 2) C1 else Color.White,
                            contentColor = Color(0xFF242424)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        onClick = { selectedButton = 2 }
                    ) {
                        Text(text = "M")
                    }
                    Button(
                        modifier = Modifier
                            .height(41.dp)
                            .width(96.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedButton == 3) C1 else Color.White,
                            contentColor = Color(0xFF242424)
                        ),
                        shape = RoundedCornerShape(12.dp),
                        onClick = { selectedButton = 3 }
                    ) {
                        Text(text = "L")
                    }
                }
            }
        }

        // --- Spacer ---
        item {
            Spacer(modifier = Modifier.padding(24.dp))
        }

        // --- Price Section ---
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.padding(start = 24.dp, top = 20.dp)) {
                        Text(
                            text = "Price",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF909090),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        if (product != null) {
                            var p = if (selectedButton == 1) product.price_S else if (selectedButton == 2) product.price_M else product.price_L
                            Text(
                                text = p.toString(),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = C1,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                    Button(
                        onClick = { /* Handle Buy Now click */ },
                        modifier = Modifier
                            .padding(end = 24.dp, top = 16.dp)
                            .height(56.dp)
                            .width(217.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = C1,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Buy Now",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.padding(50.dp))
        }
    }
}