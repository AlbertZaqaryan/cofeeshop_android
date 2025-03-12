package com.example.androidcoffeshop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.androidcoffeshop.ui.theme.C1
import com.example.androidcoffeshop.ui.theme.C6



@Composable
fun Screen2(
    categoryViewModel: CategoryViewModel = viewModel(),
    productsViewModel: ProductsViewModel = viewModel(),
    navToScreen3: (Any?) -> Unit
) {

    val categories by categoryViewModel.categories.observeAsState(initial = emptyList())
    val products by productsViewModel.products.observeAsState(initial = emptyList())
    val selIn by categoryViewModel.selIn.observeAsState(initial = 0)
    val FlitText by productsViewModel.FlitText.observeAsState("")

    var minPrice by remember {
        mutableDoubleStateOf(0.0)
    }

    var maxPrice by remember {
        mutableDoubleStateOf(1000000.0)
    }

    var rating by remember {
        mutableDoubleStateOf(0.0)
    }

    var isAlert by remember {
        mutableStateOf(false)
    }
    // Вызываем `getCategories()`, если список пуст
    LaunchedEffect(Unit) {
        if (categories.isEmpty()) {
            categoryViewModel.getCategories()
        }
    }
    LaunchedEffect(Unit) {
        if (products.isEmpty()) {
            productsViewModel.getProducts()
        }
    }
    val listCategory = remember(categories) {
        mutableListOf("All Coffee").apply {
            addAll(categories.map { it.name })
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEAEFFF))
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.im3),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Text(
                "Location",
                modifier = Modifier.padding(24.dp, 68.dp, 0.dp, 0.dp),
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )

            Row {
                Text(
                    "Your Location",
                    modifier = Modifier.padding(24.dp, 90.dp, 0.dp, 0.dp),
                    color = Color.White,
                    fontSize = 14.sp
                )
                IconButton(
                    onClick = { /* TODO: Handle click */ },
                    colors = IconButtonDefaults.iconButtonColors(),
                    modifier = Modifier
                        .padding(4.dp, 90.dp, 0.dp, 0.dp)
                        .size(14.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop Down",
                        modifier = Modifier.size(14.dp),
                        tint = Color.White
                    )
                }
            }

            var text by remember { mutableStateOf("") }  // Ensure state is remembered outside Row

            Row(
                modifier = Modifier
                    .padding(24.dp, 135.dp, 24.dp, 0.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(C6)
                    .fillMaxWidth()
                    .padding(0.dp),  // Add internal padding for better spacing
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )



                if (isAlert) {
                    AlertDialog(
                        onDismissRequest = { isAlert = false },
                        confirmButton = {
                            Button(onClick = { isAlert = false }) {
                                Text("Apply")
                            }
                        },
                        dismissButton = {
                            Button(onClick = { isAlert = false }) {
                                Text("Cancel")
                            }
                        },
                        title = { Text("Filter Products", fontSize = 20.sp) },
                        text = {
                            Column(modifier = Modifier.padding(16.dp)) {
                                OutlinedTextField(
                                    value = minPrice.toString(),
                                    onValueChange = { minPrice = it.toDouble() },
                                    label = { Text("Min Price", color = Color.White) },
                                    singleLine = true,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp)  // Keep proper spacing
                                    ,  // Ensures enough height
                                    textStyle = TextStyle(  // Set input text style
                                        fontSize = 24.sp
                                    )

                                )
                                OutlinedTextField(
                                    value = maxPrice.toString(),
                                    onValueChange = { maxPrice = it.toDouble() },
                                    label = { Text("Min Price", color = Color.White) },
                                    singleLine = true,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp)  // Keep proper spacing
                                    ,  // Ensures enough height
                                    textStyle = TextStyle(  // Set input text style
                                        fontSize = 24.sp
                                    )
                                )
                                OutlinedTextField(
                                    value = rating.toString(),
                                    onValueChange = { rating = it.toDouble() },
                                    label = { Text("Min Price", color = Color.White) },
                                    singleLine = true,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp)  // Keep proper spacing
                                    ,  // Ensures enough height
                                    textStyle = TextStyle(  // Set input text style
                                        fontSize = 24.sp
                                    )
                                )
                            }
                        })
                }

                OutlinedTextField(
                    value = FlitText,
                    onValueChange = { productsViewModel.FlitText.value = it },
                    label = { Text("Enter text", color = Color.White) }, // Label text in white
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)  // Keep proper spacing
                        .height(70.dp),  // Ensures enough height
                    textStyle = TextStyle(  // Set input text style
                        color = Color.White,
                        fontSize = 24.sp
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = C6,  // Background color
                        focusedContainerColor = C6,
                        unfocusedIndicatorColor = C6,  // Remove border
                        focusedIndicatorColor = C6,
                        cursorColor = Color.White // Ensures cursor is visible
                    )
                )


                IconButton(
                    onClick = { isAlert = true },
                    colors = IconButtonDefaults.iconButtonColors(),
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = C1)
                        .size(52.dp),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.type_regular__state_outline__library_filter),
                        contentDescription = "Filter",
                        tint = Color.White
                    )
                }
            }
            Box(
                Modifier.padding(24.dp, 211.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.im4),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .padding(24.dp, 14.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(C1)
                ) {
                    Text(
                        text = "Promo",
                        fontSize = 14.sp,
                        color = Color.White,
                        modifier = Modifier.padding(6.dp)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.im5),
                    contentDescription = "",

                    modifier = Modifier
                        .padding(24.dp, 62.dp, 103.dp, 51.dp)
                        .size(200.dp, 27.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "BUY ONE GET",
                    modifier = Modifier
                        .padding(24.dp, 47.dp, 103.dp, 51.dp),
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )

                Image(
                    painter = painterResource(id = R.drawable.im5),
                    contentDescription = "",

                    modifier = Modifier
                        .padding(24.dp, 101.dp, 103.dp, 51.dp)
                        .size(149.dp, 23.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "one FREE",
                    modifier = Modifier
                        .padding(24.dp, 86.dp, 103.dp, 51.dp),
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
        Box(
            modifier = Modifier // bottom navigation
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 160.dp)
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
                    onClick = { /*TODO*/ },
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
    Column()
    {
        Box(
            modifier = Modifier // lazy row
                .fillMaxWidth()
//            .fillMaxHeight()
                .padding(top = 375.dp)
                .padding(start = 24.dp)
        ) {

            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                items(listCategory.size) { index ->
                    Box() {
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextButton(
                            onClick = { categoryViewModel.selIn.value = index },
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(if (index == selIn) C1 else Color.Transparent)
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                                .size(87.dp, 29.dp)
                        ) {
                        }
                        Text(
                            text = listCategory[index],
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(87.dp, 29.dp)
                                .padding(top = 8.dp, start = 17.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center, // Centers text
                            color = if (index == selIn) Color.White else Color.Black
                        )
                        Spacer(modifier = Modifier.padding(2.dp)) // Correct spacing
                    }
                }
            }


        } // row end
        Box(modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp)) {
            var currentProducts = mutableListOf<Product>()

            var current2Products = mutableListOf<Product>()
            currentProducts.clear()

            if (selIn == 0) {
                currentProducts.addAll(products)
            } else {
                for (i in products) {
                    if (i.category == selIn) {
                        currentProducts.add(i)
                    }
                }
            }

            var currentProducts2 = mutableListOf<Product>()
            currentProducts2.clear()

            if (FlitText.isNotEmpty()) {
                for (i in currentProducts) {
                    if (i.name.uppercase().contains(FlitText.uppercase())) {
                        currentProducts2.add(i)
                    }
                }
            } else {
                currentProducts2.addAll(currentProducts)
            }

            var currentProducts3 = mutableListOf<Product>()
            currentProducts3.clear()

            for (i in currentProducts2) {
                if (minPrice <= i.price_M && i.price_M <= maxPrice && i.rating >= rating) {
                    currentProducts3.add(i)
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.height(340.dp), // Increased height for better display
                contentPadding = PaddingValues(8.dp)
            ) {
                currentProducts3.forEach { it ->
                    item() {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.White)
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                // Image with rating overlay
                                Box {
                                    AsyncImage(
                                        model = it.image,
                                        contentDescription = "Product Image",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(120.dp)
                                            .clip(RoundedCornerShape(16.dp)),
                                        contentScale = ContentScale.Crop
                                    )

                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .padding(8.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color.Black.copy(alpha = 0.6f))
                                            .padding(4.dp)
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = Icons.Default.Star,
                                                contentDescription = "Rating",
                                                tint = Color(0xFFFFD700),
                                                modifier = Modifier.size(12.dp)
                                            )
                                            Text(
                                                text = "${it.rating}",
                                                fontSize = 12.sp,
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                // Text content
                                Text(
                                    text = "${it.name}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = "${it.including}",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                // Price and button
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "$${it.price_M}",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp
                                    )

                                    Button(
                                        onClick = { navToScreen3(it.id)},
                                        shape = RoundedCornerShape(8.dp), // Slightly rounded square
                                        modifier = Modifier.size(32.dp), // Ensures square dimensions
                                        colors = ButtonDefaults.buttonColors(containerColor = C1),
                                        contentPadding = PaddingValues(0.dp) // Removes default padding
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "Add",
                                            tint = Color.White,
                                            modifier = Modifier.size(16.dp)
                                        )
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

//@Preview(showBackground = true)
//@Composable
//fun Screen2Preview() {
//    Screen2()
//}