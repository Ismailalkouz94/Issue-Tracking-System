package com.ismail.its.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ismail.its.R;
import com.ismail.its.model.Issues;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.MyViewHolder> {
    private List<Issues> issuesList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView issueTitle, issueOwner, issueAssignTo;

        public MyViewHolder(View view) {
            super(view);
            issueTitle = view.findViewById(R.id.issueTitle);
            issueOwner = view.findViewById(R.id.issueOwner);
            issueAssignTo = view.findViewById(R.id.issueAssignTo);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public IssueAdapter(List<Issues> myDataset) {
        issuesList = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public IssueAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.issue_list_row, parent, false);

        return new MyViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.issueTitle.setText(issuesList.get(position).getTitle());
        holder.issueOwner.setText(issuesList.get(position).getUser().getUserName());
        holder.issueAssignTo.setText(issuesList.get(position).getAssignTo().getUserName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return issuesList.size();
    }

    public void setIssuesList(List<Issues> issuesList){
        this.issuesList=issuesList;

    }

}