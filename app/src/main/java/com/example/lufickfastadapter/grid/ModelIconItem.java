package com.example.lufickfastadapter.grid;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lufickfastadapter.R;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.fastadapter.items.ModelAbstractItem;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.view.IconicsImageView;

import java.util.List;

public class ModelIconItem extends  ModelAbstractItem<com.example.lufickfastadapter.grid.IconModel, ModelIconItem, ModelIconItem.ViewHolder> {

    public ModelIconItem(com.example.lufickfastadapter.grid.IconModel icon) {
        super(icon);
    }

    /**
     * defines the type defining this item. must be unique. preferably an id
     *
     * @return the type
     */
    @Override
    public int getType() {
        return R.id.grid_layout;
    }

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    @Override
    public int getLayoutRes() {
        return R.layout.grid_item;
    }

    /**
     * binds the data of this item onto the viewHolder
     *
     * @param viewHolder the viewHolder of this item
     */
    @Override
    public void bindView(ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);

        //define our data for the view
        viewHolder.image.setIcon(new IconicsDrawable(viewHolder.image.getContext(), getModel().icon));
        viewHolder.name.setText(getModel().icon.getName());
    }

    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.image.setImageDrawable(null);
        holder.name.setText(null);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected View view;
        public TextView name;
        public IconicsImageView image;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.grid_name);
            image = view.findViewById(R.id.grid_icon);
            this.view = view;
        }
    }
}
