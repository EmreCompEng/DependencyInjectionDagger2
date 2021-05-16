package com.emreergun.dependencyinjectiondagger2.ui.main.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emreergun.dependencyinjectiondagger2.SessionManager
import com.emreergun.dependencyinjectiondagger2.models.post.Post
import com.emreergun.dependencyinjectiondagger2.models.user.User
import com.emreergun.dependencyinjectiondagger2.network.main.MainApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
) : ViewModel() {

    companion object {
        private const val TAG = "PostViewModel "
    }

    private val postsLiveData = MutableLiveData<PostResource<List<Post>>>()
    private var user: User? = null

    init {
        user = sessionManager.cachedUserLiveData.value?.data // Null Check yap
        Log.d(TAG, "User Id: ${user?.id} fetched posts... : ")
    }


    fun observePosts(): LiveData<PostResource<List<Post>>> {
        if (user != null) {
            postsLiveData.value = PostResource.Loading() // Loading....
            mainApi.getPosts(user!!.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : DisposableSingleObserver<List<Post>>() {
                    override fun onSuccess(postList: List<Post>) {
                        postsLiveData.value = PostResource.Success(postList) // Success...
                        Log.d(TAG, "onSuccess: ")

                    }
                    override fun onError(error: Throwable) {
                        Log.d(TAG, "onError: ${error.message}")
                        postsLiveData.value =
                            PostResource.Error("Something went wrong") // Error...

                    }
                })
        }
        return postsLiveData
    }

}


























