package com.example.service;

import com.example.domain.Detail;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DetailMappingUtilsTest {

    private List<Detail> input;
    private List<Detail> output;

    @Before
    public void setUp() {
        input = new ArrayList<>();
        input.add(new Detail(1, "Line1 updated"));
        input.add(new Detail("New line"));

        output = new ArrayList<>();
        output.add(new Detail(1, "Line1"));
        output.add(new Detail(2, "Line to be removed"));
    }

    @Test
    public void mapDetails() {
        DetailMappingUtils.mapDetails(input, output);
        assertMapDetails();
    }

    @Test
    public void mapDetailsById() {
        DetailMappingUtils.mapDetailsById(input, output);
        assertMapDetails();
    }

    private void assertMapDetails() {
        assertEquals(2, output.size());
        assertEquals("Line1 updated", output.get(0).getName());
        assertEquals("New line", output.get(1).getName());
    }

}
