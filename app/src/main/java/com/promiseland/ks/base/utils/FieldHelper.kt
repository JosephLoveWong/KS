package com.promiseland.ks.base.utils

import kotlin.jvm.internal.Intrinsics

/**
 * Created by Administrator on 2017/11/14.
 */
object FieldHelper {
    fun isEmpty(collection: Collection<*>?): Boolean {
        return collection?.isEmpty() ?: true
    }

    fun isNotEmpty(collection: Collection<*>?): Boolean {
        return !(collection?.isEmpty() ?: true)
    }

    fun isEmpty(str: CharSequence?): Boolean {
        return str == null || str.isEmpty()
    }

    fun equals(a: CharSequence?, b: CharSequence?): Boolean {
        if (a === b) {
            return true
        }
        if (a == null || b == null || a.length != b.length) {
            return false
        }
        if (a is String && b is String) {
            return Intrinsics.areEqual(a, b)
        }
        val it = IntRange(0, a.length - 1).iterator()
        while (it.hasNext()) {
            val index = it.nextInt()
            if (a[index] != b[index]) {
                return false
            }
        }
        return true
    }

    fun isLargerZero(value: Int?): Boolean {
        return value != null && value.toInt() > 0
    }

    fun getPrimitive(value: Double?): Double {
        return value ?: 0.0
    }

    fun getPrimitive(value: Int?): Int {
        return value ?: 0
    }

    fun getPrimitive(value: Float?): Float {
        return value ?: 0.0f
    }

    fun hasPosition(list: List<*>?, position: Int): Boolean {
        return if (position < 0) {
            false
        } else position < list?.size ?: 0
    }

    fun getListSize(list: List<*>?): Int {
        return list?.size ?: 0
    }

    fun <T, R> mapList(list: List<T>?, mapFunction: Function1<T, R>): List<R>? {
        Intrinsics.checkParameterIsNotNull(mapFunction, "mapFunction")
        if (list == null) {
            return null
        }
        val mappedList = mutableListOf<R>()
        list.mapTo(mappedList) { mapFunction.invoke(it) }
        return mappedList
    }

    fun isLastPosition(list: List<*>?, position: Int): Boolean {
        if (list != null) {
            if ((if (!(list as Collection<*>).isEmpty()) 1 else null) != null && position == list.size - 1) {
                return true
            }
        }
        return false
    }

    fun containsKeys(map: Map<*, *>?, vararg keys: Any): Boolean {
        Intrinsics.checkParameterIsNotNull(keys, "keys")
        if (map == null) {
            return false
        }
        return keys.any { map.containsKey(it) }
    }

}