package com.example.tryoutpas_absen14_absen8;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tryoutpas_absen14_absen8.teamAdapter;
import com.example.tryoutpas_absen14_absen8.ApiService;
import com.example.tryoutpas_absen14_absen8.RetrofitClient;
import com.example.tryoutpas_absen14_absen8.Team;
import  com.example.tryoutpas_absen14_absen8.TeamResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnglishFragment extends Fragment {
    private RecyclerView recyclerViewEN;
    private teamAdapter teamadapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inggris, container, false);
        recyclerViewEN = view.findViewById(R.id.recyclerviewEN);
        recyclerViewEN.setLayoutManager(new LinearLayoutManager(getContext()));
        loadTeams();
        return view;
    }
    private void loadTeams() {
        RetrofitClient.getRetrofitInstance()
                .create(ApiService.class)
                .getAllTeams()
                .enqueue(new Callback<TeamResponse>() {
                    @Override
                    public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Team> teams = response.body().getTeams();
                            teamadapter = new teamAdapter(requireContext(), teams);
                            recyclerViewEN.setAdapter(teamadapter);
                        } else {
                            Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TeamResponse> call, Throwable t) {
                        Toast.makeText(getContext(), "Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
         });
}
}