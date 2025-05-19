package com.example.tryoutpas_absen14_absen8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tryoutpas_absen14_absen8.teamAdapter;
import com.example.tryoutpas_absen14_absen8.ApiService;
import com.example.tryoutpas_absen14_absen8.RetrofitClient;
import com.example.tryoutpas_absen14_absen8.Team;
import  com.example.tryoutpas_absen14_absen8.TeamResponse;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SpanishFragment extends Fragment {
    private RecyclerView recyclerViewSP;
    private teamAdapter teamadapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spanyol, container, false);
        recyclerViewSP = view.findViewById(R.id.recyclerviewSP);
        recyclerViewSP.setLayoutManager(new LinearLayoutManager(getContext()));
        loadTeams();
        return view;
    }

    private void loadTeams() {
        RetrofitClient.getRetrofitInstance()
                .create(ApiService.class)
                .getAllTeams2()
                .enqueue(new Callback<TeamResponse>() {
                    @Override
                    public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<Team> teams = response.body().getTeams();
                            teamadapter = new teamAdapter(requireContext(), teams);
                            recyclerViewSP.setAdapter(teamadapter);
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
