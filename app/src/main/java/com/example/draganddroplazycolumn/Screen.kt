package com.example.draganddroplazycolumn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.draganddroplazycolumn.ui.theme.DragAndDropLazyColumnTheme
import kotlinx.coroutines.channels.Channel

@Composable
fun DragAndDropLazyColumnScreen(
    list: List<Music>,
    onMoved: (List<Music>) -> Unit = {}
) {
    val lazyListState = rememberLazyListState()
    var rememberList by remember {
        mutableStateOf(list)
    }

    var draggingItemIndex: Int? by remember {
        mutableStateOf(null)
    }

    var delta: Float by remember {
        mutableStateOf(0f)
    }

    var draggingItem: LazyListItemInfo? by remember {
        mutableStateOf(null)
    }

    val onMove = { fromIndex: Int, toIndex: Int ->
        if (fromIndex >= 3 && toIndex >= 3) {
            rememberList =
                rememberList.toMutableList().apply { add(toIndex - 3, removeAt(fromIndex - 3)) }
        }
    }

    val scrollChannel = Channel<Float>()

    LaunchedEffect(lazyListState) {
        while (true) {
            val diff = scrollChannel.receive()
            lazyListState.scrollBy(diff)
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .pointerInput(
                key1 = lazyListState
            ) {
                detectDragGesturesAfterLongPress(
                    onDragStart = { offset ->
                        // detect the item that is being dragged and if it is Draggable
                        lazyListState.layoutInfo.visibleItemsInfo
                            .firstOrNull { item ->
                                // ドラッグ位置からドラッグされるアイテムを探す
                                offset.y.toInt() in item.offset..(item.offset + item.size)
                            }?.also { draggedItem ->
                                // dragされているアイテムそのものを保存しておく
                                draggingItem = draggedItem
                                // dragされているアイテムのインデックスを保存しておく
                                draggingItemIndex = draggedItem.index
                            }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        if ((draggingItemIndex ?: 0) < 3) return@detectDragGesturesAfterLongPress
                        delta += dragAmount.y

                        val currentDraggingItemIndex =
                            draggingItemIndex ?: return@detectDragGesturesAfterLongPress
                        val currentDraggingItem =
                            draggingItem ?: return@detectDragGesturesAfterLongPress

                        val startOffset = currentDraggingItem.offset + delta
                        val endOffset =
                            currentDraggingItem.offset + currentDraggingItem.size + delta
                        val middleOffset = startOffset + (endOffset - startOffset) / 2

                        val targetItem =
                            lazyListState.layoutInfo.visibleItemsInfo.find { item ->
                                middleOffset.toInt() in item.offset..item.offset + item.size &&
                                        currentDraggingItem.index != item.index
                            }

                        if (targetItem != null) {
                            val targetIndex = targetItem.index
                            onMove(currentDraggingItemIndex, targetIndex)
                            draggingItemIndex = targetIndex
                            draggingItem = targetItem
                            delta += currentDraggingItem.offset - targetItem.offset
                        } else {
                            val startOffsetToTop =
                                startOffset - lazyListState.layoutInfo.viewportStartOffset
                            val endOffsetToBottom =
                                endOffset - lazyListState.layoutInfo.viewportEndOffset
                            val scroll =
                                when {
                                    startOffsetToTop < 0 -> startOffsetToTop.coerceAtMost(0f)
                                    endOffsetToBottom > 0 -> endOffsetToBottom.coerceAtLeast(0f)
                                    else -> 0f
                                }
                            val canScrollDown = currentDraggingItemIndex - 3 != rememberList.size - 1 && endOffsetToBottom > 0
                            val canScrollUp = currentDraggingItemIndex - 3 != 0 && startOffsetToTop < 0
                            if (scroll != 0f && (canScrollUp || canScrollDown)) {
                                scrollChannel.trySend(scroll)
                            }
                        }
                    },
                    onDragEnd = {
                        onMoved(rememberList)
                        draggingItemIndex = null
                        draggingItem = null
                        delta = 0f
                    },
                    onDragCancel = {
                        draggingItemIndex = null
                        draggingItem = null
                        delta = 0f
                    },
                )
            }
            .fillMaxWidth()
            .wrapContentHeight(),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Row {
                Text(
                    text = "キャンセル",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "保存",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    )
                )
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(30.dp)
                        .background(Color.Cyan)
                )
            }
        }
        item {
            Box {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                            .background(Color.White)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(Color.White)
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
        itemsIndexed(
            items = rememberList,
            contentType = { _, item -> item }
        ) { index, item ->
            DragAndDropListCell(
                music = item,
            )
        }
    }
}

@Composable
fun DragAndDropListCell(
    modifier: Modifier = Modifier,
    music: Music
) {
    Row(
        modifier = Modifier
            .background(Color.Black)
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(60.dp),
            painter = painterResource(id = music.imageId),
            contentDescription = "Thumbnail"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = music.title,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    lineHeight = 24.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = music.artist,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                    lineHeight = 18.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_sort),
            contentDescription = "Sort Icon",
            modifier = Modifier.size(60.dp)
        )
    }
}

@Preview
@Composable
fun PreviewDragAndDropListCell() {
    DragAndDropLazyColumnTheme {
        DragAndDropListCell(
            music = Music(
                R.drawable.onepiece01_luffy,
                "ルフィー",
                "OnePiece"
            )
        )
    }
}

@Preview
@Composable
fun PreviewDragAndDropLazyList() {
    DragAndDropLazyColumnTheme {
        DragAndDropLazyColumnScreen(
            list = Music.samples()
        )
    }
}