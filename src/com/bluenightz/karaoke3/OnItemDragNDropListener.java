package com.bluenightz.karaoke3;

import android.view.View;

import com.terlici.dragndroplist.DragNDropListView;

public interface OnItemDragNDropListener {
    // Called when the item begins dragging.
    public void onItemDrag(DragNDropListView parent, View view, int position, long id);

    // Called after the item is dropped in place
    public void onItemDrop(DragNDropListView parent, View view, int startPosition, int endPosition, long id);
}
