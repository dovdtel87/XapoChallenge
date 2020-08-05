package com.dmgdavid2109.xapochallenge.common.data

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    fun <R> map(transformation: (T) -> R): Result<R> {
        return when (this) {
            is Success -> Success(transformation(data))
            is Error -> Error(exception)
        }
    }

    fun <R> fold(ifFailure: (Exception) -> R, ifSuccess: (T) -> R): R {
        return when (this) {
            is Success -> ifSuccess(data)
            is Error -> ifFailure(exception)
        }
    }

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
