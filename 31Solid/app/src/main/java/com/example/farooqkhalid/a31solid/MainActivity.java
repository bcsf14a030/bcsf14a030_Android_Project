package com.example.farooqkhalid.a31solid;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddNew;
    private Button users;
    myDBAdapter helper;
    LinearLayout myLayout,hideLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new myDBAdapter(this);

        btnAddNew = (Button) findViewById(R.id.button);
        users = (Button) findViewById(R.id.users);
        myLayout = (LinearLayout) findViewById(R.id.myLayout);
        hideLayout = (LinearLayout) findViewById(R.id.hide);

        btnAddNew.setOnClickListener(this);
        users.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.button){
            Intent intent = new Intent(this, AddNewUser.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.users){
            hideLayout.removeAllViewsInLayout();
            try{
                Cursor cursor = helper.getData();
                int value = cursor.getCount();
                for (int i = 0; i < cursor.getCount(); i++){
                    cursor.moveToNext();
                    int id = cursor.getInt(cursor.getColumnIndex(myDBAdapter.myDBHelper.UID));
                    String fname = cursor.getString(cursor.getColumnIndex(myDBAdapter.myDBHelper.FNAME));
                    String lname = cursor.getString(cursor.getColumnIndex(myDBAdapter.myDBHelper.LNAME));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            600,
                            100);
                    Button btn = new Button(this);
                    btn.setId(id);
                    btn.setMinWidth(300);
                    btn.setMinHeight(50);
                    btn.setText(fname + " " + lname);
                    btn.setOnClickListener(this);
                    params.gravity = Gravity.CENTER;
                    //btn.setGravity(Gravity.CENTER);
                    hideLayout.addView(btn, params);
                }
            }catch (Exception e){}

        }
        else{
            Intent intent = new Intent(this, Expenses.class);
            intent.putExtra("id", view.getId());
            startActivity(intent);
        }
    }
}
