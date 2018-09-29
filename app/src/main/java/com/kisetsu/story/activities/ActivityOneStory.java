package com.kisetsu.story.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.kisetsu.story.utilities.NetworkCommunication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kisetsu on 18-9-20.
 * Activity for a detail story.
 */

public class ActivityOneStory extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    public ListView listOneStory;
    public EditText editParagraph;
    public Button sendParagraph;

    public List<ItemBeanHome> list=new ArrayList<>();
    public ListAdapterHome listAdapterHome;
    private String storyTitle;

    private String result;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(result!=null){
                AlertDialog dialog=new AlertDialog.Builder(ActivityOneStory.this).create();
                dialog.setMessage(result);
                dialog.show();
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_story);

        listOneStory=findViewById(R.id.list_one_story);
        editParagraph=findViewById(R.id.edit_home);
        sendParagraph=findViewById(R.id.btn_home_publish);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        if(bundle!=null){
            storyTitle=bundle.getString("title");
            getData(storyTitle);
        }

        addItem();

        listAdapterHome=new ListAdapterHome(ActivityOneStory.this,list);
        sendParagraph.setOnClickListener(this);
        listOneStory.setAdapter(listAdapterHome);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog dialog=new AlertDialog.Builder(ActivityOneStory.this).create();
        dialog.setMessage(list.get(position).content);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_home_publish:
                String editedText=editParagraph.getText().toString();
                ItemBeanHome newItem=new ItemBeanHome(editedText,editedText);
                listAdapterHome.addItem(newItem);
                listOneStory.smoothScrollToPosition(listAdapterHome.getCount());
                editParagraph.setText("");

                addData(editedText);

//                try{
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            result=NetworkCommunication.send("http://127.0.0.1:8080");
//                            Message message=handler.obtainMessage();
//                            handler.sendMessage(message);
//                        }
//                    }).start();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
                break;
        }
    }

    private void addItem(){
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
        list.add(new ItemBeanHome("234","3,45"));
    }

    private void getData(String title){
        // TODO Get data from server or file.
    }
    private void addData(String content){

        // TODO Add data to server or file;
    }
}
