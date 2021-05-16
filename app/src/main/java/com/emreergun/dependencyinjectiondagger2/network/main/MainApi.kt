package com.emreergun.dependencyinjectiondagger2.network.main
import com.emreergun.dependencyinjectiondagger2.models.post.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {

    // posts?userId=1
    @GET("user/{userId}/posts")
    fun getPosts(@Path("userId") userID: Int): Single<List<Post>>


}