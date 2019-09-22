package vn.mac.gnam.wallpaperhd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import vn.mac.gnam.wallpaperhd.R;
import vn.mac.gnam.wallpaperhd.database.FavoriteDAO;
import vn.mac.gnam.wallpaperhd.model.Favorite;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<Favorite> favoriteLists;
    Context context;
    private OnItemClickListener mListener;

    public FavoriteAdapter(List<Favorite> favoriteLists, Context context) {
        this.favoriteLists = favoriteLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_favorite, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide
                .with(context)
                .load(favoriteLists.get(i).getUrl())
                .into(viewHolder.imgFavorite);

        viewHolder.imgLike.setImageResource(R.drawable.ic_favorite);

    }


    @Override
    public int getItemCount() {
        return favoriteLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgFavorite;
        ImageView imgLike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFavorite = (ImageView) itemView.findViewById(R.id.imgFavorite);
            imgLike = (ImageView) itemView.findViewById(R.id.imgLike);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

}
