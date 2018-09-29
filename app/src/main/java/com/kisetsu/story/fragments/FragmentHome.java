package com.kisetsu.story.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kisetsu.story.R;
import com.kisetsu.story.activities.ActivityOneStory;
import com.kisetsu.story.adapters.ListAdapterHome;
import com.kisetsu.story.itembeans.ItemBeanHome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kisetsu on 18-9-20.
 * HomePage with a listView.
 */

public class FragmentHome extends Fragment implements ListView.OnItemClickListener{
    public FloatingActionButton fab;
    public ListView listHome;
    public List<ItemBeanHome> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View convertView=inflater.inflate(R.layout.fragment_home,container,false);
        listHome=convertView.findViewById(R.id.list_home);
        fab=convertView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listHome.smoothScrollToPosition(0);
            }
        });
        list=new ArrayList<>();
        getData();
        addItem();
        ListAdapterHome listAdapterHome=new ListAdapterHome(getContext(),list);
        listHome.setAdapter(listAdapterHome);
        listHome.setOnItemClickListener(this);
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getContext(), ActivityOneStory.class);
        Bundle bundle=new Bundle();
        bundle.putCharSequence("title",list.get(position).title);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void addItem(){
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
    }
    private void getData(){
        //TODO Get data from server.
    }
}
