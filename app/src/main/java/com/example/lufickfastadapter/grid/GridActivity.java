package com.example.lufickfastadapter.grid;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.lufickfastadapter.adapter.FastScrollIndicatorAdapter;
import com.example.lufickfastadapter.databinding.ActivityGridBinding;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IInterceptor;
import com.mikepenz.fastadapter.adapters.ModelAdapter;
import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.typeface.ITypeface;
import com.mikepenz.itemanimators.SlideDownAlphaAnimator;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.materialize.MaterializeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class GridActivity extends AppCompatActivity {

    private FastAdapter fastAdapter;
    ActivityGridBinding binding;
    String TAG ="tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        findViewById(android.R.id.content).setSystemUiVisibility(findViewById(android.R.id.content).getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        super.onCreate(savedInstanceState);
        binding = ActivityGridBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Handle Toolbar
        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle("Grid List");

        //style our ui
        new MaterializeBuilder().withActivity(this).build();

        //adapters
        FastScrollIndicatorAdapter fastScrollIndicatorAdapter = new FastScrollIndicatorAdapter();
        ModelAdapter<IconModel, ModelIconItem> itemAdapter = new ModelAdapter<>(new IInterceptor<IconModel, ModelIconItem>() {
            @Override
            public ModelIconItem intercept(IconModel iconModel) {
                return new ModelIconItem(iconModel);
            }
        });

        //create our FastAdapter which will manage everything
        fastAdapter = FastAdapter.with(Arrays.asList(itemAdapter));
        fastAdapter.withSelectable(true);

        //get our recyclerView and do basic setup
        //init our gridLayoutManager and configure RV
        binding.rec.setAdapter(fastScrollIndicatorAdapter.wrap(fastAdapter));

//        DragScrollBar materialScrollBar = new DragScrollBar(this, binding.rec, true);
//        materialScrollBar.setHandleColour(ContextCompat.getColor(this, R.color.colorAccent));
//        materialScrollBar.setHandleOffColour(ContextCompat.getColor(this, R.color.colorAccent));
//        materialScrollBar.addIndicator(new CustomIndicator(this), true);

        binding.rec.setLayoutManager(new GridLayoutManager(this, 3));
        binding.rec.setItemAnimator(new SlideDownAlphaAnimator());

        //order fonts by their name
       // List<ITypeface> mFonts = new ArrayList<>(Iconics.getRegisteredFonts(GridActivity.this));
        List<ITypeface> mFonts = new ArrayList<>(Iconics.getRegisteredFonts(this));

        Log.d(TAG, "onCreate: "+mFonts.size());

        Collections.sort(mFonts, new Comparator<ITypeface>() {
            @Override
            public int compare(final ITypeface object1, final ITypeface object2) {
                return object1.getFontName().compareTo(object2.getFontName());
            }
        });

        //add all icons of all registered Fonts to the list
        ArrayList<IconModel> models = new ArrayList<>();
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_align_justify));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_select_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_strikethrough));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_radio));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_swap));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort_by_alpha));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_wallpaper));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_align_justify));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_select_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_strikethrough));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_radio));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_swap));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort_by_alpha));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_wallpaper));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_align_justify));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_select_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_strikethrough));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_radio));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_swap));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort_by_alpha));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_wallpaper));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_align_justify));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_select_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_strikethrough));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_radio));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_swap));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort_by_alpha));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_wallpaper));models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_align_justify));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_select_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_strikethrough));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_radio));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_swap));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort_by_alpha));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_wallpaper));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_align_justify));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_select_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_strikethrough));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_radio));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_swap));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort_by_alpha));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_wallpaper));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_align_justify));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_select_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_strikethrough));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_radio));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_swap));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort_by_alpha));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_wallpaper));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_align_justify));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_select_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_strikethrough));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_radio));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_swap));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort_by_alpha));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_wallpaper));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_align_justify));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_select_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_strikethrough));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_radio));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_swap));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort_by_alpha));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_wallpaper));models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_align_justify));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_select_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_format_strikethrough));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_radio));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_swap));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_sort_by_alpha));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_check_all));
        models.add(new IconModel(MaterialDesignIconic.Icon.gmi_wallpaper));




//        for (ITypeface font : mFonts) {
//            Log.d(TAG, "onCreate: "+font.getFontName());
//            for (String icon : font.getIcons()) {
//                models.add(new IconModel(font.getIcon(icon)));
//            }
//        }

        Log.d(TAG, "onCreate: "+models.size());

        //fill with some sample data
        itemAdapter.add(models);

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