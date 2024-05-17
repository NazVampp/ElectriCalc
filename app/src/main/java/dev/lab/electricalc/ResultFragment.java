package dev.lab.electricalc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {

    TextView unitsUsedText, rebatePercentageText, finalCostText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        unitsUsedText = view.findViewById(R.id.units_used_text);
        rebatePercentageText = view.findViewById(R.id.rebate_percentage_text);
        finalCostText = view.findViewById(R.id.final_cost_text);

        // Get data from the bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            int unitsUsed = bundle.getInt("unitsUsed");
            double rebatePercentage = bundle.getDouble("rebatePercentage");
            double finalCost = bundle.getDouble("finalCost");

            // Set data to the TextViews
            unitsUsedText.setText(getString(R.string.units_used_format, unitsUsed));
            rebatePercentageText.setText(getString(R.string.rebate_percentage_format, rebatePercentage));
            finalCostText.setText(getString(R.string.result_format, finalCost));
        }

        return view;
    }
}

