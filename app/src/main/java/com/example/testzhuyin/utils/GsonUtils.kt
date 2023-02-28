package com.example.testzhuyin.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonUtils {

    private val gsonBuilder: GsonBuilder by lazy { GsonBuilder() }

    private val gson: Gson by lazy { gsonBuilder.create() }

    fun toJson(paramObject: Any?): String = gson.toJson(paramObject)

    fun <T> json2Object(json: String?, clazz: Class<T>?): T? {
        return try {
            gson.fromJson(json, clazz)
        } catch (e: Exception) {
            null
        }
    }

    fun <T> json2List(json: String?, clazz: Class<Array<T>>): List<T> {
        return try {
            gson.fromJson(json, clazz)?.toList()?: emptyList()
        }catch (e:Exception){
            emptyList()
        }
    }
}