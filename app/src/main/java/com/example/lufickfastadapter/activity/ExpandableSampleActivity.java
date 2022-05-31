package com.example.lufickfastadapter.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.LayoutInflaterCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.lufickfastadapter.R;
import com.example.lufickfastadapter.databinding.ActivityExpandableSampleBinding;
import com.example.lufickfastadapter.items.SimpleSubExpandableItem;
import com.example.lufickfastadapter.items.SimpleSubItem;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.expandable.ExpandableExtension;
import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.mikepenz.materialize.MaterializeBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ExpandableSampleActivity extends AppCompatActivity {

    private FastItemAdapter<IItem> fastItemAdapter;
    private ExpandableExtension<IItem> expandableExtension;

    private ActivityExpandableSampleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        findViewById(android.R.id.content).setSystemUiVisibility(findViewById(android.R.id.content).getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        //as we use an icon from Android-Iconics via xml we add the IconicsLayoutInflater
        //https://github.com/mikepenz/Android-Iconics
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        binding = ActivityExpandableSampleBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        // Handle Toolbar

        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle(R.string.title_simple_expandable_list_activity);

        //style our ui
        new MaterializeBuilder().withActivity(this).build();

        //create our FastAdapter
        fastItemAdapter = new FastItemAdapter<>();
        fastItemAdapter.withSelectable(true);
        expandableExtension = new ExpandableExtension<>();
        //expandableExtension.withOnlyOneExpandedItem(true);
        fastItemAdapter.addExtension(expandableExtension);

        //get our recyclerView and do basic setup
        binding.rec.setLayoutManager(new LinearLayoutManager(this));
        //binding.rec.setItemAnimator(new SlideDownAlphaAnimator());
        binding.rec.setAdapter(fastItemAdapter);

        //fill with some sample data
        List<IItem> items = new ArrayList<>();

        AtomicInteger identifier = new AtomicInteger(1);
        for (int i = 1; i <= 100; i++) {
            if (i % 3 != 0) {
                items.add(new SimpleSubItem().withName("Test " + i).withIdentifier(identifier.getAndIncrement()));
                continue;
            }

            SimpleSubExpandableItem parent = new SimpleSubExpandableItem();
            parent.withName("Test " + i).withIdentifier(identifier.getAndIncrement());

            List<IItem> subItems = new LinkedList<>();
            for (int ii = 1; ii <= 5; ii++) {
                SimpleSubExpandableItem subItem = new SimpleSubExpandableItem();
                subItem.withName("-- SubTest " + ii).withIdentifier(identifier.getAndIncrement());

                if (ii % 2 == 0) {
                    continue;
                }

                List<IItem> subSubItems = new LinkedList<>();
                for (int iii = 1; iii <= 3; iii++) {
                    SimpleSubExpandableItem subSubItem = new SimpleSubExpandableItem();
                    subSubItem.withName("---- SubSubTest " + iii).withIdentifier(identifier.getAndIncrement());

                    List<IItem> subSubSubItems = new LinkedList<>();
                    for (int iiii = 1; iiii <= 4; iiii++) {
                        SimpleSubExpandableItem subSubSubItem = new SimpleSubExpandableItem();
                        subSubSubItem.withName("---- SubSubSubTest " + iiii).withIdentifier(identifier.getAndIncrement());
                        subSubSubItems.add(subSubSubItem);
                    }
                    subSubItem.withSubItems(subSubSubItems);
                    subSubItems.add(subSubItem);
                }
                subItem.withSubItems(subSubItems);
                subItems.add(subItem);
            }
            parent.withSubItems(subItems);
            items.add(parent);
        }
        fastItemAdapter.add(items);

        //restore selections (this has to be done after the items were added
        fastItemAdapter.withSavedInstanceState(savedInstanceState);

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