package com.example.lufickfastadapter.swipeSample;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lufickfastadapter.R;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.fastadapter_extensions.drag.IDraggable;
import com.mikepenz.fastadapter_extensions.swipe.ISwipeable;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.List;

public class SwipeAbleItem extends AbstractItem<SwipeAbleItem, SwipeAbleItem.ViewHolder> implements ISwipeable<SwipeAbleItem, IItem>,IDraggable<SwipeAbleItem,IItem> {
    public StringHolder name, description;

    public boolean swipeAble = true;
    public boolean draggable = true;
    public int swipedDirection;
    private Runnable swipedAction;

    public SwipeAbleItem withName(StringHolder name){
        this.name = name;
        return this;
    }

    public SwipeAbleItem withDescription(StringHolder description){
        this.description = description;
        return this;
    }


    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.swiped_layout;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.swipe_able_item;
    }

    @Override
    public boolean isSwipeable() {
        return swipeAble;
    }

    @Override
    public SwipeAbleItem withIsSwipeable(boolean swipeable) {
        this.swipeAble = swipeable;
        return this;
    }

    @Override
    public boolean isDraggable() {
        return draggable;
    }

    @Override
    public SwipeAbleItem withIsDraggable(boolean draggable) {
        this.draggable = draggable;
        return this;
    }

    public void setSwipedDirection(int swipedDirection){
        this.swipedDirection = swipedDirection;
    }

    public void setSwipedAction(Runnable action){
        this.swipedAction = action;
    }


    @Override
    public void bindView(ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);

        viewHolder.name.setText(name.toString());
        viewHolder.description.setText(description.toString());

        viewHolder.swipeDeleteContainLayout.setVisibility(swipedDirection != 0 ? View.VISIBLE : View.GONE);
        viewHolder.textViewContainLayout.setVisibility(swipedDirection != 0 ? View.GONE : View.VISIBLE);


        CharSequence swipedAction = null;
        CharSequence swipedText = null;
        if (swipedDirection != 0) {
            swipedAction = viewHolder.itemView.getContext().getString(R.string.undo);
            swipedText = swipedDirection == ItemTouchHelper.LEFT ? "Removed" : "Archived";
            viewHolder.swipeDeleteContainLayout.setBackgroundColor(ContextCompat.getColor(viewHolder.itemView.getContext(), swipedDirection == ItemTouchHelper.LEFT ? R.color.red : R.color.purple_700));
        }
        viewHolder.swipedRemoved.setText(swipedAction == null ? "" : swipedAction);
        viewHolder.swipedUndo.setText(swipedText == null ? "" : swipedText);
        viewHolder.swipedActionRunnable = this.swipedAction;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder{
        public Runnable swipedActionRunnable;
        View textViewContainLayout;
        View swipeDeleteContainLayout;
        TextView name, description;
        TextView swipedUndo;
        TextView swipedRemoved;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.swiped_name);
            description = itemView.findViewById(R.id.swipe_description);
            swipedUndo = itemView.findViewById(R.id.swiped_undo);
            swipedRemoved = itemView.findViewById(R.id.swiped_remove);
            textViewContainLayout = itemView.findViewById(R.id.text_content_layout);
            swipeDeleteContainLayout = itemView.findViewById(R.id.swipe_delete_content_layout);
        }
    }

}
