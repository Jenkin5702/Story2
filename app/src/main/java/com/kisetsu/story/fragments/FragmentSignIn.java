package com.kisetsu.story.fragments;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kisetsu.story.R;

/**
 * Created by kisetsu on 18-9-21.
 */

public class FragmentSignIn extends Fragment {
    String statement;
    SQLiteDatabase db;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        db=SQLiteDatabase.openOrCreateDatabase(getContext().getFilesDir().getPath()+"loginInfo.db",null);
        View convertView=inflater.inflate(R.layout.fragment_signin,container,false);
        final EditText etUserName=convertView.findViewById(R.id.signin_username);
        final EditText etPassword=convertView.findViewById(R.id.et_password);
        Button btnSignIn=convertView.findViewById(R.id.btn_signin);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String columns[]={"username"};
                Cursor c=db.query("loginInfo",columns,etUserName.toString(),null,null,null,null);
                if(c.getLong(1)==Long.parseLong(etPassword.getText().toString())){
                    Toast.makeText(getContext(),"登录成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"密码错误",Toast.LENGTH_SHORT).show();
                }
                c.close();
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
