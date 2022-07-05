package com.example.composebasiclayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasiclayout.ui.theme.ComposeBasicLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicLayoutTheme {
                // A surface container using the 'background' color from the theme
                MainApplication()
            }
        }
    }
}

@Composable
fun MainApplication(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        //call composable here
    }
}

//SearchBar
@Composable
fun SearchBar(modifier: Modifier){
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        placeholder = {
            Text(stringResource(id=R.string.placeholder_search))
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth()
    )
}

//Card
@Composable
fun Card(
    @DrawableRes icon: Int,
    @StringRes text: Int,
    modifier: Modifier
){
    Column(
        horizontalAlignment= Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painterResource(id = icon),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            stringResource(id = text),
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

//Previews scope

@Preview(showBackground = true)
@Composable
fun SearchBarPreview(){
    ComposeBasicLayoutTheme {
        SearchBar(modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground=true, backgroundColor = 0xFFF0EAE2)
@Composable
fun CardPreview(){
    ComposeBasicLayoutTheme {
        Card(
            R.drawable.model9,
            R.string.card_text,
            modifier = Modifier.padding(8.dp)
        )
    }
}