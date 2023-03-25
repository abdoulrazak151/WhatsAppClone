package ne.futurinnov.whatsappclone

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ne.futurinnov.whatsappclone.composable.AppTabBar
import ne.futurinnov.whatsappclone.composable.AppTabs
import ne.futurinnov.whatsappclone.ui.theme.WhatsAppCloneTheme
import ne.futurinnov.whatsappclone.ui.view.ChatView
import ne.futurinnov.whatsappclone.ui.view.ContactView
import ne.futurinnov.whatsappclone.ui.view.StatusView

enum class HomeTab{
    CHAT, STATUS, CONTACT
}
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsAppCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeView(this)
                }
            }
        }
    }
}

@Composable
fun HomeView(mContext: Context) {
    var showMenu by remember {
        mutableStateOf(false)
    }
    var tabSelected by remember{ mutableStateOf(HomeTab.CHAT) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text ="WhatsApp")},
                elevation = 0.dp,

                actions = {
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu=false }) {
                        DropdownMenuItem(onClick = { /*TODO*/ }) {
                            Text("WhatsApp Web")
                        }
                        DropdownMenuItem(onClick = { /*TODO*/ }) {
                            Text("Setting")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.Search, contentDescription = null)
                        }
                        
                    }
                }
            )
        },
        floatingActionButton={
              when(tabSelected){
                  HomeTab.CHAT ->{
                      FloatingActionButton(onClick = { /*TODO*/ }, backgroundColor = Color.Green) {
                          Icon(
                              painter= painterResource(id = R.drawable.ic_message),
                              contentDescription = null,
                              tint= Color.White
                          )
                      }
                  }
                  HomeTab.STATUS ->{
                      Column {
                          FloatingActionButton(
                              onClick = { /*TODO*/ },
                              backgroundColor = Color.White,
                              modifier = Modifier.size(40.dp)
                          ) {
                              Icon(
                                  painter= painterResource(id = R.drawable.ic_create),
                                  contentDescription = null,
                                  tint=Color.Gray
                              )
                          }
                          Spacer(modifier=Modifier.height(16.dp))
                          FloatingActionButton(onClick = { /*TODO*/ }, backgroundColor = Color.Green) {
                              Icon(
                                  painter = painterResource(id =R.drawable.ic_camera),
                                  contentDescription = null,
                                  tint=Color.White
                              )
                          }
                      }
                  }
                  HomeTab.CONTACT ->{
                      FloatingActionButton(onClick = { /*TODO*/ }, backgroundColor = Color.Green) {
                          Icon(
                              painter= painterResource(id = R.drawable.ic_add_call),
                              contentDescription = null,
                              tint= Color.White
                          )
                      }
                  }
              }
        },
        modifier = Modifier.fillMaxSize()
    ){
        Column(modifier=Modifier.fillMaxSize()) {

            HomeTabBar(
                tabSelected=tabSelected,
                onTabSelected = {tabSelected=it}
            )
            when(tabSelected){
                HomeTab.CHAT -> ChatView(mContext)
                HomeTab.CONTACT -> ContactView()
                HomeTab.STATUS -> StatusView()
            }
        }
    }
    
}

@Composable
fun HomeTabBar(
    tabSelected:HomeTab,
    onTabSelected:(HomeTab)->Unit
) {
    AppTabBar { tabBarModifier ->
        AppTabs(
            modifier = tabBarModifier,
            titles = HomeTab.values().map{it.name} ,
            tabSelected =tabSelected,
            onTabSelected ={newTab->
                onTabSelected(HomeTab.values()[newTab.ordinal])
            },
        )
        
    }
}