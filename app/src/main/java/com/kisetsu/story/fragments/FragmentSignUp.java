package com.kisetsu.story.fragments;

import android.content.Context;
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

public class FragmentSignUp extends Fragment {
    String statement;
    SQLiteDatabase db;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        db=SQLiteDatabase.openOrCreateDatabase(getContext().getFilesDir().getPath()+"loginInfo.db",null);
        statement="CREATE TABLE IF NOT EXISTS loginInfo (username VARCHAR(24),password LONG)";
        db.execSQL(statement);

        View convertView=inflater.inflate(R.layout.fragment_signup,container,false);
        final EditText etUserName=convertView.findViewById(R.id.signup_username);
        final EditText etPassword=convertView.findViewById(R.id.signup_password);
        Button btnSignUp=convertView.findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statement="insert into stu_table(username,password) values('"+
                        etUserName.getText().toString()+
                        "','"+String.valueOf(etPassword.getText().toString())+"')";
                db.execSQL(statement);
                Toast.makeText(getContext(),"注册成功",Toast.LENGTH_SHORT).show();
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
