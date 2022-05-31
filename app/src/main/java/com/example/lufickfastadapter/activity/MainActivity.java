package com.example.lufickfastadapter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lufickfastadapter.databinding.ActivityMainBinding;
import com.example.lufickfastadapter.iconGrid.IconGridActivity;
import com.example.lufickfastadapter.multiSelectItem.MultiSelectActivity;
import com.example.lufickfastadapter.sortSample.SortActivity;
import com.example.lufickfastadapter.stickyHeaderSample.StickyHeaderSampleActivity;
import com.example.lufickfastadapter.swipeSample.SwipeActivity;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolBar);

        binding.simpleListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SimpleListActivity.class);
                startActivity(intent);
            }
        });

        binding.simpleImageListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SimpleImageListActivity.class);
                startActivity(intent);
            }
        });

        binding.simpleExpandableListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ExpandableSampleActivity.class);
                startActivity(intent);
            }
        });

        binding.radioButtonListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RadioButtonActivity.class);
                startActivity(intent);
            }
        });

        binding.checkboxButtonListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CheckBoxActivity.class);
                startActivity(intent);
            }
        });

        binding.simpleStickyListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StickyHeaderSampleActivity.class);
                startActivity(intent);
            }
        });

        binding.swipeListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SwipeActivity.class);
                startActivity(intent);
            }
        });

        binding.sortListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SortActivity.class);
                startActivity(intent);
            }
        });

//        binding.endLessListActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, EndLessActivity.class);
//                startActivity(intent);
//            }
//        });

        binding.multipleSelectListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MultiSelectActivity.class);
                startActivity(intent);
            }
        });

        binding.endLessListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IconGridActivity.class);
                startActivity(intent);
            }
        });







    }
}