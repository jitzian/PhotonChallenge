package com.example.rv193.photonchallenge.worker;

        import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GridTest {

    private static final int[][] TEMP = new int[][]{{5, 8, 5, 3, 5}};

    private Grid grid;

    @Before
    public void setupGridTest() {
        grid = new Grid(TEMP);
    }

    @Test
    public void checkValidRowNumber() {
        assertTrue(grid.isValidRowNumber(1));
    }

    @Test
    public void falseOnZeroRow() {
        assertFalse(grid.isValidRowNumber(0));
    }

    @Test
    public void getValueForRowAndColumn() throws Exception {
        assertEquals(5, TEMP[(TEMP.length - 1)][(TEMP[0].length - 1)]);
    }

    @Test
    public void getRowAboveIsLength() {
        assertEquals(TEMP.length, grid.getRowAbove(1));
    }

    @Test
    public void getRowBelowIsLength() {
        assertEquals(TEMP.length, grid.getRowBelow(5));
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
    public void getRowsAdjacentToNull() throws Exception {
        assertNotNull(grid.getRowsAdjacentTo(TEMP[0][0]));
    }
}