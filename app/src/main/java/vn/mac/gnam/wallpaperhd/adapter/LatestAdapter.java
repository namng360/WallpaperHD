package vn.mac.gnam.wallpaperhd.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import maes.tech.intentanim.CustomIntent;
import vn.mac.gnam.wallpaperhd.ItemClickListener;
import vn.mac.gnam.wallpaperhd.R;
import vn.mac.gnam.wallpaperhd.holder.LatestHolder;
import vn.mac.gnam.wallpaperhd.model.latest.Latest;
import vn.mac.gnam.wallpaperhd.ui.ListImageActivity;


public class LatestAdapter extends RecyclerView.Adapter<LatestHolder> {
    private Context context;
    private List<Latest> latests;

    public LatestAdapter(Context context, List<Latest> latests) {
        this.context = context;
        this.latests = latests;

    }

    @NonNull
    @Override
    public LatestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_latest, parent, false);
        return new LatestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LatestHolder holder, int position) {
        final Latest latest = latests.get(position);

        holder.tvName.setText(latest.getTitle().getRendered());

        Glide.with(context).
                load("http://file.vforum.vn/hinh/2018/01/top-nhung-hot-girl-viet-nam-xinh-nhat-hien-nay-2018-14.png").placeholder(R.drawable.phd).into(holder.imgThumb);


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(context, ListImageActivity.class);
                intent.putExtra("id", latest.getId() + "");
                context.startActivity(intent);
                CustomIntent.customType(context, "fadein-to-fadeout");
            }
        });

    }

    @Override
    public int getItemCount() {
        return latests.size();
    }


}
