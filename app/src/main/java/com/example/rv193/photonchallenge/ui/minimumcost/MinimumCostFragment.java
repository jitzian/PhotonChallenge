package com.example.rv193.photonchallenge.ui.minimumcost;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rv193.photonchallenge.R;
import com.example.rv193.photonchallenge.ui.base.BaseFragment;

public class MinimumCostFragment extends BaseFragment<MinimumCostInterface.Presenter>
        implements View.OnClickListener, MinimumCostInterface.View {

    private static final String TAG = MinimumCostFragment.class.getSimpleName();
    private Button mButton;
    private EditText mEditText;
    private TextView mResults;
    private TextView mResultsTotalCost;
    private TextView mResultsPathTaken;
    private TextView mGridTextView;
    private ProgressBar mProgressBar;

    public MinimumCostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_minimum_cost, container, false);

        mButton = v.findViewById(R.id.buttonfindmin);
        mButton.setOnClickListener(this);
        mEditText = v.findViewById(R.id.custom_grid_contents);
        mResults = v.findViewById(R.id.results_success);
        mResultsTotalCost = v.findViewById(R.id.results_total_cost);
        mResultsPathTaken = v.findViewById(R.id.results_path_taken);
        mGridTextView = v.findViewById(R.id.grid_contents);
        mProgressBar = v.findViewById(R.id.progress_bar);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mButton.setEnabled(!s.toString().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return v;
    }

    @Override
    public void onClick(View v) {
        final String input = mEditText.getText().toString();
        getPresenter().checkForValidGrid(input);
    }

    @Override
    public void showValidResults(boolean isValid) {
        Toast.makeText(getActivity(),
                isValid ? R.string.minimum_cost_grid_is_valid : R.string.minimum_cost_grid_is_not_valid,
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showSuccessfulPathResults() {
        mResults.setText(R.string.yes);
    }

    @Override
    public void showUnsuccessfulPathResult() {
        mResults.setText(R.string.no);
    }

    @Override
    public void showTotalCost(@NonNull String totalCost) {
        mResultsTotalCost.setText(totalCost);
    }

    @Override
    public void showPathTaken(@NonNull String formattedPath) {
        mResultsPathTaken.setText(formattedPath);
    }

    @Override
    public void showGridContents(@NonNull String contents) {
        mGridTextView.setText(contents);
    }

    /**
     * Returns a new instance of MinimumCostFragment
     * Any arguments should be passed in this static method
     *
     * @return
     */
    public static MinimumCostFragment newInstance() {
        return new MinimumCostFragment();
    }

    @Override
    public void setLoading(boolean isLoading) {
        mProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }
}