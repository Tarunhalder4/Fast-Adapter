package com.example.lufickfastadapter.drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.lufickfastadapter.R;
import com.example.lufickfastadapter.activity.CheckBoxActivity;
import com.example.lufickfastadapter.activity.ExpandableSampleActivity;
import com.example.lufickfastadapter.activity.RadioButtonActivity;
import com.example.lufickfastadapter.activity.SimpleImageListActivity;
import com.example.lufickfastadapter.activity.SimpleListActivity;
import com.example.lufickfastadapter.dammyData.ImageDammyData;
import com.example.lufickfastadapter.databinding.ActivityDrawerBinding;
import com.example.lufickfastadapter.endLess.EndLessActivity;
import com.example.lufickfastadapter.grid.GridActivity;
import com.example.lufickfastadapter.iconGrid.ExpandableGridActivity;
import com.example.lufickfastadapter.iconGrid.IconGridActivity;
import com.example.lufickfastadapter.multiSelectItem.MultiSelectActivity;
import com.example.lufickfastadapter.sortSample.SortActivity;
import com.example.lufickfastadapter.stickyHeaderSample.StickyHeaderSampleActivity;
import com.example.lufickfastadapter.swipeSample.SwipeActivity;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.mikepenz.fastadapter.select.SelectExtension;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.itemanimators.SlideDownAlphaAnimator;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.Arrays;
import java.util.List;

public class DrawerActivity extends AppCompatActivity {

    ActivityDrawerBinding binding;

    //save our header or result
    private Drawer mResult = null;
    //save our FastAdapter
    private FastAdapter<SimpleImageItem> mFastAdapter;
    //save our FastAdapter
    private ItemAdapter<SimpleImageItem> mItemAdapter;
    //our `SelectExtension`
    private SelectExtension<SimpleImageItem> selectExtension;
    ImageDammyData imageDammyData ;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        findViewById(android.R.id.content).setSystemUiVisibility(findViewById(android.R.id.content).getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        super.onCreate(savedInstanceState);
        binding = ActivityDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle("");
        imageDammyData = new ImageDammyData(DrawerActivity.this);

        //Create the drawer
        mResult = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(binding.toolBar)
                .withHasStableIds(true)
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.title_simple_list_activity).withDescription(R.string.des_simple_list_activity).withSelectable(false).withIdentifier(1).withIcon(MaterialDesignIconic.Icon.gmi_format_align_justify),
                        new PrimaryDrawerItem().withName(R.string.title_simple_image_activity).withDescription(R.string.des_simple_image_activity).withSelectable(false).withIdentifier(2).withIcon(MaterialDesignIconic.Icon.gmi_wallpaper),
                        new PrimaryDrawerItem().withName(R.string.title_simple_expandable_list_activity).withDescription(R.string.title_simple_expandable_list_activity).withSelectable(false).withIdentifier(3).withIcon(MaterialDesignIconic.Icon.gmi_check_all),
                        new PrimaryDrawerItem().withName(R.string.title_end_less_list_activity).withDescription(R.string.title_end_less_list_activity).withSelectable(false).withIdentifier(4).withIcon(MaterialDesignIconic.Icon.gmi_long_arrow_down),
                        new PrimaryDrawerItem().withName(R.string.title_check_box_button_list_Activity).withDescription(R.string.title_check_box_button_list_Activity).withSelectable(false).withIdentifier(5).withIcon(MaterialDesignIconic.Icon.gmi_check),
                        new PrimaryDrawerItem().withName(R.string.title_radio_button_list_Activity).withDescription(R.string.title_radio_button_list_Activity).withSelectable(false).withIdentifier(6).withIcon(MaterialDesignIconic.Icon.gmi_radio),
                        new PrimaryDrawerItem().withName(R.string.title_sort_list_activity).withDescription(R.string.title_sort_list_activity).withSelectable(false).withIdentifier(7).withIcon(MaterialDesignIconic.Icon.gmi_sort_by_alpha),
                        new PrimaryDrawerItem().withName(R.string.title_swipe_list_activity).withDescription(R.string.title_swipe_list_activity).withSelectable(false).withIdentifier(8).withIcon(MaterialDesignIconic.Icon.gmi_swap),
                        new PrimaryDrawerItem().withName(R.string.title_icon_grid_list_activity).withDescription(R.string.des_icon_grid_list_activity).withSelectable(false).withIdentifier(9).withIcon(MaterialDesignIconic.Icon.gmi_grid),
                        new PrimaryDrawerItem().withName(R.string.title_multiple_select_list_activity).withDescription(R.string.title_multiple_select_list_activity).withSelectable(false).withIdentifier(10).withIcon(MaterialDesignIconic.Icon.gmi_select_all),
                        new PrimaryDrawerItem().withName(R.string.title_sticky_header_expendable_list_activity).withDescription(R.string.title_sticky_header_expendable_list_activity).withSelectable(false).withIdentifier(11).withIcon(MaterialDesignIconic.Icon.gmi_format_strikethrough),
                        new PrimaryDrawerItem().withName(R.string.title_Sticky_Header_list_Activity).withDescription(R.string.title_Sticky_Header_list_Activity).withSelectable(false).withIdentifier(12).withIcon(MaterialDesignIconic.Icon.gmi_check),
                        new PrimaryDrawerItem().withName(R.string.title_expandable_grid_list_activity).withDescription(R.string.des_expandable_grid_list_activity).withSelectable(false).withIdentifier(13).withIcon(MaterialDesignIconic.Icon.gmi_long_arrow_down),
                        new DividerDrawerItem()
                        )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                intent = new Intent(DrawerActivity.this, SimpleListActivity.class);
                            } else if (drawerItem.getIdentifier() == 2) {
                                intent = new Intent(DrawerActivity.this, SimpleImageListActivity.class);
                            }else if (drawerItem.getIdentifier() == 3) {
                                intent = new Intent(DrawerActivity.this, ExpandableSampleActivity.class);
                            } else if (drawerItem.getIdentifier() == 4) {
                                intent = new Intent(DrawerActivity.this, EndLessActivity.class);
                            }else if (drawerItem.getIdentifier() == 5) {
                                intent = new Intent(DrawerActivity.this, CheckBoxActivity.class);
                            }else if (drawerItem.getIdentifier() == 6) {
                                intent = new Intent(DrawerActivity.this, RadioButtonActivity.class);
                            }else if (drawerItem.getIdentifier() == 7) {
                                intent = new Intent(DrawerActivity.this, SortActivity.class);
                            }else if (drawerItem.getIdentifier() == 8) {
                                intent = new Intent(DrawerActivity.this, SwipeActivity.class);
                            }else if (drawerItem.getIdentifier() == 9) {
                                intent = new Intent(DrawerActivity.this, GridActivity.class);
                            }else if (drawerItem.getIdentifier() == 10) {
                                intent = new Intent(DrawerActivity.this, MultiSelectActivity.class);
                            }else if (drawerItem.getIdentifier() == 11) {
                                intent = new Intent(DrawerActivity.this, StickyHeaderSampleActivity.class);
                            } else if (drawerItem.getIdentifier() == 13) {
                                intent = new Intent(DrawerActivity.this, ExpandableGridActivity.class);
                            }else if (drawerItem.getIdentifier() == 100) {
                                intent = new LibsBuilder()
                                        .withFields(R.string.class.getFields())
                                        .withActivityTitle(getString(R.string.open_source))
                                        .withActivityStyle(Libs.ActivityStyle.LIGHT)
                                        .withAboutIconShown(true)
                                        .withVersionShown(true)
                                        .withAboutVersionShown(true)
                                        .intent(DrawerActivity.this);
                            }
                            if (intent != null) {
                                DrawerActivity.this.startActivity(intent);
                            }
                        }
                        return false;
                    }
                })
                .withSelectedItemByPosition(-1)
                .build();

        //create our ItemAdapter which will host our items
        mItemAdapter = new ItemAdapter<>();

        //create our FastAdapter which will manage everything
        mFastAdapter = FastAdapter.with(Arrays.asList(mItemAdapter));
        mFastAdapter.withSelectable(true);
        mFastAdapter.withMultiSelect(true);
        mFastAdapter.withSelectOnLongClick(false);
        selectExtension = mFastAdapter.getExtension(SelectExtension.class);

        //configure our fastAdapter
        //get our recyclerView and do basic setup

        //mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.rec.setLayoutManager(new LinearLayoutManager(this));
        binding.rec.setAdapter(mFastAdapter);
        binding.rec.setItemAnimator(new SlideDownAlphaAnimator());
        binding.rec.getItemAnimator().setAddDuration(500);
        binding.rec.getItemAnimator().setRemoveDuration(500);

        //if we do this. the first added items will be animated :D
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //add some dummy data

                //mItemAdapter.add((SimpleImageItem) imageDammyData.getImageItems());
                mItemAdapter.add(ImageDammyData.getSimpleImageItems());


                //restore selections (this has to be done after the items were added
                mFastAdapter.withSavedInstanceState(savedInstanceState);
            }
        }, 50);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.item_add).setIcon(new IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_plus_square).color(Color.BLACK).actionBar());
        menu.findItem(R.id.item_delete).setIcon(new IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_minus_square).color(Color.BLACK).actionBar());
        menu.findItem(R.id.item_change).setIcon(new IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_settings_square).color(Color.BLACK).actionBar());
        menu.findItem(R.id.item_move).setIcon(new IconicsDrawable(this, MaterialDesignIconic.Icon.gmi_format_valign_bottom).color(Color.BLACK).actionBar());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //find out the current visible position
        int firstVisiblePosition = 0;
        if (binding.rec.getLayoutManager() instanceof LinearLayoutManager) {
            firstVisiblePosition = ((LinearLayoutManager) binding.rec.getLayoutManager()).findFirstVisibleItemPosition();
        } else if (binding.rec.getLayoutManager() instanceof GridLayoutManager) {
            firstVisiblePosition = ((GridLayoutManager) binding.rec.getLayoutManager()).findFirstVisibleItemPosition();
        }

        //handle the menu item click
        switch (item.getItemId()) {
            case R.id.item_add:
                mItemAdapter.add(firstVisiblePosition + 1, ImageDammyData.getDummyItem());
                return true;
            case R.id.item_change:
                for (Integer pos : selectExtension.getSelections()) {
                    SimpleImageItem i = mItemAdapter.getAdapterItem(pos);
                    i.withName("CHANGED");
                    i.withDescription("This item was modified");
                    mItemAdapter.set(pos, i);
                }
                return true;
            case R.id.item_move:
                List items = mItemAdapter.getAdapterItems();
                if (items.size() > firstVisiblePosition + 3) {
                    mItemAdapter.move(firstVisiblePosition + 1, firstVisiblePosition + 3);
                }
                return true;
            case R.id.item_delete:
                selectExtension.deleteAllSelectedItems();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = mResult.saveInstanceState(outState);
        //add the values which need to be saved from the adapter to the bundle
        outState = mFastAdapter.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (mResult != null && mResult.isDrawerOpen()) {
            mResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}