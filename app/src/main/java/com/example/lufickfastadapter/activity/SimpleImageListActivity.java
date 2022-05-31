package com.example.lufickfastadapter.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lufickfastadapter.R;
import com.example.lufickfastadapter.dammyData.ImageDammyData;
import com.example.lufickfastadapter.items.ImageItem;
import com.example.lufickfastadapter.items.SimpleItem;
import com.example.lufickfastadapter.databinding.ActivitySimpleImageListBinding;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.listeners.EventHook;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.mikepenz.fastadapter_extensions.drag.SimpleDragCallback;

import java.util.List;

public class SimpleImageListActivity extends AppCompatActivity {

    private ActivitySimpleImageListBinding binding;
    private FastAdapter<ImageItem> fastAdapter;
    private ItemAdapter<ImageItem> itemAdapter;

    private SimpleDragCallback touchCallback;
    private ItemTouchHelper touchHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySimpleImageListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
//        getActionBar().setTitle(R.string.title_simple_image_activity);

        fastAdapter = new FastItemAdapter<>();
        itemAdapter = new ItemAdapter();
        itemAdapter.add(new ImageDammyData(SimpleImageListActivity.this).getImageItems());
        fastAdapter = FastAdapter.with(itemAdapter);

        int count = getResources().getInteger(R.integer.simple_image_grid_count);
        if (count==1){
            binding.rec.setLayoutManager(new LinearLayoutManager(SimpleImageListActivity.this));
        }else {
            binding.rec.setLayoutManager(new GridLayoutManager(SimpleImageListActivity.this,count));
        }

        binding.rec.setItemAnimator(new DefaultItemAnimator());

        binding.rec.setAdapter(fastAdapter);

        fastAdapter.withEventHook(new ImageItem.ImageItemHeartClickEvent());
        fastAdapter.withSavedInstanceState(savedInstanceState);


        fastAdapter.withOnClickListener(new OnClickListener<ImageItem>() {
            @Override
            public boolean onClick(@Nullable View v, IAdapter<ImageItem> adapter, ImageItem item, int position) {
                Toast.makeText(SimpleImageListActivity.this,"item cklick",Toast.LENGTH_SHORT).show();
                item.setHeartImage(R.drawable.red_heart_broken_24);
                return true;
            }
        });


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState = fastAdapter.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
}