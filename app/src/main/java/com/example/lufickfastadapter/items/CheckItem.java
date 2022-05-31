package com.example.lufickfastadapter.items;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lufickfastadapter.R;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.fastadapter.listeners.ClickEventHook;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.AbstractList;
import java.util.List;

public class CheckItem extends AbstractItem<CheckItem, CheckItem.ViewHolder> {

    public String header;
    public StringHolder name;
    public StringHolder description;

    public CheckItem withHeader(String header) {
        this.header = header;
        return this;
    }

    public CheckItem withName(String Name) {
        this.name = new StringHolder(Name);
        return this;
    }

    public CheckItem withName(@StringRes int NameRes) {
        this.name = new StringHolder(NameRes);
        return this;
    }

    public CheckItem withDescription(String description) {
        this.description = new StringHolder(description);
        return this;
    }

    public CheckItem withDescription(@StringRes int descriptionRes) {
        this.description = new StringHolder(descriptionRes);
        return this;
    }

    /**
     * defines the type defining this item. must be unique. preferably an id
     *
     * @return the type
     */
    @Override
    public int getType() {
        return R.id.checkbox_layout;
    }

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    @Override
    public int getLayoutRes() {
        return R.layout.chech_box_list;
    }

    /**
     * binds the data of this item onto the viewHolder
     *
     * @param viewHolder the viewHolder of this item
     */
    @Override
    public void bindView(ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);

        viewHolder.checkBox.setChecked(isSelected());

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
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView name, description;
        public ViewHolder(View view) {
            super(view);
            checkBox = view.findViewById(R.id.checkbox);
            name = view.findViewById(R.id.checkbox_name);
            description = view.findViewById(R.id.checkbox_description);

        }
    }

    public static class CheckBoxClickEvent extends ClickEventHook<CheckItem> {
        @Override
        public View onBind(@NonNull RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof CheckItem.ViewHolder) {
                return ((CheckItem.ViewHolder) viewHolder).checkBox;
            }
            return null;
        }

        @Override
        public void onClick(View v, int position, FastAdapter<CheckItem> fastAdapter, CheckItem item) {
            fastAdapter.toggleSelection(position);
        }
    }
}
