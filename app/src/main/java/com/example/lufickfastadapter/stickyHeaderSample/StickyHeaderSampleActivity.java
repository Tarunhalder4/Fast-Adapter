package com.example.lufickfastadapter.stickyHeaderSample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.lufickfastadapter.R;
import com.example.lufickfastadapter.databinding.ActivityStickyHeaderSampleBinding;
import com.example.lufickfastadapter.stickyHeaderSample.SimpleItem;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.materialize.MaterializeBuilder;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StickyHeaderSampleActivity extends AppCompatActivity {

    private static final String[] headers = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    //save our FastAdapter
    private FastAdapter fastAdapter;
    private ActivityStickyHeaderSampleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        findViewById(android.R.id.content).setSystemUiVisibility(findViewById(android.R.id.content).getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        super.onCreate(savedInstanceState);
        binding = ActivityStickyHeaderSampleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Handle Toolbar
        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle(R.string.title_Sticky_Header_list_Activity);

        //style our ui
        new MaterializeBuilder().withActivity(this).build();

        //create our adapters
        final StickyHeaderAdapter stickyHeaderAdapter = new StickyHeaderAdapter();
        final ItemAdapter headerAdapter = new ItemAdapter();
        final ItemAdapter itemAdapter = new ItemAdapter();

        //create our FastAdapter
        fastAdapter = FastAdapter.with(Arrays.asList(headerAdapter, itemAdapter));
        fastAdapter.withSelectable(true);


        //configure our fastAdapter
        //as we provide id's for the items we want the hasStableIds enabled to speed up things
        fastAdapter.setHasStableIds(true);

        //get our recyclerView and do basic setup
        binding.rec.setLayoutManager(new LinearLayoutManager(this));
        binding.rec.setItemAnimator(new DefaultItemAnimator());
        binding.rec.setAdapter(stickyHeaderAdapter.wrap(fastAdapter));

        //this adds the Sticky Headers within our list
        final StickyRecyclerHeadersDecoration decoration = new StickyRecyclerHeadersDecoration(stickyHeaderAdapter);
        binding.rec.addItemDecoration(decoration);

        //fill with some sample data
        headerAdapter.add(new SimpleItem().withName("Header").withIdentifier(1));
        List<IItem> items = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            items.add(new SimpleItem().withName("Test " + i).withHeader(headers[i / 5]).withIdentifier(100 + i));
        }
        itemAdapter.add(items);

        //so the headers are aware of changes
        stickyHeaderAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                decoration.invalidateHeaders();
            }
        });

        //restore selections (this has to be done after the items were added
        fastAdapter.withSavedInstanceState(savedInstanceState);

        //set the back arrow in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the adapter to the bundle
        outState = fastAdapter.saveInstanceState(outState);
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