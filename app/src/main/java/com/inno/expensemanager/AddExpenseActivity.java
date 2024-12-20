package com.inno.expensemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {
    private ExpenseDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        EditText descriptionInput = findViewById(R.id.description_input);
        EditText amountInput = findViewById(R.id.amount_input);
        Spinner categorySpinner = findViewById(R.id.category_spinner);
        Button saveButton = findViewById(R.id.save_button);

        dbHelper = new ExpenseDatabaseHelper(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = descriptionInput.getText().toString().trim();
                String amountText = amountInput.getText().toString().trim();
                String category = categorySpinner.getSelectedItem().toString();

                if (description.isEmpty() || amountText.isEmpty()) {
                    Toast.makeText(AddExpenseActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                double amount = Double.parseDouble(amountText);
                boolean success = dbHelper.insertExpense(description, category, amount);

                if (success) {
                    Toast.makeText(AddExpenseActivity.this, "Expense added", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddExpenseActivity.this, "Error adding expense", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
