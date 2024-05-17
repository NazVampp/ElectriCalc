package dev.lab.electricalc;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutPageFragment extends Fragment {

    TextView textView2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_page, container, false);

        // Initialize textView2
        textView2 = view.findViewById(R.id.detailed);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }
}
