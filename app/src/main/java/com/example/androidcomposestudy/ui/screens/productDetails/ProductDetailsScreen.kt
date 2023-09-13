package com.example.androidcomposestudy.ui.screens.productDetails

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.androidcomposestudy.R
import com.example.androidcomposestudy.domain.entities.ProductListItem
import com.example.androidcomposestudy.ui.theme.AndroidComposeStudyTheme
import com.example.androidcomposestudy.utils.generateProductList

@Composable
fun ProductDetailsScreen(
    navController: NavController,
    productListItem: ProductListItem
) {
    val viewModel = viewModel<ProductDetailsViewModel>()
    val products by remember { mutableStateOf(generateProductList(10)) }
    val selectedImage by viewModel.selectedImage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box() {
            AsyncImage(
                model = selectedImage,
                contentDescription = null,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds
            )

            Box(modifier = Modifier.statusBarsPadding().padding(horizontal = 24.dp, vertical = 16.dp)) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(40.dp))
                        .size(48.dp)
                        .background(Color.White)
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = ""
                    )
                }
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(products, key = { it.name }) {
                val animateValue by animateIntAsState(targetValue = if (selectedImage == it.imageURL) 2 else 0)
                AsyncImage(
                    model = it.imageURL,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(60.dp)
                        .width(80.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .border(width = animateValue.dp, color = Color.Black)
                        .clickable { viewModel.onImageChange(it.imageURL) }
                )
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = productListItem.category,
                color = Color.LightGray,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = productListItem.name,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight(500),
                lineHeight = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "O tênis branco da Nike, parte da nova linha, combina estilo e conforto. Seu design minimalista e elegante é perfeito para qualquer ocasião, enquanto a tecnologia avançada oferece um ajuste perfeito e amortecimento de alto desempenho.",
                color = Color.Gray,
                fontSize = 14.sp,
                lineHeight = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsDetailsScreenPreview() {
    val product = remember { generateProductList(1) }

    AndroidComposeStudyTheme {
        ProductDetailsScreen(rememberNavController(), product[0])
    }
}