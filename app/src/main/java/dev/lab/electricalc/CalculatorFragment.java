package dev.lab.electricalc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CalculatorFragment extends Fragment {

    EditText unitsInput, rebateInput;
    Button calculateButton, clearButton;
    TextView resultText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        unitsInput = view.findViewById(R.id.units_input);
        rebateInput = view.findViewById(R.id.rebate_input);
        calculateButton = view.findViewById(R.id.calculate_button);
        clearButton = view.findViewById(R.id.clear_button);
        resultText = view.findViewById(R.id.result_text);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBill();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputs();
            }
        });

        return view;
    }

    private void calculateBill() {
        try {
            int unitsUsed = Integer.parseInt(unitsInput.getText().toString());
            double rebatePercentage = Double.parseDouble(rebateInput.getText().toString());

            // Check if rebate percentage is within the allowed range (0% to 5%)
            if (rebatePercentage < 0 || rebatePercentage > 5) {
                // Show a popup message indicating invalid input
                showErrorDialog();
                return;
            }

            double totalCharge = calculateTotalCharge(unitsUsed);
            double finalCost = totalCharge - (totalCharge * rebatePercentage / 100);

            resultText.setText(getString(R.string.result_format, finalCost));

            // Switch to ResultFragment and pass data
            showResultFragment(unitsUsed, rebatePercentage, finalCost);

        } catch (NumberFormatException e) {
            showEmptyInputErrorDialog();
            return;
        }
    }

    private void showEmptyInputErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Please enter both units used and rebate percentage.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Rebate percentage must be between 0% and 5%.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private double calculateTotalCharge(int unitsUsed) {
        double totalCharge = 0;

        if (unitsUsed <= 200) {
            totalCharge = unitsUsed * 0.218;
        } else if (unitsUsed <= 300) {
            totalCharge = (200 * 0.218) + ((unitsUsed - 200) * 0.334);
        } else if (unitsUsed <= 600) {
            totalCharge = (200 * 0.218) + (100 * 0.334) + ((unitsUsed - 300) * 0.516);
        } else {
            totalCharge = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((unitsUsed - 600) * 0.546);
        }

        return totalCharge;
    }

    private void clearInputs() {
        unitsInput.setText("");
        rebateInput.setText("");
        resultText.setText("");
    }

    private void showResultFragment(int unitsUsed, double rebatePercentage, double finalCost) {
        ResultFragment resultFragment = new ResultFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("unitsUsed", unitsUsed);
        bundle.putDouble("rebatePercentage", rebatePercentage);
        bundle.putDouble("finalCost", finalCost);

        resultFragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, resultFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

