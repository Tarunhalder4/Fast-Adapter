package com.example.lufickfastadapter.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lufickfastadapter.R;
import com.example.lufickfastadapter.adapter.FastScrollIndicatorAdapter;
import com.example.lufickfastadapter.databinding.ActivitySimpleListBinding;
import com.example.lufickfastadapter.items.SimpleItem;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItemAdapter;
import com.mikepenz.fastadapter.ISelectionListener;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.listeners.ItemFilterListener;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.mikepenz.fastadapter.listeners.OnLongClickListener;
import com.mikepenz.fastadapter_extensions.ActionModeHelper;
import com.mikepenz.fastadapter_extensions.UndoHelper;
import com.mikepenz.fastadapter_extensions.drag.ItemTouchCallback;
import com.mikepenz.fastadapter_extensions.drag.SimpleDragCallback;
import com.mikepenz.fastadapter_extensions.utilities.DragDropUtil;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.materialize.MaterializeBuilder;
import com.mikepenz.materialize.util.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleListActivity extends AppCompatActivity implements ItemFilterListener {
    private static final String[] ALPHABET = new String[]{"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private ActivitySimpleListBinding binding;
    private FastAdapter<SimpleItem> fastAdapter;
    private ItemAdapter<SimpleItem> itemAdapter;

    private SimpleDragCallback touchCallback;
    private ItemTouchHelper touchHelper;
    private ActionModeHelper actionModeHelper;
    private UndoHelper mUndoHelper;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySimpleListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        new MaterializeBuilder().withActivity(this).build();

        itemAdapter = new ItemAdapter();
        FastScrollIndicatorAdapter fastScrollIndicatorAdapter = new FastScrollIndicatorAdapter();

        List<SimpleItem> items = setDataInSimpleItemList();
        itemAdapter.add(items);
        fastAdapter = FastAdapter.with(itemAdapter);
        fastAdapter.withSelectable(true);
        fastAdapter.setHasStableIds(true);
        fastAdapter.withMultiSelect(true);
        fastAdapter.withSelectOnLongClick(true);

        binding.rec.setLayoutManager(new LinearLayoutManager(this));
        binding.rec.setItemAnimator(new DefaultItemAnimator());
        binding.rec.setAdapter(fastScrollIndicatorAdapter.wrap(fastAdapter));

        fastAdapter.withSelectionListener(new ISelectionListener<SimpleItem>() {
            @Override
            public void onSelectionChanged(@Nullable SimpleItem item, boolean selected) {
                Toast.makeText(SimpleListActivity.this,item.name+"\t"+selected,Toast.LENGTH_SHORT).show();
                if(selected){
                    findViewById(R.id.each_row).setBackgroundColor(getResources().getColor(R.color.red));
                }else {
                    findViewById(R.id.each_row).setBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        });

        fastAdapter.withOnPreLongClickListener(new OnLongClickListener<SimpleItem>() {
            @Override
            public boolean onLongClick(View v, IAdapter<SimpleItem> adapter, SimpleItem item, int position) {

                Toast.makeText(SimpleListActivity.this,"long press",Toast.LENGTH_SHORT).show();

                return true;
            }

        });


        itemAdapter.getItemFilter().withFilterPredicate(new IItemAdapter.Predicate<SimpleItem>() {
            @Override
            public boolean filter(SimpleItem item, CharSequence constraint) {
                return item.name.toLowerCase().contains(constraint.toString().toLowerCase());
            }
        });

        touchCallback = new SimpleDragCallback(new ItemTouchCallback() {
            @Override
            public boolean itemTouchOnMove(int oldPosition, int newPosition) {
                DragDropUtil.onMove(itemAdapter, oldPosition, newPosition);
                return true;
            }

            @Override
            public void itemTouchDropped(int oldPosition, int newPosition) {

            }
        });

        touchHelper = new ItemTouchHelper(touchCallback);
        touchHelper.attachToRecyclerView(binding.rec);

        fastAdapter.withOnClickListener(new OnClickListener<SimpleItem>() {
            @Override
            public boolean onClick(@Nullable View v, IAdapter<SimpleItem> adapter, SimpleItem item, int position) {
                Toast.makeText(SimpleListActivity.this, item.age, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }


    List<SimpleItem> setDataInSimpleItemList(){
        int x = 0;
        List<SimpleItem> items = new ArrayList<>();
        for (String s : ALPHABET) {
            int count = new Random().nextInt(20);
            for (int i = 1; i <= count; i++) {
                SimpleItem item = new SimpleItem("Text"+s,String.valueOf(i));
                items.add(item);
                x++;
            }
        }
        return items;
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){

        switch (item.getItemId()){
            case R.id.search:
                Toast.makeText(this,"search",Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.menu.search){
            Toast.makeText(this,"search",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search,menu);

        menu.findItem(R.id.search).setIcon(new IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_search).color(Color.BLACK).actionBar());
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                touchCallback.setIsDragEnabled(false);
                itemAdapter.filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                itemAdapter.filter(s);
                touchCallback.setIsDragEnabled(TextUtils.isEmpty(s));
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void itemsFiltered(@Nullable CharSequence constraint, @Nullable List results) {

    }

    @Override
    public void onReset() {

    }


    static class ActionBarCallBack implements android.view.ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(android.view.ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onPrepareActionMode(android.view.ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.view.ActionMode actionMode, MenuItem menuItem) {
            return true;
        }

        @Override
        public void onDestroyActionMode(android.view.ActionMode actionMode) {

        }
    }
}