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
import vn.mac.gnam.wallpaperhd.holder.ImageHolder;
import vn.mac.gnam.wallpaperhd.holder.LoadHolder;
import vn.mac.gnam.wallpaperhd.model.image.Image;
import vn.mac.gnam.wallpaperhd.ui.DetailImageActivity;


public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Image> imagelist;

    public boolean isOnLoadMore() {
        return onLoadMore;
    }

    public void setOnLoadMore(boolean onLoadMore) {
        this.onLoadMore = onLoadMore;
    }

    private boolean onLoadMore = true;


    public ImageAdapter(Context context, List<Image> imagelist) {
        this.context = context;
        this.imagelist = imagelist;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.item_list_image, parent, false);
            return new ImageHolder(view);
        } else {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.load_more, parent, false);
            return new LoadHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageHolder) {
            final Image images = imagelist.get(position);
            if (images.getGuid().getRendered() != null && !images.getGuid().getRendered().isEmpty()){
                Glide.with(context).
                        load(images.getGuid().getRendered()).placeholder(R.drawable.phd).into(((ImageHolder) holder).imgGirl);
            }
            ((ImageHolder) holder).setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(context, DetailImageActivity.class);
                    Image images = imagelist.get(position);
                    intent.putExtra("detail", images.getGuid().getRendered());
                    context.startActivity(intent);
                    CustomIntent.customType(context, "fadein-to-fadeout");
                }
            });

        } else if (holder instanceof LoadHolder){

        }
    }

    int ITEM = 1;
    int LOAD_MORE = 2;

    @Override
    public int getItemViewType(int position) {

        if (onLoadMore){
            if (position == imagelist.size() - 1) {
                return LOAD_MORE;
            }
            else{
                return ITEM;
            }
        }else{
            return ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return imagelist.size();
    }


}
