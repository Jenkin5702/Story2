package com.kisetsu.story.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kisetsu.story.R;
import com.kisetsu.story.adapters.ListAdapterCommunity;
import com.kisetsu.story.adapters.ListAdapterHome;
import com.kisetsu.story.itembeans.ItemBeanCommunity;
import com.kisetsu.story.itembeans.ItemBeanHome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kisetsu on 18-9-20.
 */

public class FragmentCommunity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View convertView=inflater.inflate(R.layout.fragment_community,container,false);
        ListView listCommunity=convertView.findViewById(R.id.list_community);
        List<ItemBeanCommunity> list=new ArrayList<>();
        ListAdapterCommunity listAdapterCommunity=new ListAdapterCommunity(getContext(),list);
        listCommunity.setAdapter(listAdapterCommunity);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
