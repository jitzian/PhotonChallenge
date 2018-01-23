package com.example.rv193.photonchallenge.ui.minimumcost;

import android.support.annotation.NonNull;

import com.example.rv193.photonchallenge.path.PathState;
import com.example.rv193.photonchallenge.ui.base.BaseContract;

import io.reactivex.Observable;

/**
 * Contract for the presenter and view
 */
public interface MinimumCostInterface {

    interface View extends BaseContract.View {

        void showValidResults(boolean isValid);

        void showSuccessfulPathResults();

        void showUnsuccessfulPathResult();

        void showTotalCost(@NonNull String totalCost);

        void showPathTaken(@NonNull String formattedPath);

        void showGridContents(@NonNull String contents);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void checkForValidGrid(@NonNull String input);

        void processGridResults(int[][] gridContents);

        boolean isGridValid(int[][] tempGrid);

        @NonNull
        Observable<int[][]> convertStringToGrid(@NonNull String inputString);

        @NonNull
        PathState getTheBestPath(int[][] contents);

        @NonNull
        String validGridGetDelimitedString(String s);

        @NonNull
        String formatPath(PathState path);
    }
}
