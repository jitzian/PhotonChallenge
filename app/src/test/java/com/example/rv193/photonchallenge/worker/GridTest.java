package com.example.rv193.photonchallenge.worker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by rv193 on 1/25/2018.
 */
public class GridTest {

    private static final int[][]TEMP = new int[][]{{5,8,5,3,5}};

    @Mock
    Grid grid;

    private static final String CORRECT = "5    8   5   3   5";
    private static final String DELIMITER = "\t";

    @Before
    public void setupGridTest(){
        MockitoAnnotations.initMocks(this);

        grid = new Grid(TEMP);
    }

    @Test
    public void getValueForRowAndColumn() throws Exception {
        assertEquals(5, TEMP[(TEMP.length-1)][(TEMP[0].length-1)]);
    }

    @Test
    public void getRowCount() throws Exception {
        assertEquals(1, TEMP.length);
    }

    @Test
    public void getColumnCount() throws Exception {
        assertEquals(5, TEMP[0].length);
    }

    @Test
    public void getRowsAdjacentToNotNull() throws Exception {
        assertNotNull(grid.getRowsAdjacentTo(TEMP[0][0]));
    }

    @Test
    public void getRowsAdjacentToNull()throws Exception{
        assertNotNull(grid.getRowsAdjacentTo(TEMP[0][0]));
    }
}