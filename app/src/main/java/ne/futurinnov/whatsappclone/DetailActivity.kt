package ne.futurinnov.whatsappclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.coil.rememberCoilPainter
import github.farhanroy.whatsappclone.data.Chat
import github.farhanroy.whatsappclone.data.DummyData
import github.farhanroy.whatsappclone.data.Message
import ne.futurinnov.whatsappclone.ui.theme.WhatsAppCloneTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsAppCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   val chatId = intent.getIntExtra("Extra_chatid", 0)
                    DetailView(chatId)
                }
            }
        }
    }
}

@Composable
fun DetailView(
    chatId:Int
) {
    val chat=DummyData.listChat.filter { it.id == chatId }[0]
    Scaffold (
        backgroundColor = Color(0xFFEDEDED),
        topBar = {MessageTopBar(chat)},
        bottomBar = {MessageBox()},
        content={MessageList()}
            )
}

@Composable
fun MessageTopBar(chat: Chat) {
    TopAppBar(
        title = {
            Column(Modifier.padding(start = 16.dp)) {
                Text(chat.name, fontSize = 17.sp)
                Text(chat.time, fontSize = 17.sp, fontWeight = FontWeight.Light)
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Call, contentDescription =null , tint=Color.White)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription =null , tint=Color.White)
            }
        },
        navigationIcon ={
            Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 8.dp)
        ){
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            Image(
                painter = rememberCoilPainter(request = chat.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
        }
        }
    )
}

@Composable
fun MessageList() {
    val listMessage=DummyData.listMessage
    LazyColumn{
        items(listMessage){
            Spacer(modifier = Modifier.height(8.dp))
            if(it.isPeer){
                 PeerBubble(it)
            }else{
                UserBubble(it)
            }
        }
    }
    
}

@Composable
fun PeerBubble(message: Message) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp, end = 80.dp)
        .background(color = Color.White)
        .clip(RoundedCornerShape(8.dp))
    ){
        Row (
            modifier = Modifier
                .padding(10.dp)
                ){
            Column(Modifier.weight(3.0f, true)) {
                Text(
                    text=message.message,
                    fontSize = 16.sp,
                    color= Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun UserBubble(message: Message) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(80.dp, end = 10.dp)
            .background(color = Color(0xFFD0FFC4))
            .clip(RoundedCornerShape(8.dp))
    ){
        Row(Modifier.padding(all=10.dp)){
            Column(Modifier.weight(3.0f, true), ) {
                Text(
                    text =message.message,
                    fontSize = 16.sp,
                    color= Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }


}

@Composable
fun MessageBox() {
    val textState =remember{
        mutableStateOf(TextFieldValue())
    }
    Box(
        Modifier
            .background(MaterialTheme.colors.primary)
    ){
        Row(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        ) {
            BasicTextField(
                value = textState.value,
                onValueChange = {textState.value=it}
            )
            Spacer(modifier =Modifier.size(12.dp))
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
                
            }

        }
    }
}

