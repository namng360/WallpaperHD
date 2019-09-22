package vn.mac.gnam.wallpaperhd.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.mac.gnam.wallpaperhd.ItemClickListener;
import vn.mac.gnam.wallpaperhd.R;

public class LatestHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tvName;
    public ImageView imgThumb;
    private ItemClickListener itemClickListener;

    public LatestHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvTitle);
        imgThumb = itemView.findViewById(R.id.imgAvatar);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());
    }

}
