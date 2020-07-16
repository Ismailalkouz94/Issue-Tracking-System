package com.ismail.its.ui.issues;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ismail.its.R;
import com.ismail.its.adapter.IssueAdapter;
import com.ismail.its.model.Issues;
import com.ismail.its.model.response.IssueResponse;
import com.ismail.its.network.ApiService;
import com.ismail.its.network.RetrofitClientInstance;
import com.ismail.its.utils.Constants;
import com.ismail.its.utils.SharedPrefUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssuesFragment extends Fragment {

    private IssuesViewModel issuesViewModel;

    RecyclerView recyclerView;
    IssueAdapter issueAdapter;
    List<Issues> issuesList =new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        issuesViewModel =
                ViewModelProviders.of(this).get(IssuesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_issues, container, false);
        getIssueData(root);

        return root;
    }

    public void getIssueData(final View view){
//        User user=new User(1l,"","","",true,"ismail",null,null);
//        User asssginTo=new User(2l,"","","",true,"Ali",null,null);
//
//        issuesList.add(new Issues(1L,"test","test","test", user,asssginTo,null,null,null,null));


        ApiService service = RetrofitClientInstance.getRetrofitInstance(getContext()).create(ApiService.class);
        Long userId = SharedPrefUtils.getSharedPrefeLong(getContext().getSharedPreferences(Constants.SHARED_PERF_NAME, Context.MODE_PRIVATE), Constants.USER_ID);
        Call<IssueResponse> call = service.getIssues(userId,2L);
        call.enqueue(new Callback<IssueResponse>() {
            @Override
            public void onResponse(Call<IssueResponse> call, Response<IssueResponse> response) {
                if (response.code() == 200) {
                    if (response.body().getResponse().size()>0){
                        issuesList= response.body().getResponse();
                        recyclerView = view.findViewById(R.id.issue_recycler);
                        issueAdapter = new IssueAdapter(issuesList);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));
                        recyclerView.setAdapter(issueAdapter);
                        issueAdapter.notifyDataSetChanged();
                    }
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(getContext(), jsonObject.get("errMsg").toString(), Toast.LENGTH_SHORT).show();
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<IssueResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
