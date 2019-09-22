package vn.mac.gnam.wallpaperhd.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.mac.gnam.wallpaperhd.R;
import vn.mac.gnam.wallpaperhd.adapter.LatestAdapter;
import vn.mac.gnam.wallpaperhd.model.latest.Latest;
import vn.mac.gnam.wallpaperhd.service.Retrofit;

public class LatestFragment extends Fragment {
    private RecyclerView rvLatest;
    private List<Latest> latestList;
    private GridLayoutManager gridLayoutManager;
    private LatestAdapter latestAdapter;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_latest, container, false);
        rvLatest = (RecyclerView) view.findViewById(R.id.rvLatest);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        latestList = new ArrayList<>();
        latestAdapter = new LatestAdapter(getContext(), latestList);
        rvLatest.setAdapter(latestAdapter);
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvLatest.setLayoutManager(gridLayoutManager);
        getLatestData();
        showProgressBar(true);
        return view;
    }

    private void getLatestData() {
        Retrofit.
                getInstance().
                getDataOfLatest()
                .enqueue(new Callback<List<Latest>>() {
                    @Override
                    public void onResponse(Call<List<Latest>> call, Response<List<Latest>> response) {

                        if (response.code() == 200){
                            latestList.addAll(response.body());
                            latestAdapter.notifyDataSetChanged();

                        }else {
                            Toast.makeText(getContext(), "Lỗi mạng", Toast.LENGTH_SHORT).show();
                        }
                        showProgressBar(false);
                    }

                    @Override
                    public void onFailure(Call<List<Latest>> call, Throwable t) {
                        Log.e("data", t.getMessage() + "");
                        Toast.makeText(getContext(), "Lỗi mạng", Toast.LENGTH_SHORT).show();
                        showProgressBar(false);
                    }
                });
    }

    private void showProgressBar(boolean isShow){
        if (isShow) {
            progressBar.setVisibility(View.VISIBLE);
            rvLatest.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            rvLatest.setVisibility(View.VISIBLE);
        }
    }
}
