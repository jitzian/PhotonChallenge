package com.example.rv193.photonchallenge.worker;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grid {

    private final int[][] values;

    public Grid(int[][] values)
    {
        //Log.d("Grid", "The length is " + values.length);
        if ((values.length < 1) || (values.length > 10))
            throw new IllegalArgumentException("Between one and ten rows of values are expected");
        if ((values[0].length < 1) || (values[0].length > 100)) {
            throw new IllegalArgumentException("Between five and one hundred columns of values are expected");
        }

        this.values = values;
    }

    public int getValueForRowAndColumn(int row, int column) {
        return this.values[(row - 1)][(column - 1)];
    }

    public int getRowCount() {
        return this.values.length;
    }

    public int getColumnCount() {
        return this.values[0].length;
    }

    @NonNull
    public List<Integer> getRowsAdjacentTo(int rowNumber) {
        final Set adjacentRows = new HashSet();

        if (isValidRowNumber(rowNumber)) {
            adjacentRows.add(Integer.valueOf(rowNumber));
            adjacentRows.add(Integer.valueOf(getRowAbove(rowNumber)));
            adjacentRows.add(Integer.valueOf(getRowBelow(rowNumber)));
        }

        return new ArrayList(adjacentRows);
    }

    @NonNull
    public String asDelimitedString(String delimiter) {
        final StringBuilder builder = new StringBuilder();

        for (int row = 0; row < this.values.length; row++) {
            for (int column = 0; column < this.values[row].length; column++) {
                builder.append(this.values[row][column]);
                if (column < this.values[row].length - 1) {
                    builder.append(delimiter);
                }
            }
            if (row < this.values.length - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }

    @VisibleForTesting
    boolean isValidRowNumber(int rowNumber) {
        return (rowNumber > 0) && (rowNumber <= this.values.length);
    }

    @VisibleForTesting
    int getRowAbove(int rowNumber) {
        int potentialRowNumber = rowNumber - 1;
        return potentialRowNumber < 1 ? this.values.length : potentialRowNumber;
    }

    @VisibleForTesting
    int getRowBelow(int rowNumber) {
        int potentialRowNumber = rowNumber + 1;
        return potentialRowNumber > this.values.length ? 1 : potentialRowNumber;
    }
}