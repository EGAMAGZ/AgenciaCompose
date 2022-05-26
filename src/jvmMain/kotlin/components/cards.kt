package components

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
                        Text(
                            text = "Email: ${contact.email}"
                        )
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
                            Text(
                                text = "${contact.telefono}",
                                modifier = Modifier.offset(x = 4.dp)
                            )
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

@Composable
fun PersonaInfoCard(modifier: Modifier = Modifier, contact: Contact) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)) {
            Text(
                "Informacion del contacto:", style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Icon(
                    modifier = Modifier.size(96.dp),
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Perfil de Contacto"
                )
                Column(
                    modifier = Modifier.padding(8.dp)
                        .align(Alignment.CenterVertically),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        "${contact.nombre} ${contact.apellido}",
                        fontWeight = FontWeight.Bold
                    )
                    Text("Telefono: ${contact.telefono}")
                    Text("Email: ${contact.email}")
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PersonaInfoCardClickable(modifier: Modifier = Modifier, contact: Contact) {
    var showMore by remember { mutableStateOf(false) }
    var transition = updateTransition(showMore)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
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
                        Text(
                            text = "Email: ${contact.email}"
                        )
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
                            Text(
                                text = "${contact.telefono}",
                                modifier = Modifier.offset(x = 4.dp)
                            )
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun NotFoundCard(modifier: Modifier = Modifier, message: String) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                message,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}