package com.kisetsu.story.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kisetsu.story.R;
import com.kisetsu.story.adapters.ListAdapterFavorite;
import com.kisetsu.story.adapters.ListAdapterHome;
import com.kisetsu.story.itembeans.ItemBeanFavorite;
import com.kisetsu.story.itembeans.ItemBeanHome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kisetsu on 18-9-20.
 */

public class FragmentFavorite extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View convertView=inflater.inflate(R.layout.fragment_favorite,container,false);
        ListView listFavorite=convertView.findViewById(R.id.list_favorite);
        List<ItemBeanFavorite> list=new ArrayList<>();
        ListAdapterFavorite listAdapterFavorite=new ListAdapterFavorite(getContext(),list);
        listFavorite.setAdapter(listAdapterFavorite);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
