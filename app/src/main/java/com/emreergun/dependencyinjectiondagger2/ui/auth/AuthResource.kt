package com.emreergun.dependencyinjectiondagger2.ui.auth

// this is a Jetpack use guide
// A generic class that contains data and status about loading this data.
sealed class AuthResource<T>(
    val status: AuthStatus,
    val data: T? = null,
    val message: String? = null
) {
    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED,COMPLETED
    }

    class Authenticated<T>(data: T? = null) : AuthResource<T>(AuthStatus.AUTHENTICATED, data)
    class Error<T>(msg: String, data: T? = null) : AuthResource<T>(AuthStatus.ERROR, data, msg)
    class Loading<T>(data: T? = null) : AuthResource<T>(AuthStatus.LOADING, data)
    class Logout<T> : AuthResource<T>(AuthStatus.NOT_AUTHENTICATED)
    class Completed<T>(data: T? = null) : AuthResource<T>(AuthStatus.COMPLETED)

}
