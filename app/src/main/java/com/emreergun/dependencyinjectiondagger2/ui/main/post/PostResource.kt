package com.emreergun.dependencyinjectiondagger2.ui.main.post

import com.emreergun.dependencyinjectiondagger2.ui.auth.AuthResource

sealed class PostResource<T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null
) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }
    class Success<T>(data: T? = null) : PostResource<T>(Status.SUCCESS, data)
    class Error<T>(msg: String, data: T? = null) : PostResource<T>(Status.ERROR, data, msg)
    class Loading<T>(data: T? = null) : PostResource<T>(Status.LOADING, data)
}
