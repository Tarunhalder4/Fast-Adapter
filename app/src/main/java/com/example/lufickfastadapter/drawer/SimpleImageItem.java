package com.example.lufickfastadapter.drawer;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lufickfastadapter.R;
import com.mikepenz.fastadapter.commons.utils.FastAdapterUIUtils;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.util.UIUtils;

import java.util.List;

public class SimpleImageItem extends AbstractItem<SimpleImageItem, SimpleImageItem.ViewHolder> {

    private String mImageUrl;
    private String mName;
    private String mDescription;

    public SimpleImageItem withImage(String imageUrl) {
        this.mImageUrl = imageUrl;
        return this;
    }

    public SimpleImageItem withName(String name) {
        this.mName = name;
        return this;
    }

    public SimpleImageItem withDescription(String description) {
        this.mDescription = description;
        return this;
    }

    /**
     * defines the type defining this item. must be unique. preferably an id
     *
     * @return the type
     */
    @Override
    public int getType() {
        return R.id.simple_image_item_layout;
    }

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    @Override
    public int getLayoutRes() {
        return R.layout.simple_image_item;
    }

    /**
     * binds the data of this item onto the viewHolder
     *
     * @param viewHolder the viewHolder of this item
     */
    @Override
    public void bindView(SimpleImageItem.ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);

        //get the context
        Context ctx = viewHolder.itemView.getContext();

        //define our data for the view
        viewHolder.imageName.setText(mName);
        viewHolder.imageDescription.setText(mDescription);
        viewHolder.imageView.setImageBitmap(null);

        //set the background for the item
        int color = UIUtils.getThemeColor(ctx, androidx.appcompat.R.attr.actionBarSize);

        viewHolder.view.clearAnimation();
        viewHolder.view.setForeground(FastAdapterUIUtils.getSelectablePressedBackground(ctx, FastAdapterUIUtils.adjustAlpha(color, 100), 50, true));

        //load glide
        //Glide.clear(viewHolder.imageView);
        Glide.with(ctx).load(mImageUrl).into(viewHolder.imageView);
    }

    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
       // Glide.clear(holder.imageView);
        holder.imageView.setImageDrawable(null);
        holder.imageDescription.setText(null);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected FrameLayout view;
        protected ImageView imageView;
        protected TextView imageName;
        protected TextView imageDescription;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.item_image_img1);
            imageName = view.findViewById(R.id.item_image_name1);
            imageDescription = view.findViewById(R.id.item_image_description1);

            this.view = (FrameLayout) view;

            //optimization to preset the correct height for our device
            int screenWidth = view.getContext().getResources().getDisplayMetrics().widthPixels;
            int finalHeight = (int) (screenWidth / 1.5) / 2;
            imageView.setMinimumHeight(finalHeight);
            imageView.setMaxHeight(finalHeight);
            imageView.setAdjustViewBounds(false);
            //set height as layoutParameter too
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) imageView.getLayoutParams();
            lp.height = finalHeight;
            imageView.setLayoutParams(lp);
        }
    }
}