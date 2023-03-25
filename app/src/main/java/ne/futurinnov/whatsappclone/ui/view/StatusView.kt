package ne.futurinnov.whatsappclone.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ne.futurinnov.whatsappclone.R

@Composable
fun StatusView() {
    Row(
     modifier=Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    
    ){
        Box(modifier=Modifier.weight(2f)) {
            Image(
                painter= painterResource(id = R.drawable.ic_person),
                contentDescription = null
            )
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = null,
                tint = Color.Green,
                modifier = Modifier.offset(x=32.dp, y=32.dp)
            )
        }
        Column(modifier = Modifier
            .padding(horizontal = 8.dp)
            .weight(8f)) {
            Text("Status  abdoul", maxLines=1, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(6.dp))
            Text("Mahamadou Moussa status", fontSize = 15.sp, color=Color.Gray)
        }
    }
}