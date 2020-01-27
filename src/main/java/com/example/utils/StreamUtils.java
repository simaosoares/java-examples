package com.example.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;

import static java.util.stream.Collectors.toList;

/**
 *  Utilities class for {@link java.util.stream.Stream}.
 */
public class StreamUtils {

    /**
     * Filter collection to only one element
     * @param collection collection
     * @param predicate filter
     * @param notFoundException exception to be thrown in case the element is not found
     * @param <T> the object type of the collection
     * @return filtered element
     * @throws Throwable the exception to be thrown in case of element not found or more than one element found
     */
    public static <T> T filterToOnlyOneElement(List<T> collection, Predicate<T> predicate, Supplier<? extends Throwable> notFoundException) throws Throwable {
        return collection.stream()
                .filter(predicate)
                // more than one result cannot occur
                .reduce(toOnlyElementThrowing(IllegalStateException::new))
                // and one result must exist
                .orElseThrow(notFoundException);
    }

    /**
     * Helper method to ensure only one element is returned on <code>Stream</code> filter.
     * <code>IllegalArgumentException</code> is thrown if not only one element is found.
     *
     * More information can be found on <a href="https://blog.codefx.org/java/stream-findfirst-findany-reduce/"/>
     */
    public static <T> BinaryOperator<T> toOnlyElement() {
        return toOnlyElementThrowing(IllegalArgumentException::new);
    }

    /**
     * Helper method to ensure only one element is returned on <code>Stream</code> filter.
     * The provided exception is thrown if not only one element is found.
     *
     * More information can be found on <a href="https://blog.codefx.org/java/stream-findfirst-findany-reduce/"/>
     */
    public static <T, E extends RuntimeException> BinaryOperator<T> toOnlyElementThrowing(Supplier<E> exception) {
        return (element, otherElement) -> {
            throw exception.get();
        };
    }

    /**
     * Utility function to find distinct by class field.
     * More information can be found on <a href="https://howtodoinjava.com/java8/java-stream-distinct-examples/"/>
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * Helper function to map an Integer property from a collection of objects
     * @param collection collection
     * @param toIntFunction represents a function that produces an int-valued result.
     * @param <T> the type of the object collection
     * @return collection of integers
     */
    public static <T> List<Integer> mapToInt(Collection<T> collection, ToIntFunction<T> toIntFunction) {
        return collection.stream()
                .mapToInt(toIntFunction)
                .boxed()
                .collect(toList());
    }

    /**
     * Helper function to map an Integer property from a collection of objects
     * @param collection collection
     * @param toIntFunction represents a function that produces an int-valued result.
     * @param <T> the type of the object collection
     * @return array of integers
     */
    public static <T> Integer[] mapToIntArray(Collection<T> collection, ToIntFunction<T> toIntFunction) {
        return collection.stream()
                .mapToInt(toIntFunction)
                .boxed()
                .toArray(Integer[]::new);
    }
}
