package vn.mac.gnam.wallpaperhd.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import maes.tech.intentanim.CustomIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.mac.gnam.wallpaperhd.EndlessRecyclerViewScrollListener;
import vn.mac.gnam.wallpaperhd.R;
import vn.mac.gnam.wallpaperhd.adapter.ImageAdapter;
import vn.mac.gnam.wallpaperhd.model.image.Image;
import vn.mac.gnam.wallpaperhd.service.Retrofit;

public class ListImageActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipe;
    private RecyclerView rvListImage;
    private Toolbar toolbar;
    private List<Image> listImage;
    private ImageAdapter imageAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int per_page = 5;
    private int page = 1;
    private int parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_image);
        swipe = findViewById(R.id.swipe);
        rvListImage = findViewById(R.id.rvListImage);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("List Image");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListImageActivity.this, HomeActivity.class);
                startActivity(intent);
                CustomIntent.customType(ListImageActivity.this, "fadein-to-fadeout");
                finish();
            }
        });
        listImage = new ArrayList<>();
        imageAdapter = new ImageAdapter(this, listImage);
        linearLayoutManager = new LinearLayoutManager(this);
        rvListImage.setHasFixedSize(true);
        rvListImage.setLayoutManager(linearLayoutManager);
        rvListImage.setAdapter(imageAdapter);
        swipe.setRefreshing(true);
        getData(page,per_page,Integer.parseInt(getIntent().getStringExtra("id")));
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listImage.clear();
                getData(page,per_page,Integer.parseInt(getIntent().getStringExtra("id")));
                imageAdapter.setOnLoadMore(true);
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipe.setRefreshing(false);
                    }
                }, 2000); // Delay in millis
            }
        });

        swipe.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );


        rvListImage.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                getData(page + 1, per_page, parent);
            }
        });
    }

    private void getData(int page, int per_page, int parent) {
        Retrofit.getInstance().getListImage(page,per_page,parent).enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                swipe.setRefreshing(false);
                if (response.body().size() == 0){
                    rvListImage.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                        @Override
                        public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                            rvListImage.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                                @Override
                                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

                                }
                            });
                        }
                    });

                    imageAdapter.setOnLoadMore(false);

                }
                listImage.addAll(response.body());
                imageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {
                swipe.setRefreshing(false);
            }
        });
    }
}
