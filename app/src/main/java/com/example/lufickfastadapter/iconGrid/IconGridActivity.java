package com.example.lufickfastadapter.iconGrid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.LayoutInflaterCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lufickfastadapter.R;
import com.example.lufickfastadapter.databinding.ActivityIconGridBinding;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.expandable.ExpandableExtension;
import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.mikepenz.iconics.typeface.ITypeface;
import com.mikepenz.itemanimators.SlideDownAlphaAnimator;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.materialize.MaterializeBuilder;
import com.mikepenz.materialize.color.Material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IconGridActivity extends AppCompatActivity {

    private FastItemAdapter fastItemAdapter;
    ActivityIconGridBinding binding;
    String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        findViewById(android.R.id.content).setSystemUiVisibility(findViewById(android.R.id.content).getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        //as we use an icon from Android-Iconics via xml we add the IconicsLayoutInflater
        //https://github.com/mikepenz/Android-Iconics
        super.onCreate(savedInstanceState);
        binding = ActivityIconGridBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    //    LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        // Handle Toolbar
        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle(R.string.title_icon_grid_list_activity);

        //style our ui
        new MaterializeBuilder().withActivity(this).build();

        //create our FastAdapter which will manage everything
        fastItemAdapter = new FastItemAdapter();

        //we want to have expandables
        ExpandableExtension expandableExtension = new ExpandableExtension();
        fastItemAdapter.addExtension(expandableExtension);

        //get our recyclerView and do basic setup

        //init our gridLayoutManager and configure RV
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (fastItemAdapter.getItemViewType(position)) {
                    case R.id.fastadapter_expandable_item_id:
                        return 3;
                    case R.id.fastadapter_icon_item_id:
                        return 1;
                    default:
                        return -1;
                }
            }
        });

        binding.rec.setLayoutManager(gridLayoutManager);
        binding.rec.setItemAnimator(new SlideDownAlphaAnimator());
        binding.rec.setAdapter(fastItemAdapter);

        //order fonts by their name
        //List<ITypeface> mFonts = new ArrayList<>(Iconics.getRegisteredFonts(this));

       // List<MaterialDesignIconic> mFonts = new ArrayList<>();
        MaterialDesignIconic mFonts = new MaterialDesignIconic();
        Log.d(TAG, "onCreate12: "+ mFonts.getFontName());
        Log.d(TAG, "onCreate12: "+ mFonts.getIcons());
        Log.d(TAG, "onCreate12: "+ mFonts.getAuthor());
       // Log.d(TAG, "onCreate4: "+mFonts.size());

//        Collections.sort(mFonts, new Comparator<ITypeface>() {
//            @Override
//            public int compare(final ITypeface object1, final ITypeface object2) {
//                return object1.getFontName().compareTo(object2.getFontName());
//            }
//        });
//        Toast.makeText(IconGridActivity.this,"dummy1",Toast.LENGTH_SHORT).show();

        //add all icons of all registered Fonts to the list
        int count = 0;
        ArrayList<SimpleSubExpandableItem> items = new ArrayList<>(Iconics.getRegisteredFonts(this).size());
        Log.d(TAG, "onCreate78: " + Iconics.getRegisteredFonts(IconGridActivity.this).size());
        Log.d(TAG, "onCreate79: " + Iconics.getRegisteredFonts(IconGridActivity.this));
        Toast.makeText(IconGridActivity.this,"dummy2",Toast.LENGTH_SHORT).show();


       // for (ITypeface font : mFonts) {
            //we set the identifier from the count here, as I need a stable ID in the sample to showcase the state restore
            SimpleSubExpandableItem expandableItem = new SimpleSubExpandableItem();
            expandableItem.withName(mFonts.getFontName()).withIdentifier(count);
            Log.d("tag", "onCreate: "+ mFonts.getFontName());
            Toast.makeText(IconGridActivity.this,mFonts.getFontName(),Toast.LENGTH_SHORT).show();
            Toast.makeText(IconGridActivity.this,"dummy",Toast.LENGTH_SHORT).show();


            //ArrayList<IItem> icons = new ArrayList<>();
            ArrayList<String> icons = new ArrayList<>();
            //for (String icon : mFonts.getIcons()) {
                ///IconItem iconItem = new IconItem();
                //iconItem.withIcon(mFonts.getIcon(icon));
                //icons.add(iconItem);

              //  Log.d("tag", "onCreate: "+ mFonts.getIcons());
           // }
            //expandableItem.withSubItems(icons);
           // expandableItem.withSubItems(mFonts.getIcons());

            items.add(expandableItem);
            count++;
       // }

        //fill with some sample data
        fastItemAdapter.add(items);

        //if first start we want to expand the item with ID 2
        if (savedInstanceState != null) {
            //restore selections (this has to be done after the items were added
            fastItemAdapter.withSavedInstanceState(savedInstanceState);
        } else {
            //expand one item to make sample look a bit more interesting
            expandableExtension.expand(2);
        }

        //set the back arrow in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the adapter to the bundle
        outState = fastItemAdapter.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}