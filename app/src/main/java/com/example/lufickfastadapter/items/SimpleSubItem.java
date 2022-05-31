package com.example.lufickfastadapter.items;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lufickfastadapter.R;
import com.mikepenz.fastadapter.IClickable;
import com.mikepenz.fastadapter.IExpandable;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.ISubItem;
import com.mikepenz.fastadapter.commons.utils.FastAdapterUIUtils;
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem;
import com.mikepenz.fastadapter_extensions.drag.IDraggable;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.List;

public class SimpleSubItem<Parent extends IItem & IExpandable & ISubItem & IClickable> extends AbstractExpandableItem<Parent, SimpleSubItem.ViewHolder, SimpleSubItem<Parent>> implements IDraggable<SimpleSubItem, IItem> {

    public String header;
    public StringHolder name;
    public StringHolder description;

    private boolean mIsDraggable = true;

    public SimpleSubItem<Parent> withHeader(String header) {
        this.header = header;
        return this;
    }

    public SimpleSubItem<Parent> withName(String Name) {
        this.name = new StringHolder(Name);
        return this;
    }

    public SimpleSubItem<Parent> withName(@StringRes int NameRes) {
        this.name = new StringHolder(NameRes);
        return this;
    }

    public SimpleSubItem<Parent> withDescription(String description) {
        this.description = new StringHolder(description);
        return this;
    }

    public SimpleSubItem<Parent> withDescription(@StringRes int descriptionRes) {
        this.description = new StringHolder(descriptionRes);
        return this;
    }

    @Override
    public boolean isDraggable() {
        return mIsDraggable;
    }

    @Override
    public SimpleSubItem withIsDraggable(boolean draggable) {
        this.mIsDraggable = draggable;
        return this;
    }

    /**
     * defines the type defining this item. must be unique. preferably an id
     *
     * @return the type
     */
    @Override
    public int getType() {
        return R.id.each_row;
    }

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    @Override
    public int getLayoutRes() {
        return R.layout.simple_each_row;
    }

    /**
     * binds the data of this item onto the viewHolder
     *
     * @param viewHolder the viewHolder of this item
     */
    @Override
    public void bindView(ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);

        //get the context
        Context ctx = viewHolder.itemView.getContext();

        //set the background for the item
        viewHolder.itemView.clearAnimation();
        ViewCompat.setBackground(viewHolder.itemView, FastAdapterUIUtils.getSelectableBackground(ctx, Color.RED, true));
        //set the text for the name
        StringHolder.applyTo(name, viewHolder.name);
        //set the text for the description or hide
        StringHolder.applyToOrHide(description, viewHolder.description);
    }

    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.name.setText(null);
        holder.description.setText(null);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description;
        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.age);
        }
    }
}