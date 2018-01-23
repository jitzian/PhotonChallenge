package com.example.rv193.photonchallenge.ui.minimumcost;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.example.rv193.photonchallenge.R;
import com.example.rv193.photonchallenge.dagger.BaseSchedulerProvider;
import com.example.rv193.photonchallenge.path.PathState;
import com.example.rv193.photonchallenge.ui.base.BasePresenter;
import com.example.rv193.photonchallenge.worker.Grid;
import com.example.rv193.photonchallenge.worker.GridVisitor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.DisposableObserver;

public class MinimumCostPresenter
        extends BasePresenter<MinimumCostInterface.View>
        implements MinimumCostInterface.Presenter {

    private static final String TAG = MinimumCostPresenter.class.getSimpleName();

    @Nullable
    private Grid mValidGrid;
    @Nullable private GridVisitor mVisitor;
    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @Inject
    public MinimumCostPresenter(@NonNull BaseSchedulerProvider schedulerProvider) {
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void checkForValidGrid(@NonNull String input) {
        getView().setLoading(true);
        mCompositeDisposable.add(
                convertStringToGrid(input)
                        .subscribeOn(mSchedulerProvider.getIoThread())
                        .observeOn(mSchedulerProvider.getMainThread())
                        .subscribeWith(new DisposableObserver<int[][]>() {
                            @Override
                            public void onNext(int[][] value) {
                                processGridResults(value);
                            }

                            @Override
                            public void onError(Throwable e) {
                                getView().setLoading(false);
                                getView().showMessage(R.string.error_invalid_matrix);
                            }

                            @Override
                            public void onComplete() {
                                getView().setLoading(false);
                            }
                        }));
    }

    @Override
    public void processGridResults(int[][] gridContents) {
        final PathState bestPath = getTheBestPath(gridContents);
        if (bestPath.isSuccessful()) {
            getView().showSuccessfulPathResults();
        } else {
            getView().showUnsuccessfulPathResult();
        }
        getView().showTotalCost(Integer.toString(bestPath.getTotalCost()));
        getView().showPathTaken(formatPath(bestPath));
        getView().showGridContents(validGridGetDelimitedString("\t"));
    }

    /**
     *  Now happening on a background thread to prevent frame skipping
     * @param inputString
     * @return
     */
    @NonNull
    public Observable<int[][]> convertStringToGrid(@NonNull final String inputString) {
        return Observable.create(new ObservableOnSubscribe<int[][]>() {
            @Override
            public void subscribe(ObservableEmitter<int[][]> emitter) throws Exception {
                if (!TextUtils.isEmpty(inputString)) {
                    final String[] rows = inputString.split("\n");
                    final String[] firstColumns = rows[0].split("\\s+");
                    int[][] output = new int[rows.length][firstColumns.length];

                    try {
                        for (int row = 0; row < rows.length; row++) {
                            final String[] columns = rows[row].split("\\s+");
                            for (int column = 0; column < columns.length; column++) {
                                if (column < output[0].length) {
                                    output[row][column] = Integer.valueOf(columns[column]);
                                }
                            }
                        }

                        emitter.onNext(output);
                    } catch (NumberFormatException nfe) {
                        Log.e(TAG, "exception: %s", nfe.getCause());
                        emitter.onError(nfe);
                    }
                } else {
                    emitter.onNext(new int[0][0]);
                }
                emitter.onComplete();
            }
        });
    }

    @Override
    public boolean isGridValid(int[][] tempGrid) {
        return tempGrid.length > 0 && tempGrid.length <= 10
                && tempGrid[0].length > 0 && tempGrid[0].length <= 100;
    }

    @NonNull
    public PathState getTheBestPath(int[][] contents) {
        mValidGrid = new Grid(contents);
        mVisitor = new GridVisitor(mValidGrid);
        return mVisitor.getBestPathForGrid();
    }

    @NonNull
    public String validGridGetDelimitedString(String s) {
        return mValidGrid.asDelimitedString(s);
    }

    @NonNull
    @Override
    public String formatPath(PathState path) {
        final StringBuilder builder = new StringBuilder();
        final List<Integer> rows = path.getRowsTraversed();
        for (int i = 0; i < rows.size(); i++) {
            builder.append(rows.get(i));
            if (i < rows.size() - 1) {
                builder.append("\t");
            }
        }
        return builder.toString();
    }

}