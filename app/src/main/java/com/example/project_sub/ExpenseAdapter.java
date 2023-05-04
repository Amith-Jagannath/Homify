package com.example.project_sub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseViewHolder> {

    private List<Expense> expenseList;

    public ExpenseAdapter(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }


    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expense, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense e = expenseList.get(position);
        holder.bind(e);

    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public void updateList(ArrayList<Expense> list) {
        expenseList.clear();
        expenseList.addAll(list);
        notifyDataSetChanged();
    }


}


class ExpenseViewHolder extends RecyclerView.ViewHolder {


    private AppCompatTextView day;
    private AppCompatTextView amount;

    public ExpenseViewHolder(@NonNull View itemView) {
        super(itemView);
        day = itemView.findViewById(R.id.type);
        amount = itemView.findViewById(R.id.amount);
    }

    public void bind(Expense expense) {
        day.setText(expense.day);
        amount.setText(expense.amount);
    }

}
