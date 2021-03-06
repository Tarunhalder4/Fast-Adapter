package com.example.lufickfastadapter.multiSelectItem;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lufickfastadapter.R;
import com.example.lufickfastadapter.databinding.ActivityMultiSelectBinding;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.ISelectionListener;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.mikepenz.fastadapter.listeners.OnLongClickListener;
import com.mikepenz.fastadapter_extensions.ActionModeHelper;
import com.mikepenz.fastadapter_extensions.UndoHelper;
import com.mikepenz.itemanimators.SlideDownAlphaAnimator;
import com.mikepenz.materialize.MaterializeBuilder;
import com.mikepenz.materialize.util.UIUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class MultiSelectActivity extends AppCompatActivity {

    ActivityMultiSelectBinding binding;

    private FastAdapter<SimpleItem> mFastAdapter;

    private UndoHelper mUndoHelper;

    private ActionModeHelper<SimpleItem> mActionModeHelper;

    ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(android.R.id.content).setSystemUiVisibility(findViewById(android.R.id.content).getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        binding = ActivityMultiSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Handle Toolbar
        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle("Multi Select Item");

        //style our ui
        new MaterializeBuilder().withActivity(this).build();

        //create our adapters
        final ItemAdapter<SimpleItem> headerAdapter = new ItemAdapter<>();
        ItemAdapter<SimpleItem> itemAdapter = new ItemAdapter<>();

        //create our FastAdapter
        mFastAdapter = FastAdapter.with(Arrays.asList(headerAdapter, itemAdapter));

        //configure our mFastAdapter
        //as we provide id's for the items we want the hasStableIds enabled to speed up things
        mFastAdapter.setHasStableIds(true);
        mFastAdapter.withSelectable(true);
        mFastAdapter.withMultiSelect(true);
        mFastAdapter.withSelectOnLongClick(true);
        mFastAdapter.withSelectionListener(new ISelectionListener<SimpleItem>() {
            @Override
            public void onSelectionChanged(SimpleItem item, boolean selected) {
                Log.i("FastAdapter", "SelectedCount: " + mFastAdapter.getSelections().size() + " ItemsCount: " + mFastAdapter.getSelectedItems().size());
            }
        });
        mFastAdapter.withOnPreClickListener(new OnClickListener<SimpleItem>() {
            @Override
            public boolean onClick(View v, IAdapter<SimpleItem> adapter, @NonNull SimpleItem item, int position) {
                //we handle the default onClick behavior for the actionMode. This will return null if it didn't do anything and you can handle a normal onClick
                Boolean res = mActionModeHelper.onClick(item);
                return res != null ? res : false;
            }
        });
        mFastAdapter.withOnClickListener(new OnClickListener<SimpleItem>() {
            @Override
            public boolean onClick(View v, IAdapter<SimpleItem> adapter, @NonNull SimpleItem item, int position) {
                Toast.makeText(v.getContext(), "SelectedCount: " + mFastAdapter.getSelections().size() + " ItemsCount: " + mFastAdapter.getSelectedItems().size(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });
        mFastAdapter.withOnPreLongClickListener(new OnLongClickListener<SimpleItem>() {
            @Override
            public boolean onLongClick(View v, IAdapter<SimpleItem> adapter, SimpleItem item, int position) {
                ActionMode actionMode = mActionModeHelper.onLongClick(MultiSelectActivity.this, position);
                getSupportActionBar().hide();
                if (actionMode != null) {
                    //we want color our CAB
//                    v.findViewById(R.id.sort_each_row_layout).setBackgroundColor(getResources().getColor(R.color.red));
                    v.findViewById(R.id.multi_select_each_row_layout).setBackgroundColor(UIUtils.getThemeColorFromAttrOrRes(MultiSelectActivity.this, androidx.appcompat.R.attr.color,
                            R.color.red));
                }

                //if we have no actionMode we do not consume the event
                return actionMode != null;
            }
        });

        //
        mUndoHelper = new UndoHelper<>(mFastAdapter, new UndoHelper.UndoListener<SimpleItem>() {
            @Override
            public void commitRemove(Set<Integer> positions, ArrayList<FastAdapter.RelativeInfo<SimpleItem>> removed) {
                Log.e("UndoHelper", "Positions: " + positions.toString() + " Removed: " + removed.size());
            }
        });

        //we init our ActionModeHelper
        mActionModeHelper = new ActionModeHelper<>(mFastAdapter, R.menu.cab, new ActionBarCallBack());

        //get our recyclerView and do basic setup
        binding.rec.setLayoutManager(new LinearLayoutManager(this));
        binding.rec.setItemAnimator(new SlideDownAlphaAnimator());
        binding.rec.setAdapter(mFastAdapter);

        //fill with some sample data
        SimpleItem SimpleItem = new SimpleItem();
        SimpleItem.withName("Header")
                .withIdentifier(1)
                .withSelectable(false);
        headerAdapter.add(SimpleItem);

        List<SimpleItem> items = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            SimpleItem item = new SimpleItem();
            item.withName("Test " + i).withIdentifier(100 + i).withDescription(String.valueOf(i));
            items.add(item);
        }
        itemAdapter.add(items);

        //restore selections (this has to be done after the items were added
        mFastAdapter.withSavedInstanceState(savedInstanceState);

        //set the back arrow in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        //inform that longClick is required
        Toast.makeText(this, "LongClick to enable Multi-Selection", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the adapter to the bundle
        outState = mFastAdapter.saveInstanceState(outState);
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

    /**
     * Our ActionBarCallBack to showcase the CAB
     */
    class ActionBarCallBack implements ActionMode.Callback {

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            mUndoHelper.remove(findViewById(android.R.id.content), "Item removed", "Undo", 1, mFastAdapter.getSelections());
            //as we no longer have a selection so the actionMode can be finished
            mode.finish();
            //we consume the event
            return true;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
    }
}