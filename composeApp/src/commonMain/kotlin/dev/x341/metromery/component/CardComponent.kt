package dev.x341.metromery.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.x341.metromery.model.Card
import org.jetbrains.compose.resources.painterResource

@Composable
fun CardComponent(
    card: Card,
    isFlipped: Boolean,
    onClick: () -> Unit
) {
    val painter = card.imagePath?.let { painterResource(it) }

    // El color primario ya viene inyectado con el color de la dificultad desde MetromeryLayout
    val difficultyColor = MaterialTheme.colorScheme.primary

    Card(
        onClick = onClick,
        // 1. Invertimos las medidas para que sea HORIZONTAL (ancho > alto)
        modifier = Modifier
            .size(80.dp, 120.dp)
            .clip(RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isFlipped) 2.dp else 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (!isFlipped) {
                // ==========================================
                // PARTE FRONTAL (Carta destapada)
                // ==========================================
                if (painter != null) {
                    Image(
                        painter = painter,
                        contentDescription = card.name,
                        // 2. Añadimos un padding para que el logo no toque los bordes
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        // 3. Cambiamos a 'Fit' para que escale sin recortarse
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Text(
                        text = card.name,
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                // ==========================================
                // PARTE TRASERA (Carta oculta)
                // ==========================================
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .drawBehind {
                            val canvasWidth = size.width
                            val canvasHeight = size.height
                            val spacing = 20.dp.toPx()
                            val strokeWidth = 1.5.dp.toPx()
                            val lineColor = difficultyColor.copy(alpha = 0.4f)

                            // Líneas diagonales hacia la derecha (\)
                            for (i in -canvasHeight.toInt() until canvasWidth.toInt() step spacing.toInt()) {
                                drawLine(
                                    color = lineColor,
                                    start = Offset(x = i.toFloat(), y = 0f),
                                    end = Offset(x = i.toFloat() + canvasHeight, y = canvasHeight),
                                    strokeWidth = strokeWidth
                                )
                            }

                            // Líneas diagonales hacia la izquierda (/)
                            for (i in 0 until (canvasWidth + canvasHeight).toInt() step spacing.toInt()) {
                                drawLine(
                                    color = lineColor,
                                    start = Offset(x = i.toFloat(), y = 0f),
                                    end = Offset(x = i.toFloat() - canvasHeight, y = canvasHeight),
                                    strokeWidth = strokeWidth
                                )
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    // Círculo central con la "M"
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .background(MaterialTheme.colorScheme.surface, CircleShape)
                            .border(2.dp, difficultyColor, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "M",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Black,
                            color = difficultyColor
                        )
                    }
                }
            }
        }
    }
}