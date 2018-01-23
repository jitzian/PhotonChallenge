package com.example.rv193.photonchallenge.worker;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.rv193.photonchallenge.path.PathState;
import com.example.rv193.photonchallenge.path.PathStateCollector;
import com.example.rv193.photonchallenge.path.PathStateComparator;
import com.example.rv193.photonchallenge.path.RowVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GridVisitor {

    @NonNull
    private final Grid grid;
    @NonNull
    private PathStateComparator pathComparator;

    public GridVisitor(@Nullable Grid grid)
    {
        if (grid == null) {
            throw new IllegalArgumentException("A visitor requires a grid");
        }

        this.grid = grid;
        this.pathComparator = new PathStateComparator();
    }

    @NonNull
    public PathState getBestPathForGrid() {
        final List<PathState> allPaths = new ArrayList<>();

        for (int row = 1; row <= this.grid.getRowCount(); row++) {
            final RowVisitor visitor = new RowVisitor(row, this.grid, new PathStateCollector());
            allPaths.add(visitor.getBestPathForRow());
        }

        Collections.sort(allPaths, this.pathComparator);

        return allPaths.get(0);
    }
}
