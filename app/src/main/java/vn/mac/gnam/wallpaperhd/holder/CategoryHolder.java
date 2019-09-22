package vn.mac.gnam.wallpaperhd.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.mac.gnam.wallpaperhd.R;

public class CategoryHolder extends RecyclerView.ViewHolder {
    public ImageView imgAvatar;
    public TextView tvTitle;

    public CategoryHolder(@NonNull View itemView) {
        super(itemView);
        imgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatar);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
    }
}
