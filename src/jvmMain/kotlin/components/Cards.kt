package components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import entities.Contact


@Composable
fun PersonaCard(modifier: Modifier = Modifier, contact: Contact, onClickDelete: () -> Unit, onClickEdit: () -> Unit) {
    var showMore by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .clickable { showMore = !showMore },
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 18.dp, horizontal = 10.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessHigh
                    )
                )
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    "${contact.nombre} ${contact.apellido}",
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    AnimatedVisibility(
                        showMore,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Text("Email: ${contact.email}")
                    }
                    Text("${contact.telefono}")
                }
            }
            Row {
                IconButton(
                    onClick = { onClickEdit() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Editar Usuario",
                    )
                }

                IconButton(
                    onClick = { onClickDelete() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Borrar Usuario"
                    )
                }

            }

        }
    }
}