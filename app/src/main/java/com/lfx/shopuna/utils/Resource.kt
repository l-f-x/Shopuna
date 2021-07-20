package com.lfx.shopuna.utils

data class Resource<out T>(val status:NetworkStatus, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(NetworkStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(NetworkStatus.ERROR, data, msg)
        }

        fun <T> loading(msg: String?, data: T?): Resource<T> {
            return Resource(NetworkStatus.LOADING, data, msg)
        }

    }

}