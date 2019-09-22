package vn.mac.gnam.wallpaperhd.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.mac.gnam.wallpaperhd.ItemClickListener;
import vn.mac.gnam.wallpaperhd.R;

public class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView imgGirl;
    private ItemClickListener itemClickListener;

    public ImageHolder(@NonNull View itemView) {
        super(itemView);
        imgGirl = itemView.findViewById(R.id.imgGirl);
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
