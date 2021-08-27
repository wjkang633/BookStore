package woojin.project.android.aop_part3_chapter04.api


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import woojin.project.android.aop_part3_chapter04.model.BestSellerDTO
import woojin.project.android.aop_part3_chapter04.model.SearchBookDTO

interface BookService {
    @GET("/api/search.api?output=json")
    fun getBooksByName(
        @Query("key") apiKye: String,
        @Query("query") keyword: String,
    ): Call<SearchBookDTO>

    @GET("/api/bestSeller.api?output=json&categoryId=100")
    fun getBestSellerBooks(
        @Query("key") apiKye: String,
    ): Call<BestSellerDTO>
}