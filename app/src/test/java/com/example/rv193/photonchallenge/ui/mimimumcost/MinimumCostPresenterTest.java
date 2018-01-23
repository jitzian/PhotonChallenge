package com.example.rv193.photonchallenge.ui.mimimumcost;

import com.example.rv193.photonlowestpath.dagger.ImmediateSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class MinimumCostPresenterTest {

    @Mock MinimumCostInterface.View view;
    private static final int[][] TEMP = new int[][]{{5,8,5,3,5}};
    private static final String CORRECT = "5 8 5 3 5";
    private static final String INCORRECT = "5 4 H\n8 M 7\n5 7 5";
    private final ImmediateSchedulerProvider schedulerProvider = new ImmediateSchedulerProvider();
    private MinimumCostPresenter presenter;

    @Before
    public void setupPresenterTest() {
        MockitoAnnotations.initMocks(this);

        presenter = new MinimumCostPresenter(schedulerProvider);
        presenter.onViewAttached(view);
    }

    @Test
    public void checkForValidGrid() {
        final TestObserver<int[][]> testObserver = new TestObserver<>();
        presenter.convertStringToGrid(CORRECT)
                .subscribe(testObserver);
        testObserver.assertSubscribed();
        testObserver.assertComplete();
    }

    @Test
    public void checkForValidGridGetError() {
        final TestObserver<int[][]> testObserver = new TestObserver<>();
        presenter.convertStringToGrid(INCORRECT)
                .subscribe(testObserver);
        testObserver.assertError(NumberFormatException.class);
    }

    @Test
    public void isGridValid() {
        assertTrue(presenter.isGridValid(TEMP));
    }

    @Test
    public void gridIsInvalid() {
        final int[][] incorrect = new int[0][0];
        assertFalse(presenter.isGridValid(incorrect));
    }

    @Test
    public void getBestPathAndSucceed() {
        assertNotNull(presenter.getTheBestPath(TEMP));
    }
}