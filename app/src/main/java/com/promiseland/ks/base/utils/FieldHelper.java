package com.promiseland.ks.base.utils;

import java.util.Collection;
import java.util.List;

public final class FieldHelper {
    public static final boolean isEmpty(Collection<?> collection) {
        return collection != null ? collection.isEmpty() : true;
    }

    public static final boolean isNotEmpty(Collection<?> collection) {
        return !(collection != null ? collection.isEmpty() : true);
    }

    public static final boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

//    public static final boolean isEmpty(Image image) {
//        return isEmpty((CharSequence) image != null ? image.getUrl() : null);
//    }

//    public static final boolean equals(CharSequence a, CharSequence b) {
//        if (a == b) {
//            return true;
//        }
//        if (a == null || b == null || a.length() != b.length()) {
//            return false;
//        }
//        if ((a instanceof String) && (b instanceof String)) {
//            return Intrinsics.areEqual(a, b);
//        }
//        boolean z;
//        Iterator it = new IntRange(0, a.length() - 1).iterator();
//        while (it.hasNext()) {
//            int it2 = ((IntIterator) it).nextInt();
//            if (a.charAt(it2) != b.charAt(it2)) {
//                z = true;
//                continue;
//            } else {
//                z = false;
//                continue;
//            }
//            if (z) {
//                z = false;
//                break;
//            }
//        }
//        z = true;
//        return z;
//    }

    public static final boolean isLargerZero(Integer value) {
        return value != null && value.intValue() > 0;
    }

    public static final double getPrimitive(Double value) {
        return value != null ? value.doubleValue() : 0.0d;
    }

    public static final int getPrimitive(Integer value) {
        return value != null ? value.intValue() : 0;
    }

//    public static final String getPrimitive(Image image) {
//        return image != null ? image.getUrl() : null;
//    }

    public static final float getPrimitive(Float value) {
        return value != null ? value.floatValue() : 0.0f;
    }

    public static final boolean hasPosition(List<?> list, int position) {
        if (position < 0) {
            return false;
        }
        return position < (list != null ? list.size() : 0);
    }

    public static final int getListSize(List<?> list) {
        return list != null ? list.size() : 0;
    }

//    public static final <T, R> List<R> mapList(List<? extends T> list, Function1<? super T, ? extends R> mapFunction) {
//        Intrinsics.checkParameterIsNotNull(mapFunction, "mapFunction");
//        if (list == null) {
//            return null;
//        }
//        ArrayList mappedList = new ArrayList(list.size());
//        for (Object item$iv : list) {
//            mappedList.add(mapFunction.invoke(item$iv));
//        }
//        Collection collection = mappedList;
//        return mappedList;
//    }

    public static final boolean isLastPosition(List<?> list, int position) {
        if (list != null) {
            if ((!((Collection) list).isEmpty() ? 1 : null) != null && position == list.size() - 1) {
                return true;
            }
        }
        return false;
    }

//    public static final boolean containsKeys(Map<?, ?> map, Object... keys) {
//        Intrinsics.checkParameterIsNotNull(keys, "keys");
//        if (map == null) {
//            return false;
//        }
//        Object[] $receiver$iv = keys;
//        for (Object element$iv : $receiver$iv) {
//            if (!map.containsKey(element$iv)) {
//                return false;
//            }
//        }
//        return true;
//    }
}
