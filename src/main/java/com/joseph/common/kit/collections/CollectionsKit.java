package com.joseph.common.kit.collections;

import java.util.*;

/**
 * @author Joseph
 */
public class CollectionsKit {

    
    public static boolean isEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return null == map || map.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static <T> List<T> arrayList(T... t) {
        if (null == t) {
            return new ArrayList<>(0);
        }
        return Arrays.asList(t);
    }

    public static <T> List<T> linkedList(T... t) {
        if (null == t) {
            return new LinkedList<>();
        }
        LinkedList<T> list = new LinkedList<>();
        for (T item : t) {
            list.addLast(item);
        }
        return list;
    }

}
