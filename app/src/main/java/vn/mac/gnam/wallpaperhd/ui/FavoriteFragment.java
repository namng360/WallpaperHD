package vn.mac.gnam.wallpaperhd.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.mac.gnam.wallpaperhd.R;
import vn.mac.gnam.wallpaperhd.adapter.FavoriteAdapter;
import vn.mac.gnam.wallpaperhd.database.Database;
import vn.mac.gnam.wallpaperhd.database.FavoriteDAO;
import vn.mac.gnam.wallpaperhd.model.Favorite;

public class FavoriteFragment extends Fragment implements FavoriteAdapter.OnItemClickListener {
    private RecyclerView rvFavorite;
    private FavoriteAdapter favoriteAdapter;
    private List<Favorite> listFavorites;
    FavoriteDAO favoriteDAO;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rvFavorite = (RecyclerView) view.findViewById(R.id.rvFavorite);
        favoriteDAO = new FavoriteDAO(getActivity());
        listFavorites = new ArrayList<>();
        listFavorites = favoriteDAO.getAllFavorite();
        favoriteAdapter = new FavoriteAdapter(listFavorites, getContext());
        rvFavorite.setHasFixedSize(true);
        favoriteAdapter.setOnItemClickListener(this);
        linearLayoutManager = new LinearLayoutManager(getContext());
        rvFavorite.setLayoutManager(linearLayoutManager);
        rvFavorite.setAdapter(favoriteAdapter);

        return view;
    }


    @Override
    public void onItemClick(int position) {
        favoriteDAO.deleteURL(listFavorites.get(position).getUrl());
        listFavorites.remove(position);
        favoriteAdapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
    }
}
