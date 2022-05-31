package com.example.lufickfastadapter.items;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.lufickfastadapter.R;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

public class SimpleItem extends AbstractItem <SimpleItem, SimpleItem.ViewHolder> {

    public SimpleItem(String name) {
        this.name = name;
    }

    public String name;
    public String age;

    public SimpleItem(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int getType() {
        return R.id.each_row;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.simple_each_row;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(@NonNull View view) {
        return new ViewHolder(view);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    protected static class ViewHolder extends FastAdapter.ViewHolder<SimpleItem>{
        TextView name,age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
        }

        @Override
        public void bindView(SimpleItem item, List<Object> payloads) {
            name.setText(item.name);
            age.setText(item.age);
        }


        @Override
        public void unbindView(@NonNull SimpleItem item) {
            name.setText(null);
            age.setText(null);
        }
    }
}
