package com.example.utils;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Java Collection utils unit tests
 */
public class CollectionUtilsTest {

    List<Integer> aList = Arrays.asList(1, 2, 3, 4, 5) ;
    List<Integer> bList = Arrays.asList(1, 3, 5, 7);

    @Test
    public void intersectionUsingJDK8() {
        Collection<?> objects = CollectionUtils.intersection(aList, bList);
        assertEquals(3, objects.size());
    }

    @Test
    public void intersectionUsingApacheCommonsCollections() {
        Collection<?> objects = org.apache.commons.collections4.ListUtils.intersection(aList, bList);
        assertEquals(3, objects.size());
    }
}