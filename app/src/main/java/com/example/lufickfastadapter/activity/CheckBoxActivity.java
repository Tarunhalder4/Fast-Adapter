package com.example.lufickfastadapter.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lufickfastadapter.R;
import com.example.lufickfastadapter.databinding.ActivityCheckBoxBinding;
import com.example.lufickfastadapter.items.CheckItem;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.listeners.OnClickListener;
import com.mikepenz.materialize.MaterializeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CheckBoxActivity extends AppCompatActivity {
    private static final String[] ALPHABET = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    //save our FastAdapter
    private FastItemAdapter<CheckItem> fastItemAdapter;
    private ActivityCheckBoxBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckBoxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        findViewById(android.R.id.content).setSystemUiVisibility(findViewById(android.R.id.content).getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setSupportActionBar(binding.toolBar);


        //style our ui
        new MaterializeBuilder().withActivity(this).build();

        //create our FastAdapter which will manage everything
        fastItemAdapter = new FastItemAdapter<>();
        fastItemAdapter.withSelectable(true);

        //configure our fastAdapter
        fastItemAdapter.withOnClickListener(new OnClickListener<CheckItem>() {
            @Override
            public boolean onClick(View v, IAdapter<CheckItem> adapter, @NonNull CheckItem item, int position) {
                Toast.makeText(v.getContext(), (item).name.getText(v.getContext()), Toast.LENGTH_LONG).show();
                return false;
            }
        });

        fastItemAdapter.withOnPreClickListener(new OnClickListener<CheckItem>() {
            @Override
            public boolean onClick(View v, IAdapter<CheckItem> adapter, @NonNull CheckItem item, int position) {
                // consume otherwise radio/checkbox will be deselected
                return true;
            }
        });
        fastItemAdapter.withEventHook(new CheckItem.CheckBoxClickEvent());

        binding.rec.setLayoutManager(new LinearLayoutManager(this));
        binding.rec.setItemAnimator(new DefaultItemAnimator());
        binding.rec.setAdapter(fastItemAdapter);

        //fill with some sample data
        int x = 0;
        List<CheckItem> items = new ArrayList<>();
        for (String s : ALPHABET) {
            int count = new Random().nextInt(20);
            for (int i = 1; i <= count; i++) {
                items.add(new CheckItem().withName(s + " Test " + x).withIdentifier(100 + x));
                x++;
            }
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
                Toast.makeText(getApplicationContext(), "selections = " + fastItemAdapter.getSelections(), Toast.LENGTH_LONG).show();
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}