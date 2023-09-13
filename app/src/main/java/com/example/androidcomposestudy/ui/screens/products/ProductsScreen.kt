package com.example.androidcomposestudy.ui.screens.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.androidcomposestudy.ui.navigation.Screens
import com.example.androidcomposestudy.ui.screens.products.components.ProductCard
import com.example.androidcomposestudy.ui.theme.AndroidComposeStudyTheme
import com.example.androidcomposestudy.ui.theme.Black
import com.example.androidcomposestudy.ui.theme.Border
import com.example.androidcomposestudy.utils.generateProductList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(navController: NavController) {
    var searchTextField by remember { mutableStateOf("") }
    val list = remember { generateProductList(100) }

    Column(modifier = Modifier.statusBarsPadding()) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = searchTextField,
                onValueChange = { searchTextField = it },
                modifier = Modifier.weight(1f),
                label = { Text(text = "Pesquisar produtos") },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Black,
                    placeholderColor = Black,
                    focusedSupportingTextColor = Black,
                    containerColor = Color(0xFFFFFFFF),
                    focusedIndicatorColor = Black,
                    focusedLabelColor = Black
                )
            )
            
            Spacer(Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(40.dp))
                    .width(40.dp)
                    .height(40.dp)
                    .background(Color.Black),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "L", fontWeight = FontWeight(500), color = Color.White, fontSize = 18.sp)
            }
        }

        Divider(color = Border)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Todos os produtos", fontSize = 16.sp, fontWeight = FontWeight(500))
                        Text(text = "(3 produtos)", fontSize = 12.sp, color = Color.Gray)
                    }

                    Text(text = "Visualizar todos", fontSize = 12.sp, color = Color.Gray)
                }
            }

            items(items = list, key = { it.name }) {
                ProductCard(product = it) { navController.navigate(Screens.ProductsDetails.route) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    AndroidComposeStudyTheme {
        ProductsScreen(rememberNavController())
    }
}