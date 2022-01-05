package com.joseph.common.kit.collections;

import java.util.Collection;
import java.util.Map;

/**
 * @author Joseph
 * @since 2022-01-02 23:19
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

}
