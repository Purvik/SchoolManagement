package com.ourwork.schoolmanagement.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ourwork.schoolmanagement.fragments.GalleryPageFragment;

import java.util.List;

/**
 * Created by Purvik Rana on 12-06-2018.
 */

public class GalleryViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Integer> images;

    public GalleryViewPagerAdapter(FragmentManager fm, List<Integer> imagesList) {
        super(fm);
        this.images = imagesList;
    }

    @Override
    public Fragment getItem(int position) {
        return GalleryPageFragment.getInstance(images.get(position));
    }

    @Override
    public int getCount() {
        return images.size();
    }
}
