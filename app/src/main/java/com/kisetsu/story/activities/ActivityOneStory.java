package com.kisetsu.story.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.kisetsu.story.R;
import com.kisetsu.story.adapters.ListAdapterHome;
import com.kisetsu.story.itembeans.ItemBeanHome;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kisetsu on 18-9-20.
 */

public class ActivityOneStory extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    public ListView listOneStory;
    public List<ItemBeanHome> list;
    public ListAdapterHome listAdapterHome;
    public EditText editParagraph;
    public Button sendParagraph;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_story);

        listOneStory=findViewById(R.id.list_one_story);
        editParagraph=findViewById(R.id.edit_home);
        sendParagraph=findViewById(R.id.btn_home_publish);

        list=new ArrayList<>();
        list.add(new ItemBeanHome("123","234","3,45"));
        list.add(new ItemBeanHome("123","234","3,45"));
        list.add(new ItemBeanHome("123","234","3,45"));
        list.add(new ItemBeanHome("123","234","3,45"));
        list.add(new ItemBeanHome("123","234","3,45"));
        list.add(new ItemBeanHome("123","234","3,45"));
        listAdapterHome=new ListAdapterHome(ActivityOneStory.this,list);
        sendParagraph.setOnClickListener(this);
        listOneStory.setAdapter(listAdapterHome);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_home_publish:
                String editedText=editParagraph.getText().toString();
                ItemBeanHome newItem=new ItemBeanHome("url",editedText,editedText);
                listAdapterHome.addItem(newItem);
                break;
        }
    }
}
