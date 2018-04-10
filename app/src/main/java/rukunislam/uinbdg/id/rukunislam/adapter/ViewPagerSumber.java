package rukunislam.uinbdg.id.rukunislam.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import rukunislam.uinbdg.id.rukunislam.fragments.HaditsFragment;
import rukunislam.uinbdg.id.rukunislam.fragments.QuranFragment;

public class ViewPagerSumber extends FragmentPagerAdapter {
    int tabCount;
    private final SparseArray<WeakReference<Fragment>> instantiatedFragments = new SparseArray<>();
    private ArrayList<String> mTabHeader;

    public ViewPagerSumber(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    public ViewPagerSumber(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                QuranFragment quranFragment= new QuranFragment();
                return quranFragment ;
            case 1:
                HaditsFragment haditsFragment= new HaditsFragment();
                return haditsFragment;

//            case 2:
//                UnduhFragment unduhFragment= new UnduhFragment();
//                return unduhFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final Fragment fragment = (Fragment) super.instantiateItem(container, position);
        instantiatedFragments.put(position, new WeakReference<>(fragment));
        return fragment;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position,  Object objec) {
        instantiatedFragments.remove(position);
        super.destroyItem(container, position, objec);
    }

    @Nullable
    public Fragment getFragment(final int position) {
        final WeakReference<Fragment> wr = instantiatedFragments.get(position);
        if (wr != null) {
            return wr.get();
        } else {
            return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabHeader.get(position);
    }
}

