package vn.mac.gnam.wallpaperhd.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.mac.gnam.wallpaperhd.model.category.Category;
import vn.mac.gnam.wallpaperhd.model.image.Image;
import vn.mac.gnam.wallpaperhd.model.latest.Latest;

public interface Service {
    @GET("/wp-json/wp/v2/posts?_embed")
    Call<List<Latest>> getDataOfLatest();

    @GET("/wp-json/wp/v2/media")
    Call<List<Image>> getListImage(@Query("page") int page,
                                   @Query("per_page") int per_page,
                                   @Query("parent") int parent);

    @GET("/wp-json/wp/v2/categories")
    Call<List<Category>> getCategory(@Query("page") int page,
                                     @Query("per_page") int per_page);
//
//    @GET("/wp-json/wp/v2/media")
//    Call<List<Image>> getImage(@Query("parent") int parent);


}
