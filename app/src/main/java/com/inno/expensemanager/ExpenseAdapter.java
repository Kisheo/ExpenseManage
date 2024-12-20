package com.inno.expensemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExpenseAdapter extends ArrayAdapter<Expense> {
    public ExpenseAdapter(Context context, ArrayList<Expense> expenses) {
        super(context, 0, expenses);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Reuse the view if possible, otherwise inflate a new view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.expense_item, parent, false);
        }

        // Get the current expense
        Expense expense = getItem(position);

        // Find and populate the views in the layout
        TextView descriptionView = convertView.findViewById(R.id.expense_description);
        TextView categoryView = convertView.findViewById(R.id.expense_category);
        TextView amountView = convertView.findViewById(R.id.expense_amount);

        if (expense != null) {
            descriptionView.setText(expense.getDescription());
            categoryView.setText(expense.getCategory());
            amountView.setText(String.format("$%.2f", expense.getAmount()));
        }

        return convertView;
    }
}
