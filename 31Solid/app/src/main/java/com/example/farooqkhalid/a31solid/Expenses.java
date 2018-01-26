package com.example.farooqkhalid.a31solid;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Expenses extends AppCompatActivity implements View.OnClickListener{

    private EditText expense;
    private EditText amount;
    private Integer id;
    private Button save;
    private TextView hisab;
    private Integer total = 0;
    private String format = "Expenses: \n";
    myDBAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        helper = new myDBAdapter(this);
        expense = (EditText) findViewById(R.id.expense);
        amount = (EditText) findViewById(R.id.amount);
        save = (Button) findViewById(R.id.save);
        hisab = (TextView) findViewById(R.id.hisab);
        id = getIntent().getIntExtra("id", -1);
        save.setOnClickListener(this);

        Cursor cursor = helper.getExpenses(id);

        if(cursor.getCount() > 0) {

            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                int amount = cursor.getInt(cursor.getColumnIndex(myDBAdapter.myDBHelper.AMOUNT));
                String expenseName = cursor.getString(cursor.getColumnIndex(myDBAdapter.myDBHelper.EXPENSE));
                total = total + amount;
                format = format + expenseName + ": " + amount + "\n";
            }
            format = format + "Total: "+ total + "\n";
            hisab.setText(format);
        }


    }

    @Override
    public void onClick(View view) {
        long rid = helper.insertExpense(expense.getText().toString(), Integer.parseInt(amount.getText().toString()) , id);
        if (rid>0){
            Message.message(getApplicationContext(),"Expense Added");
            expense.setText("");
            amount.setText("");
            Intent intent = new Intent(this, MainActivity.class);
            finish();

        }else {
            Message.message(getApplicationContext(), "Unsuccessful");
            expense.setText("");
            amount.setText("");
        }
    }
}
