package components

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
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


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PersonaCard(modifier: Modifier = Modifier, contact: Contact, onClickDelete: () -> Unit, onClickEdit: () -> Unit) {
    var showMore by remember { mutableStateOf(false) }
    var transition = updateTransition(showMore)

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
                CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)) {

                    transition.AnimatedContent(
                        transitionSpec = {
                            if (targetState) {
                                slideInHorizontally { width -> -width } + fadeIn() with
                                        slideOutHorizontally { width -> width } + fadeOut()
                            } else {
                                slideInHorizontally { width -> width } + fadeIn() with
                                        slideOutHorizontally { width -> -width } + fadeOut()
                            }.using(
                                SizeTransform(clip = false)
                            )
                        }
                    ) {
                        if (it) {
                            Column {
                                Text(
                                    "Nombre: ${contact.nombre}"
                                )
                                Text(
                                    "Apellido: ${contact.apellido}"
                                )
                            }
                        } else {
                            Text(
                                "${contact.nombre} ${contact.apellido}"
                            )
                        }
                    }
                }
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    transition.AnimatedVisibility(
                        visible = { targetSelected -> targetSelected }
                    ) {
                        Text("Email: ${contact.email}")
                    }
                    transition.AnimatedContent(
                        transitionSpec = {
                            if (targetState) {
                                slideInHorizontally { width -> -width } + fadeIn() with
                                        slideOutHorizontally { width -> width } + fadeOut()
                            } else {
                                slideInHorizontally { width -> width } + fadeIn() with
                                        slideOutHorizontally { width -> -width } + fadeOut()
                            }.using(
                                SizeTransform(clip = false)
                            )
                        }
                    ) {
                        if (it) {
                            Text("Telefono: ${contact.telefono}")
                        } else {
                            Text("${contact.telefono}")
                        }
                    }
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