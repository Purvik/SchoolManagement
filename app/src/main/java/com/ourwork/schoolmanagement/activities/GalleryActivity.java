package com.ourwork.schoolmanagement.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ourwork.schoolmanagement.R;
import com.ourwork.schoolmanagement.adapters.GalleryAdapter;
import com.ourwork.schoolmanagement.adapters.GalleryViewPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Purvik Rana on 12-06-2018.
 */

public class GalleryActivity extends AppCompatActivity {

    private final static int[] resourceIDs = new int[]{
            R.drawable.ic_about_us_new,
            R.drawable.ic_assignments_new,
            R.drawable.ic_attendance_new,
            R.drawable.ic_behaviour,
            R.drawable.ic_chat,
            R.drawable.ic_feepayments};
    private ArrayList<Integer> images;
    private ViewPager viewPager;
    private GalleryViewPagerAdapter adapter;
    private GalleryAdapter galleryAdapter;
    private Toolbar toolbar;
    private RecyclerView rvGallary;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Gallery");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        images = new ArrayList<>();

        //find view by id
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        rvGallary = (RecyclerView) findViewById(R.id.rvGallary);
        rvGallary.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        setImagesData();

        galleryAdapter = new GalleryAdapter(images, GalleryActivity.this, new GalleryAdapter.onItemClickListner() {
            @Override
            public void OnItemClick(int pos) {
                viewPager.setCurrentItem(pos);
            }
        });
        rvGallary.setAdapter(galleryAdapter);
        // init viewpager adapter and attach
        adapter = new GalleryViewPagerAdapter(GalleryActivity.this, images);
        viewPager.setAdapter(adapter);

    }

    private void setImagesData() {
        for (int i = 0; i < resourceIDs.length; i++) {
            images.add(resourceIDs[i]);
        }
    }

    private View.OnClickListener onChagePageClickListener(final int i) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(i);
            }
        };
    }


}
