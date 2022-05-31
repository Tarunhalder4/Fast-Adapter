package com.example.lufickfastadapter.sortSample;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.lufickfastadapter.R;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

public class SimpleItem extends AbstractItem<SimpleItem, SimpleItem.ViewHolder> {
    public String  name;
    public String description;

    public SimpleItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public SimpleItem(String name, String description) {
        this.name = name;
        this.description = description;
    }


    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.sort_each_row_layout;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.sort_each_row;
    }


    protected class ViewHolder extends FastAdapter.ViewHolder<SimpleItem>{
        TextView name, description;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sort_name);
            description = itemView.findViewById(R.id.sort_description);
        }

        @Override
        public void bindView(SimpleItem item, List<Object> payloads) {
            name.setText(item.name);
            description.setText(item.description);
        }

        @Override
        public void unbindView(SimpleItem item) {
            name.setText("");
            description.setText("");
        }
    }


}
