package com.example.utils;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Java Collections utils
 */
public class CollectionUtils {

    /**
     *  Returns a new list containing all elements that are contained in both given lists.
     * @param list1 the first list
     * @param list2 the second list
     * @return the intersection of those two lists
     */
    public static List<?> intersection(List<?> list1, List<?> list2){
        return list1.stream()
                .filter(list2::contains)
                .collect(toList());
    }
}
