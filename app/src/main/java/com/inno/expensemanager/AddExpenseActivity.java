package com.inno.expensemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {

    private Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        // Initialize views
        EditText descriptionInput = findViewById(R.id.description_input);
        EditText amountInput = findViewById(R.id.amount_input);
        categorySpinner = findViewById(R.id.category_spinner);
        Button saveButton = findViewById(R.id.save_button);

        // Set up the category spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.expense_categories,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // Set up the save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String description = descriptionInput.getText().toString().trim();
                String amountStr = amountInput.getText().toString().trim();
                String category = (String) categorySpinner.getSelectedItem();

                // Validate inputs
                if (description.isEmpty()) {
                    Toast.makeText(AddExpenseActivity.this, "Please enter a description", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (amountStr.isEmpty()) {
                    Toast.makeText(AddExpenseActivity.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (category == null || category.isEmpty()) {
                    Toast.makeText(AddExpenseActivity.this, "Please select a category", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Parse the amount
                double amount;
                try {
                    amount = Double.parseDouble(amountStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(AddExpenseActivity.this, "Invalid amount format", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a new expense object
                Expense expense = new Expense(description, amount, category);

                // Add the expense to the list (handled in MainActivity or database)
                MainActivity.expenseList.add(expense);

                // Notify the user
                Toast.makeText(AddExpenseActivity.this, "Expense added successfully", Toast.LENGTH_SHORT).show();

                // Finish the activity
                finish();
            }
        });
    }
}
