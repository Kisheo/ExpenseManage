package com.inno.expensemanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ExpenseDatabaseHelper dbHelper;
    private ExpenseAdapter adapter;
    private ArrayList<Expense> expenseList;
    private TextView totalExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.expense_list);
        Button addExpenseButton = findViewById(R.id.add_expense_button);
        totalExpenses = findViewById(R.id.total_expenses);

        dbHelper = new ExpenseDatabaseHelper(this);
        expenseList = new ArrayList<>();
        adapter = new ExpenseAdapter(this, expenseList);
        listView.setAdapter(adapter);

        loadExpenses();

        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadExpenses() {
        Cursor cursor = dbHelper.getAllExpenses();
        expenseList.clear();
        double total = 0;

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String description = cursor.getString(1);
                String category = cursor.getString(2);
                double amount = cursor.getDouble(3);

                expenseList.add(new Expense(id, description, category, amount));
                total += amount;
            }
            cursor.close();
        }

        adapter.notifyDataSetChanged();
        totalExpenses.setText("Total: $" + total);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadExpenses();
    }
}
