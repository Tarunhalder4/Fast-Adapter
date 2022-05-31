package com.example.lufickfastadapter.stickyHeaderSample;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.example.lufickfastadapter.R;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.fastadapter_extensions.drag.IDraggable;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.List;

public class SimpleItem extends AbstractItem<SimpleItem, SimpleItem.ViewHolder> implements IDraggable<SimpleItem, IItem> {

    public String header;
    public String name;
    public String description;

    private boolean mIsDraggable = true;

    public SimpleItem withHeader(String header) {
        this.header = header;
        return this;
    }

    public SimpleItem withName(String name) {
        this.name = name;
        return this;
    }

//    public SimpleItem withName(@StringRes int NameRes) {
//        this.name = new StringHolder(NameRes);
//        return this;
//    }

    public SimpleItem withDescription(String description) {
        this.description = description;
        return this;
    }

    public SimpleItem withDescription(@StringRes int descriptionRes) {
        this.description = description;
        return this;
    }

    @Override
    public boolean isDraggable() {
        return mIsDraggable;
    }

    @Override
    public SimpleItem withIsDraggable(boolean draggable) {
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
        return R.id.sticky_header_simple_layout;
    }

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    @Override
    public int getLayoutRes() {
        return R.layout.sticky_header_simple_item;
    }

    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        return new ViewHolder(v);
    }


    protected static class ViewHolder extends FastAdapter.ViewHolder<SimpleItem> {

        TextView name, description;
        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.sticky_header_name);
            description = view.findViewById(R.id.sticky_header_description);
        }

        @Override
        public void bindView(@NonNull SimpleItem item, @NonNull List<Object> payloads) {
            name.setText((CharSequence) item.name);
            description.setText((CharSequence) item.description);
        }

        @Override
        public void unbindView(@NonNull SimpleItem item) {
            name.setText(null);
            description.setText(null);
        }
    }
}
