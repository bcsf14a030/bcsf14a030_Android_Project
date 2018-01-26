package com.example.farooqkhalid.a31solid;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.*;

public class AddNewUser extends AppCompatActivity implements View.OnClickListener{

    private EditText newName;
    private EditText lastName;
    private Button addbtn;

    myDBAdapter helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        helper = new myDBAdapter(this);
        newName = (EditText) findViewById(R.id.newName);
        lastName = (EditText) findViewById(R.id.lastName);
        addbtn  = (Button) findViewById(R.id.addbtn);
        addbtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
            long rid = helper.insertData(newName.getText().toString(), lastName.getText().toString());
            Cursor c = helper.getData();
            String[] rows = new String[c.getCount()];
            while(c.moveToNext()){

                rows[c.getPosition()] = "ID: " + c.getInt(c.getColumnIndex(myDBAdapter.myDBHelper.UID)) + ", Name:" +
                        c.getString(c.getColumnIndex(myDBAdapter.myDBHelper.FNAME));
            }
            if (rid>0){
                Message.message(getApplicationContext(),"Instance Added");
                newName.setText("");
                lastName.setText("");
                Intent intent = new Intent(this, MainActivity.class);
                finish();

            }else {
                Message.message(getApplicationContext(), "Unsuccessful");
                newName.setText("");
                lastName.setText("");
            }


    }
}
