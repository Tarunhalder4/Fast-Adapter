package com.example.lufickfastadapter.items;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lufickfastadapter.R;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.fastadapter.listeners.ClickEventHook;
import com.mikepenz.fastadapter.utils.EventHookUtil;

import java.util.List;

public class ImageItem extends AbstractItem<ImageItem, ImageItem.ViewHolder> {
    public String imageUrl;
    public String countryName;
    public String description;
    public boolean heartCLick;
    public Context context;
    public int heartImage;

    public ImageItem(Context context, String imageUrl, String countryName, String description, boolean heartCLick,  int heartImage) {
        this.imageUrl = imageUrl;
        this.countryName = countryName;
        this.description = description;
        this.heartCLick = heartCLick;
        this.context = context;
        this.heartImage = heartImage;
    }

    public ImageItem(Context context) {
        this.context = context;
    }

    public ImageItem setImage(String imageUrl){
        this.imageUrl = imageUrl;
        return this;
    }

    public ImageItem setCountryName(String countryName){
        this.countryName = countryName;
        return this;
    }

    public ImageItem setDescription(String description){
        this.description = description;
        return this;
    }

    public ImageItem setHeart(boolean heartCLick){
        this.heartCLick = heartCLick;
        return this;
    }

    public ImageItem setHeartImage(int heartImage){
        this.heartImage = heartImage;
        return this;
    }

    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.simple_image_row;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.simple_image_row;
    }

    public static class ViewHolder extends FastAdapter.ViewHolder <ImageItem>{
        ImageView simpleImage, heartImage;
        TextView country, description;
        //Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleImage = itemView.findViewById(R.id.simple_image);
            heartImage = itemView.findViewById(R.id.heart_image);
            country = itemView.findViewById(R.id.country);
            description = itemView.findViewById(R.id.description);
        }

        @Override
        public void bindView(ImageItem item, List<Object> payloads) {

            country.setText(item.countryName);
            description.setText(item.description);
            heartImage.setImageResource(item.heartImage);

            if(item.heartCLick){
                heartImage.setImageResource(R.drawable.red_heart_broken_24);
            }else {
                heartImage.setImageResource(R.drawable.wight_heart_broken_24);
            }

            Glide.with(item.context).load(item.imageUrl).placeholder(R.drawable.demo_image).into(simpleImage);
        }

        @Override
        public void unbindView(ImageItem item) {
            country.setText("");
            description.setText("");
            simpleImage.setImageResource(R.drawable.demo_image);
        }


    }

    public static class ImageItemHeartClickEvent extends ClickEventHook<ImageItem> {
        boolean heartClicked = true;

        @Nullable
        @Override
        public List<View> onBindMany(@NonNull RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof ImageItem.ViewHolder) {
                return EventHookUtil.toList(((ViewHolder) viewHolder).heartImage);
            }
            return super.onBindMany(viewHolder);
        }

        @Override
        public void onClick(View v, int position, FastAdapter<ImageItem> fastAdapter, ImageItem item) {
            ImageView imageView ;
            imageView = v.findViewById(R.id.heart_image);

            if(heartClicked){
                imageView.setImageResource(R.drawable.red_heart_broken_24);
                heartClicked = false;
            }else{
                imageView.setImageResource(R.drawable.wight_heart_broken_24);
                heartClicked =true;
            }

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }


    }





}
