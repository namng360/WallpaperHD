package vn.mac.gnam.wallpaperhd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.mac.gnam.wallpaperhd.R;
import vn.mac.gnam.wallpaperhd.holder.CategoryHolder;
import vn.mac.gnam.wallpaperhd.model.category.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {
    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.tvTitle.setText(category.getName());

        String name = holder.tvTitle.getText().toString().trim();
        if (name.equals("3D")) {
            Glide.with(context).
                    load("https://d11wkw82a69pyn.cloudfront.net/siteassets/images/3d-printing/3dprinting_1440x600_canvas_low.jpg")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        } else if (name.equals("Asian")) {
            Glide.with(context).
                    load("https://www.sparklebox.co.uk/wp-content/uploads/1-7174.jpg")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        } else if (name.equals("Cambodia")) {
            Glide.with(context).
                    load("https://www.deccanherald.com/sites/dh/files/styles/article_detail/public/article_images/2019/01/05/file72l7xkabyw0v5etg71w-1546660565.jpg?itok=xAlgqjvD")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        } else if (name.equals("Car")) {
            Glide.with(context).
                    load("https://vinfast.vn/themes/custom/vinfast/static/images/home/thumbnail-3.png")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        } else if (name.equals("China")) {
            Glide.with(context).
                    load("http://iaic2018.iaas.org.sg/wp-content/uploads/2017/12/banner3-1500x630.jpg")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        } else if(name.equals("Galaxy")){
            Glide.with(context).
                    load("https://image.freepik.com/free-psd/banner-galaxy-abstract-background_47618-35.jpg")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        }else if(name.equals("Girl")){
            Glide.with(context).
                    load("https://cdn.nhanh.vn/cdn/store/14345/ps/20190105/1_1000x389.jpg")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        }else if(name.equals("Japan")){
            Glide.with(context).
                    load("https://i.pinimg.com/originals/c3/1b/7c/c31b7c1e9618a3b84c898db2df8e3d6d.jpg")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        }else if(name.equals("Landscape")){
            Glide.with(context).
                    load("https://cdn.pixabay.com/photo/2017/06/05/10/15/landscape-2373651_960_720.jpg")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        }else if(name.equals("Laos")){
            Glide.with(context).
                    load("http://asiarevealtour.com/wp-content/uploads/2015/01/Banner-Destinations-Vientiane-2.jpg")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        }else if(name.equals("MotoBike")){
            Glide.with(context).
                    load("https://s3-ap-southeast-2.amazonaws.com/imotor-cms/images_cms/Honda_inside_banner_3_nm.jpg")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        }else if(name.equals("Planet")){
            Glide.with(context).
                    load("https://www.paolini.net/wp-content/uploads/2018/03/1001-future-planet-banner.jpg")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        }else if(name.equals("Sea")){
            Glide.with(context).
                    load("http://belfasthills.org/wp-content/uploads/2015/02/Shallow-Tropical-Sea-Banner.jpg")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        }else if(name.equals("Truck")){
            Glide.with(context).
                    load("http://quickcarshipping.com/wp-content/uploads/2017/02/banner-truck.jpg")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        }else if(name.equals("VietNam")){
            Glide.with(context).
                    load("https://lattitude.org.uk/wp-content/uploads/2016/08/vietnam-banner.jpg")
                    .placeholder(R.drawable.phd).into(holder.imgAvatar);
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
