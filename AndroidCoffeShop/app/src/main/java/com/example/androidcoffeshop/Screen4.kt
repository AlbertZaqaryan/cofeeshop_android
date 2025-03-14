package com.example.androidcoffeshop

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.androidcoffeshop.ui.theme.C1

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun DetailsPreview() {
//    Screen4()
//}


@SuppressLint("UnrememberedMutableState")
@Composable
fun Screen4(
    productsViewModel: ProductsViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel(),
    navToScreen2: () -> Unit
) {
    val products by productsViewModel.products.observeAsState(initial = emptyList())
    val cart by cartViewModel.cart.observeAsState(initial = emptyList())
    LaunchedEffect(Unit) {
        if (products.isEmpty()) {
            productsViewModel.getProducts()
        }
    }

    LaunchedEffect(Unit) {
        if (cart.isEmpty()) {
            cartViewModel.getCart()
        }
    }

    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.88f)
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
                    Spacer(modifier = Modifier.fillMaxWidth(0.25f))
                }
            }


            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    modifier = Modifier.height(340.dp), // Increased height for better display
                    contentPadding = PaddingValues(8.dp)
                ) {
                    cart.forEach { it ->
                        item() {
                            val p = productsViewModel.getProd(it.product)
                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color.White)
                                    .size(327.dp, 54.dp)
                            ) {
                                Row {
                                    AsyncImage(
                                        model = p?.image,
                                        contentDescription = "Caffe Mocha",
                                        modifier = Modifier
                                            .fillMaxWidth(.4f)
                                            .padding(horizontal = 24.dp)
                                            .height(200.dp)
                                            .size(20.dp)
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(Color.Gray),
                                        contentScale = ContentScale.Crop
                                    )
                                    Column {
                                        p?.name?.let { it1 -> Text(text = it1) }
//                                        p?.including?.let { it1 -> Text(text = it1) }
                                    }
                                    Row {
                                        IconButton(onClick = {
                                            cartViewModel.addCount(
                                                it.id,
                                                it.count
                                            )
                                        }) {
                                            Icon(imageVector =Icons.Default.ArrowBack, contentDescription = " ")
                                            LaunchedEffect(Unit) {
                                                if (cart.isEmpty()) {
                                                    cartViewModel.getCart()
                                                }
                                            }
                                        }
                                        Text(text = it.count.toString())
                                        IconButton(onClick = {
                                            cartViewModel.subCount(
                                                it.id,
                                                it.count
                                            )
                                        }) {
                                            Icon(imageVector =Icons.Default.ArrowForward, contentDescription = " ")
                                            LaunchedEffect(Unit) {
                                                if (cart.isEmpty()) {
                                                    cartViewModel.getCart()
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }


        }
        Box(
            modifier = Modifier // bottom navigation
                .fillMaxWidth()
                .fillMaxHeight()
//                .padding(top = 160.dp)
                .padding(start = 24.dp, end = 24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .size(375.dp, 99.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically

            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                ) {
                    Icon(
                        imageVector = Icons.Default.Home, contentDescription = "Home",
                        tint = C1
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = "Filter",
                        tint = Color.Black
                    )
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.bag),
                        contentDescription = "Filter",
                        tint = Color.Black
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.notification),
                        contentDescription = "Filter",
                        tint = Color.Black
                    )
                }
            }

        }
    }
}