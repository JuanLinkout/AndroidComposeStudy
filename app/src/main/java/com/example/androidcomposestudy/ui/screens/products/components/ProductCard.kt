package com.example.androidcomposestudy.ui.screens.products.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.androidcomposestudy.R
import com.example.androidcomposestudy.domain.entities.ProductListItem
import com.example.androidcomposestudy.ui.theme.AndroidComposeStudyTheme
import com.example.androidcomposestudy.utils.formatCurrency

@Composable
fun ProductCard(
    product: ProductListItem,
    onClick: () -> Unit
) {
    val totalPrice = product.price
    val priceToDiscount = product.price * product.discountPercentage

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(3.dp))
            .clickable { onClick() }
            .animateContentSize()
    ) {
        AsyncImage(
            model = product.imageURL,
            contentDescription = null,
            modifier = Modifier
                .height(150.dp)
                .clip(RoundedCornerShape(3.dp)),
            placeholder = painterResource(R.drawable.ic_launcher_background),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = product.category,
            color = Color.LightGray,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            modifier = Modifier.padding(bottom = 2.dp)
        )
        Text(
            text = product.name,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(500),
            lineHeight = 16.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Row() {
            Text(text = formatCurrency(product.price), fontSize = 12.sp, fontWeight = FontWeight(400), color = Color.Green)

            if (priceToDiscount != 0.0) {
                Text(
                    text = formatCurrency(totalPrice - priceToDiscount),
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Color.LightGray,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    val product = ProductListItem(
        name = "Tênis nike esporte branco corrida",
        category = "Sapatos",
        price = 19.90,
        discountPercentage = 0.20,
        imageURL = "https://source.unsplash.com/random/300×300"
    )

    AndroidComposeStudyTheme {
        ProductCard(product = product) {  }
    }
}