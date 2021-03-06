package com.example.lufickfastadapter.stickyHeaderSample;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lufickfastadapter.R;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IExpandable;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.ISubItem;
import com.mikepenz.fastadapter.commons.utils.FastAdapterUIUtils;
import com.mikepenz.fastadapter.expandable.items.AbstractExpandableItem;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.List;

public class SimpleSubExpandableItem <Parent extends IItem & IExpandable, SubItem extends IItem & ISubItem> extends
        AbstractExpandableItem<SimpleSubExpandableItem<Parent, SubItem>, SimpleSubExpandableItem.ViewHolder, SubItem> {

    public String header;
    public StringHolder name;
    public StringHolder description;

    private OnClickListener<SimpleSubExpandableItem> mOnClickListener;

    public SimpleSubExpandableItem<Parent, SubItem> withHeader(String header) {
        this.header = header;
        return this;
    }

    public SimpleSubExpandableItem<Parent, SubItem> withName(String Name) {
        this.name = new StringHolder(Name);
        return this;
    }

    public SimpleSubExpandableItem<Parent, SubItem> withName(@StringRes int NameRes) {
        this.name = new StringHolder(NameRes);
        return this;
    }

    public SimpleSubExpandableItem<Parent, SubItem> withDescription(String description) {
        this.description = new StringHolder(description);
        return this;
    }

    public SimpleSubExpandableItem<Parent, SubItem> withDescription(@StringRes int descriptionRes) {
        this.description = new StringHolder(descriptionRes);
        return this;
    }

    public OnClickListener<SimpleSubExpandableItem> getOnClickListener() {
        return mOnClickListener;
    }

    public SimpleSubExpandableItem<Parent, SubItem> withOnClickListener(OnClickListener<SimpleSubExpandableItem> mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
        return this;
    }

    //we define a clickListener in here so we can directly animate
    final private OnClickListener<SimpleSubExpandableItem<Parent, SubItem>> onClickListener = new OnClickListener<SimpleSubExpandableItem<Parent, SubItem>>() {
        @Override
        public boolean onClick(View v, IAdapter adapter, @NonNull SimpleSubExpandableItem item, int position) {
            if (item.getSubItems() != null) {
                if (!item.isExpanded()) {
                    ViewCompat.animate(v.findViewById(R.id.expendable_icon)).rotation(180).start();
                } else {
                    ViewCompat.animate(v.findViewById(R.id.expendable_icon)).rotation(0).start();
                }
                return mOnClickListener == null || mOnClickListener.onClick(v, adapter, item, position);
            }
            return mOnClickListener != null && mOnClickListener.onClick(v, adapter, item, position);
        }
    };

    /**
     * we overwrite the item specific click listener so we can automatically animate within the item
     *
     * @return
     */
    @Override
    public OnClickListener<SimpleSubExpandableItem<Parent, SubItem>> getOnItemClickListener() {
        return onClickListener;
    }

    @Override
    public boolean isSelectable() {
        //this might not be true for your application
        return getSubItems() == null;
    }

    /**
     * defines the type defining this item. must be unique. preferably an id
     *
     * @return the type
     */
    @Override
    public int getType() {
        return R.id.expandable_item_layout;
    }

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    @Override
    public int getLayoutRes() {
        return R.layout.expandable_item;
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

        if (getSubItems() == null || getSubItems().size() == 0) {
            viewHolder.icon.setVisibility(View.GONE);
        } else {
            viewHolder.icon.setVisibility(View.VISIBLE);
        }

        if (isExpanded()) {
            ViewCompat.setRotation(viewHolder.icon, 0);
        } else {
            ViewCompat.setRotation(viewHolder.icon, 180);
        }
    }

    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.name.setText(null);
        holder.description.setText(null);
        //make sure all animations are stopped
        holder.icon.clearAnimation();
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
       ImageView icon;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.expendable_name);
            description = view.findViewById(R.id.expendable_description);
            icon = view.findViewById(R.id.expendable_icon);
        }
    }
}
