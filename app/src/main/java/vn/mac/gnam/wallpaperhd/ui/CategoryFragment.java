package vn.mac.gnam.wallpaperhd.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.mac.gnam.wallpaperhd.R;
import vn.mac.gnam.wallpaperhd.adapter.CategoryAdapter;
import vn.mac.gnam.wallpaperhd.adapter.LatestAdapter;
import vn.mac.gnam.wallpaperhd.model.category.Category;
import vn.mac.gnam.wallpaperhd.model.latest.Latest;
import vn.mac.gnam.wallpaperhd.service.Retrofit;

public class CategoryFragment extends Fragment {
    private ProgressBar progressBar;
    private RecyclerView rvCategory;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_category, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        rvCategory = (RecyclerView) view.findViewById(R.id.rvCategory);
        categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(), categoryList);
        rvCategory.setAdapter(categoryAdapter);
        linearLayoutManager = new LinearLayoutManager(getContext());
        rvCategory.setLayoutManager(linearLayoutManager);
        getCategoryData();
        showProgressBar(true);
        return view;
    }

    private void getCategoryData() {
        Retrofit.
                getInstance().
                getCategory(1,15)
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                        if (response.code() == 200){
                            categoryList.addAll(response.body());
                            categoryAdapter.notifyDataSetChanged();

                        }else {
                            Toast.makeText(getContext(), "Lỗi mạng", Toast.LENGTH_SHORT).show();
                        }
                        showProgressBar(false);
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        Toast.makeText(getContext(), "Lỗi mạng", Toast.LENGTH_SHORT).show();
                        showProgressBar(false);
                    }
                });
    }

    private void showProgressBar(boolean isShow){
        if (isShow) {
            progressBar.setVisibility(View.VISIBLE);
            rvCategory.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            rvCategory.setVisibility(View.VISIBLE);
        }
    }
}
