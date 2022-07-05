package com.example.composebasiclayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import java.util.*


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

//Hard coded data
private  val itemsData = listOf(
    R.drawable.model10 to R.string.card_collection,
    R.drawable.men9 to R.string.photo_shoot,
    R.drawable.model5 to R.string.walking,
    R.drawable.men8 to R.string.move_on,
    R.drawable.model4 to R.string.breathing,
    R.drawable.model7 to R.string.ow_yeah,
    R.drawable.men7 to R.string.cool,
).map { DrawableStringPair(it.first,it.second) }

private val favoriteCollection = listOf(
    R.drawable.model1 to R.string.card_collection,
    R.drawable.men3 to R.string.photo_shoot,
    R.drawable.model2 to R.string.walking,
    R.drawable.men2 to R.string.move_on,
    R.drawable.model6 to R.string.breathing,
    R.drawable.simple to R.string.ow_yeah,
    R.drawable.wo to R.string.cool,
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val icon: Int,
    @StringRes val text: Int)


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

@Composable
fun FavoriteCollectionCard(
    @DrawableRes  icon: Int,
    @StringRes text: Int,
    modifier: Modifier
){
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)
        ){
                Image(
                    painterResource(id = icon),
                    contentDescription = null,
                     contentScale = ContentScale.Crop,
                    modifier = Modifier.size(56.dp)
                )
            Text(
                stringResource(id = text),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun ScrollableRow(
    modifier: Modifier
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),//space around the whole content
        modifier = modifier
    ){
        items(itemsData){item ->
            Card(
                icon = item.icon,
                text = item.text,
                modifier = modifier
            )
        }
    }
}

@Composable
fun FavoriteCollectionGrid(
    modifier: Modifier
){
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal =  16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(150.dp)
    ){
        items(favoriteCollection){item ->
            FavoriteCollectionCard(
                item.icon,
                item.text,
                modifier
            )
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier,
    content: @Composable () -> Unit
){
    Column(modifier) {
        Text(
            stringResource(id = title).uppercase(locale = Locale.getDefault()),
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
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

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionCardPreview(){
    ComposeBasicLayoutTheme {
        FavoriteCollectionCard(
            R.drawable.model10,
            R.string.card_collection,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ScrollableRowPreview(){
    ComposeBasicLayoutTheme {
        ScrollableRow(Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun FavoriteCollectionGridPreview(){
    ComposeBasicLayoutTheme {
        FavoriteCollectionGrid(Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun HomeSection(){
    ComposeBasicLayoutTheme {
        HomeSection(title = R.string.home_section_title, Modifier.padding()){
            ScrollableRow(Modifier.padding(8.dp))
        }
    }
}