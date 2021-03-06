package com.example.lufickfastadapter.multiSelectItem;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.example.lufickfastadapter.R;
import com.mikepenz.fastadapter.commons.utils.FastAdapterUIUtils;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.fastadapter_extensions.drag.IDraggable;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialize.util.UIUtils;

import java.util.List;


/**
 * Created by mikepenz on 28.12.15.
 */
public class SimpleItem extends AbstractItem<SimpleItem, SimpleItem.ViewHolder> implements IDraggable<SimpleItem, IItem> {

    public String header;
    public StringHolder name;
    public StringHolder description;

    private boolean mIsDraggable = true;

    public SimpleItem withHeader(String header) {
        this.header = header;
        return this;
    }

    public SimpleItem withName(String Name) {
        this.name = new StringHolder(Name);
        return this;
    }

    public SimpleItem withName(@StringRes int NameRes) {
        this.name = new StringHolder(NameRes);
        return this;
    }

    public SimpleItem withDescription(String description) {
        this.description = new StringHolder(description);
        return this;
    }

    public SimpleItem withDescription(@StringRes int descriptionRes) {
        this.description = new StringHolder(descriptionRes);
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
        return R.id.multi_select_each_row_layout;
    }

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    @Override
    public int getLayoutRes() {
        return R.layout.multi_select_each_item;
    }

    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        return new ViewHolder(v);
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends FastAdapter.ViewHolder<SimpleItem> {
        protected View view;
        TextView name;
        TextView description;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.multi_select_name);
            description = view.findViewById(R.id.multi_select_description);
            this.view = view;
        }

        @Override
        public void bindView(@NonNull SimpleItem item, @NonNull List<Object> payloads) {
            //get the context
            Context ctx = itemView.getContext();

            //set the background for the item
            UIUtils.setBackground(view, FastAdapterUIUtils.getSelectableBackground(ctx, Color.RED, true));
            //set the text for the name
            StringHolder.applyTo(item.name, name);
            //set the text for the description or hide
            StringHolder.applyToOrHide(item.description, description);
        }

        @Override
        public void unbindView(@NonNull SimpleItem item) {
            name.setText(null);
            description.setText(null);
        }
    }
}
