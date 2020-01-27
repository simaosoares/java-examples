package com.example.utils;

import com.example.domain.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for {@link StreamUtils} class
 *
 * @author simon
 */
public class StreamUtilsTest {

    final List<User> users = Arrays.asList(new User(1, "Liu Kang"), new User(2, "Kabal"));

    @Test(expected = IllegalStateException.class)
    public void filterToOnlyOneElement_shouldTrowExceptionIfElementNotFound() throws Throwable {
        StreamUtils.filterToOnlyOneElement(users, u -> u.getName().equals("Sektor"), IllegalStateException::new);
    }

    @Test
    public void filterToOnlyOneElement_shouldReturnOneElement() throws Throwable {
        User user = StreamUtils.filterToOnlyOneElement(users, u -> u.getName().equals("Kabal"), IllegalStateException::new);

        assertNotNull(user);
    }

    @Test
    public void distinctByKey_shouldReturnDistinctValues() {
        List<User> users = new ArrayList<User>(){{
            add(new User("Liu Kang"));
            add(new User("Liu Kang"));
            add(new User("Kabal"));
            add(new User("Kabal"));
            add(new User("Kabal"));
        }};

        final List<User> filtered = users.stream()
                .filter(StreamUtils.distinctByKey(User::getName))
                .collect(Collectors.toList());

        assertEquals(2, filtered.size());
    }

    @Test
    public void mapToInt_shouldReturnCollectionOfIntegers() {

        // act
        final List<Integer> ids = StreamUtils.mapToInt(users, User::getId);

        // assert
        assertEquals(2, ids.size());
        assertEquals(0, ids.get(0).compareTo(1));
        assertEquals(0, ids.get(1).compareTo(2));
    }

    @Test
    public void mapToIntArray_shouldReturnArrayOfIntegers() {
        // act
        final Integer[] ids = StreamUtils.mapToIntArray(users, User::getId);

        // assert
        assertEquals(2, ids.length);
        assertEquals(1, ids[0].intValue());
        assertEquals(2, ids[1].intValue());
    }
}
