package com.example.lufickfastadapter.endLess;

import static com.mikepenz.fastadapter.adapters.ItemAdapter.items;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lufickfastadapter.R;
import com.example.lufickfastadapter.databinding.ActivityEndLessBinding;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItemAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.listeners.ItemFilterListener;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.mikepenz.fastadapter_extensions.drag.ItemTouchCallback;
import com.mikepenz.fastadapter_extensions.drag.SimpleDragCallback;
import com.mikepenz.fastadapter_extensions.items.ProgressItem;
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener;
import com.mikepenz.fastadapter_extensions.utilities.DragDropUtil;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.materialize.MaterializeBuilder;

import java.util.ArrayList;
import java.util.List;

public class EndLessActivity extends AppCompatActivity implements ItemTouchCallback, ItemFilterListener<SimpleItem> {

//save our FastAdapter
private FastItemAdapter<SimpleItem> fastItemAdapter;
private ItemAdapter footerAdapter;
private ActivityEndLessBinding binding;
//drag & drop
private SimpleDragCallback touchCallback;
private ItemTouchHelper touchHelper;

        //endless scroll
private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityEndLessBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    findViewById(android.R.id.content).setSystemUiVisibility(findViewById(android.R.id.content).getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    // Handle Toolbar
        setSupportActionBar(binding.toolBar);


        //style our ui
        new MaterializeBuilder().withActivity(this).build();

        //create our FastAdapter which will manage everything
        fastItemAdapter = new FastItemAdapter<>();
        fastItemAdapter.withSelectable(true);

        //create our FooterAdapter which will manage the progress items
        footerAdapter = items();
        fastItemAdapter.addAdapter(1, footerAdapter);

        //configure our fastAdapter
        fastItemAdapter.withOnClickListener(new OnClickListener<SimpleItem>() {
@Override
public boolean onClick(View v, IAdapter<SimpleItem> adapter, @NonNull SimpleItem item, int position) {
        Toast.makeText(v.getContext(), (item).name.getText(v.getContext()), Toast.LENGTH_LONG).show();
        return false;
        }
        });

        //configure the itemAdapter
        fastItemAdapter.getItemFilter().withFilterPredicate(new IItemAdapter.Predicate<SimpleItem>() {
@Override
public boolean filter(SimpleItem item, CharSequence constraint) {
        //return true if we should filter it out
        //return false to keep it
        return item.name.getText().toString().toLowerCase().contains(constraint.toString().toLowerCase());
        }
        });

        fastItemAdapter.getItemFilter().withItemFilterListener(this);

        //get our recyclerView and do basic setup
        binding.rec.setLayoutManager(new LinearLayoutManager(this));
        binding.rec.setItemAnimator(new DefaultItemAnimator());
        binding.rec.setAdapter(fastItemAdapter);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(footerAdapter) {
@Override
public void onLoadMore(final int currentPage) {
        footerAdapter.clear();
        footerAdapter.add(new ProgressItem().withEnabled(false));
        //simulate networking (2 seconds)
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
@Override
public void run() {
        footerAdapter.clear();
        for (int i = 1; i < 16; i++) {
        fastItemAdapter.add(fastItemAdapter.getAdapterItemCount(), new SimpleItem().withName("Item " + i + " Page " + currentPage));
        }
        }
        }, 2000);
        }
        };
        binding.rec.addOnScrollListener(endlessRecyclerOnScrollListener);

        //fill with some sample data (load the first page here)
        List<SimpleItem> items = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
        items.add(new SimpleItem().withName("Item " + i + " Page 0"));
        }
        fastItemAdapter.add(items);

        //add drag and drop for item
        touchCallback = new SimpleDragCallback(this);
        touchHelper = new ItemTouchHelper(touchCallback); // Create ItemTouchHelper and pass with parameter the SimpleDragCallback
        touchHelper.attachToRecyclerView(binding.rec); // Attach ItemTouchHelper to RecyclerView

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


@Override
public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        //search icon
        menu.findItem(R.id.search).setIcon(new IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_search).color(Color.BLACK).actionBar());

final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
@Override
public boolean onQueryTextSubmit(String s) {
        touchCallback.setIsDragEnabled(false);
        fastItemAdapter.filter(s);
        return true;
        }


@Override
public boolean onQueryTextChange(String s) {
        fastItemAdapter.filter(s);
        touchCallback.setIsDragEnabled(TextUtils.isEmpty(s));
        return true;
        }
        });
        endlessRecyclerOnScrollListener.enable();

        return super.onCreateOptionsMenu(menu);
        }

@Override
public boolean itemTouchOnMove(int oldPosition, int newPosition) {
        DragDropUtil.onMove(fastItemAdapter.getItemAdapter(), oldPosition, newPosition); // change position
        return true;
        }

@Override
public void itemTouchDropped(int oldPosition, int newPosition) {
        // save the new item order, i.e. in your database
        }

@Override
public void itemsFiltered(@Nullable CharSequence constraint, @Nullable List<SimpleItem> results) {
        endlessRecyclerOnScrollListener.disable();
        Toast.makeText(EndLessActivity.this, "filtered items count: " + fastItemAdapter.getItemCount(), Toast.LENGTH_SHORT).show();
        }

@Override
public void onReset() {
        endlessRecyclerOnScrollListener.enable();
        }
}
