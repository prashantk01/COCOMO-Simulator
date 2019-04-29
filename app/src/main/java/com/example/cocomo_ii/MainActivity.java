package com.example.cocomo_ii;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int value = item.getItemId();

            switch (value) {
                case R.id.organic:
                    calculateEstimate(value);
                    return true;
                case R.id.semidetached:
                    calculateEstimate(value);
                    return true;
                case R.id.Embedded:
                    calculateEstimate(value);
                    return true;
            }
            return false;
        }

    };


    @BindView(R.id.computeButton)
    Button ComputeEstimateDevelopment;

    @BindView(R.id.size_editText)
    EditText size;

    @BindView(R.id.display_estimate)
    TextView mDisplayEstimate;

    @BindView(R.id.tdev)
    TextView mDisplayDevEstimate;

    @BindView(R.id.display_estimate_inter)
    TextView mDisplayEstimate_inter;

    @BindView(R.id.tdev_inter)
    TextView mDisplayDevEstimate_inter;

    @BindView(R.id.cost)
    EditText costpm;

    @BindView(R.id.cost_basic)
    TextView costbasic;

    @BindView(R.id.cost_intermediate)
    TextView costinter;

    public static final double a_ORGANIC = 2.4;
    public static final double a_EMBEDDED = 3.6;
    public static final double a_DETACHED = 3.0;

    public static final double b_ORGANIC = 1.05;
    public static final double b_EMBEDDED = 1.20;
    public static final double b_DETACHED = 1.12;

    public static final double tDev_constant = 2.5;

    public static final double t_ORGANIC = 0.38;
    public static final double t_EMBEDDED = 0.32;
    public static final double t_DETACHED = 0.35;

    private double m_eOrganic;
    private double m_eDetached;
    private double m_eEmbedded;


    private double m_eOrganic_inter;
    private double m_eDetached_inter;
    private double m_eEmbedded_inter;


    private double t_eOrganic;
    private double t_eDetached;
    private double t_eEmbedded;

    private double t_eOrganic_inter;
    private double t_eDetached_inter;
    private double t_eEmbedded_inter;

    private double mKLOC;
    private double EAF;
    private double cost_basic;
    private double cost_intermediate;

    Spinner spinner_1;
    Spinner spinner_2;
    Spinner spinner_3;
    Spinner spinner_4;
    Spinner spinner_5;
    Spinner spinner_6;
    Spinner spinner_7;
    Spinner spinner_8;
    Spinner spinner_9;
    Spinner spinner_10;
    Spinner spinner_11;
    Spinner spinner_12;
    Spinner spinner_13;
    Spinner spinner_14;
    Spinner spinner_15;


    //cost driver
    public static final double required_Software_Reliability_h = 1.15;
    public static final double size_of_Application_Database_h = 1.08;
    public static final double complexity_of_The_Product_h = 1.15;

    public static final double runtime_Performance_Constraints_h = 1.11;
    public static final double memory_Constraints_h = 1.06;
    public static final double volatility_of_the_virtual_machine_environment_h = 1.15;
    public static final double required_turnabout_time_h = 1.07;

    public static final double analyst_capability_h = 0.86;
    public static final double applications_experience_h = 0.91;
    public static final double software_engineer_capability_h = 0.86;
    public static final double virtual_machine_experience_h = 0.90;
    public static final double programming_language_experience_h = 0.95;

    public static final double application_of_software_engineering_methods_h = 0.91;
    public static final double use_of_software_tools_h = 0.91;
    public static final double required_development_schedule_h = 1.04;


    // cost drivers used
    public static double cs_1;
    public static double cs_2;
    public static double cs_3;
    public static double cs_4;
    public static double cs_5;
    public static double cs_6;
    public static double cs_7;
    public static double cs_8;
    public static double cs_9;
    public static double cs_10;
    public static double cs_11;
    public static double cs_12;
    public static double cs_13;
    public static double cs_14;
    public static double cs_15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ButterKnife.bind(this);

        spinner_1 = findViewById(R.id.spinner_RSR);
        spinner_2 = findViewById(R.id.spinner_SAD);
        spinner_3 = findViewById(R.id.spinner_CTP);
        spinner_4 = findViewById(R.id.spinner_RPC);
        spinner_5 = findViewById(R.id.spinner_MC);
        spinner_6 = findViewById(R.id.spinner_VOVME);
        spinner_7 = findViewById(R.id.spinner_RTT);
        spinner_8 = findViewById(R.id.spinner_ACAP);
        spinner_9 = findViewById(R.id.spinner_AEXP);
        spinner_10 = findViewById(R.id.spinner_SEC);
        spinner_11 = findViewById(R.id.spinner_VMEXP);
        spinner_12 = findViewById(R.id.spinner_PLEXP);
        spinner_13 = findViewById(R.id.spinner_ASEM);
        spinner_14 = findViewById(R.id.spinner_USTL);
        spinner_15 = findViewById(R.id.spinner_RDS);


        setupspinner();
        spinner_1.setOnItemSelectedListener(this);
        spinner_2.setOnItemSelectedListener(this);
        spinner_3.setOnItemSelectedListener(this);
        spinner_4.setOnItemSelectedListener(this);
        spinner_5.setOnItemSelectedListener(this);
        spinner_6.setOnItemSelectedListener(this);
        spinner_7.setOnItemSelectedListener(this);
        spinner_8.setOnItemSelectedListener(this);
        spinner_9.setOnItemSelectedListener(this);
        spinner_10.setOnItemSelectedListener(this);
        spinner_11.setOnItemSelectedListener(this);
        spinner_12.setOnItemSelectedListener(this);
        spinner_13.setOnItemSelectedListener(this);
        spinner_14.setOnItemSelectedListener(this);
        spinner_15.setOnItemSelectedListener(this);

        ComputeEstimateDevelopment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = navigation.getSelectedItemId();
                calculateEstimate(value);
                //Toast.makeText(MainActivity.this,"Scrolldown to view result",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setupspinner() {
        ArrayAdapter rsrSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_cost_driver, android.R.layout.simple_spinner_item);
        rsrSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_1.setAdapter(rsrSpinnerAdapter);
        spinner_2.setAdapter(rsrSpinnerAdapter);
        spinner_3.setAdapter(rsrSpinnerAdapter);
        spinner_4.setAdapter(rsrSpinnerAdapter);
        spinner_5.setAdapter(rsrSpinnerAdapter);
        spinner_6.setAdapter(rsrSpinnerAdapter);
        spinner_7.setAdapter(rsrSpinnerAdapter);
        spinner_8.setAdapter(rsrSpinnerAdapter);
        spinner_9.setAdapter(rsrSpinnerAdapter);
        spinner_10.setAdapter(rsrSpinnerAdapter);
        spinner_11.setAdapter(rsrSpinnerAdapter);
        spinner_12.setAdapter(rsrSpinnerAdapter);
        spinner_13.setAdapter(rsrSpinnerAdapter);
        spinner_14.setAdapter(rsrSpinnerAdapter);
        spinner_15.setAdapter(rsrSpinnerAdapter);
    }


    public void calculateEstimate(int value) {
        String input = size.getText().toString();
        String costinput = costpm.getText().toString();
        EAF = cs_1 * cs_2 * cs_3 * cs_4 * cs_5 * cs_6 * cs_7 * cs_8 * cs_9 * cs_10 * cs_11 * cs_12 * cs_13 * cs_14 * cs_15;
        if (input.isEmpty() || costinput.isEmpty()) {
            Toast.makeText(this, "value for size and cost must be entered", Toast.LENGTH_SHORT).show();
        } else {
            switch (value) {
                case R.id.organic:
                    mKLOC = Double.parseDouble(input);
                    m_eOrganic = a_ORGANIC * Math.pow(mKLOC, b_ORGANIC);
                    m_eOrganic_inter = a_ORGANIC * Math.pow(mKLOC, b_ORGANIC) * EAF;
                    t_eOrganic = tDev_constant * Math.pow(m_eOrganic, t_ORGANIC);
                    t_eOrganic_inter = tDev_constant * Math.pow(m_eOrganic_inter, t_ORGANIC);


                    String Eoutput = String.valueOf(m_eOrganic);
                    mDisplayEstimate.setText(Eoutput.substring(0, 5));
                    //display dev
                    String toutput = String.valueOf(t_eOrganic);
                    mDisplayDevEstimate.setText(toutput.substring(0, 5));

                    String Eoutput_inter = String.valueOf(m_eOrganic_inter);
                    mDisplayEstimate_inter.setText(Eoutput_inter.substring(0, 5));
                    //display dev
                    String toutput_inter = String.valueOf(t_eOrganic_inter);
                    mDisplayDevEstimate_inter.setText(toutput_inter.substring(0, 5));

                    Double costinpm = Double.valueOf(costinput);
                    cost_basic = costinpm * t_eOrganic;
                    cost_intermediate = costinpm * t_eOrganic_inter;
                    String costtoPrintBasic = String.valueOf(cost_basic);
                    String costtoPrintInter = String.valueOf(cost_intermediate);
                    costbasic.setText(costtoPrintBasic.substring(0, 7));
                    costinter.setText(costtoPrintInter.substring(0, 7));

                    break;
                case R.id.semidetached:

                    mKLOC = Double.valueOf(input);
                    m_eDetached = a_DETACHED * Math.pow(mKLOC, b_DETACHED);
                    t_eDetached = tDev_constant * Math.pow(m_eDetached, t_DETACHED);

                    m_eDetached_inter = a_DETACHED * Math.pow(mKLOC, b_DETACHED) * EAF;
                    t_eDetached_inter = tDev_constant * Math.pow(m_eDetached_inter, t_DETACHED);


                    String doutput = String.valueOf(m_eDetached);
                    mDisplayEstimate.setText(doutput.substring(0, 5));
                    //display dev
                    String tdoutput = String.valueOf(t_eDetached);
                    mDisplayDevEstimate.setText(tdoutput.substring(0, 5));

                    String doutput_inter = String.valueOf(m_eDetached_inter);
                    mDisplayEstimate_inter.setText(doutput_inter.substring(0, 5));
                    //display dev
                    String tdoutput_inter = String.valueOf(t_eDetached_inter);
                    mDisplayDevEstimate_inter.setText(tdoutput_inter.substring(0, 5));
                    Double costinpmd = Double.valueOf(costinput);
                    cost_basic = costinpmd * t_eDetached;
                    cost_intermediate = costinpmd * t_eDetached_inter;
                    String costtoPrintBasicd = String.valueOf(cost_basic);
                    String costtoPrintInterd = String.valueOf(cost_intermediate);
                    costbasic.setText(costtoPrintBasicd.substring(0, 7));
                    costinter.setText(costtoPrintInterd.substring(0, 7));


                    break;
                case R.id.Embedded:
                    mKLOC = Double.valueOf(input);
                    m_eEmbedded = a_EMBEDDED * Math.pow(mKLOC, b_EMBEDDED);
                    t_eEmbedded = tDev_constant * Math.pow(m_eEmbedded, t_EMBEDDED);

                    m_eEmbedded_inter = a_EMBEDDED * Math.pow(mKLOC, b_EMBEDDED) * EAF;
                    t_eEmbedded_inter = tDev_constant * Math.pow(m_eEmbedded_inter, t_EMBEDDED);


                    String Emoutput = String.valueOf(m_eEmbedded);
                    mDisplayEstimate.setText(Emoutput.substring(0, 5));
                    //display dev
                    String Emdoutput = String.valueOf(t_eEmbedded);
                    mDisplayDevEstimate.setText(Emdoutput.substring(0, 5));


                    String Emoutput_inter = String.valueOf(m_eEmbedded_inter);
                    mDisplayEstimate_inter.setText(Emoutput_inter.substring(0, 5));
                    //display dev
                    String Emdoutput_inter = String.valueOf(t_eEmbedded_inter);
                    mDisplayDevEstimate_inter.setText(Emdoutput_inter.substring(0, 5));

                    Double costinpmdE = Double.valueOf(costinput);
                    cost_basic = costinpmdE * t_eEmbedded;
                    cost_intermediate = costinpmdE * t_eEmbedded_inter;
                    String costtoPrintBasicdE = String.valueOf(cost_basic);
                    String costtoPrintInterdE = String.valueOf(cost_intermediate);
                    costbasic.setText(costtoPrintBasicdE.substring(0, 7));
                    costinter.setText(costtoPrintInterdE.substring(0, 7));
                    break;


            }

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner_RSR) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_1 = 0.75;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_1 = 0.88;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_1 = required_Software_Reliability_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_1 = 1.40;
                } else {
                    cs_1 = 1;
                }
            }
        }
        if (parent.getId() == R.id.spinner_SAD) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_2 = 0.75;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_2 = 0.94;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_2 = size_of_Application_Database_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_2 = 1.16;
                } else {
                    cs_2 = 1;
                }

            }
        }
        if (parent.getId() == R.id.spinner_CTP) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_3 = 0.70;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_3 = 0.85;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_3 = complexity_of_The_Product_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_3 = 1.30;
                } else {
                    cs_3 = 1;
                }

            }
        }
        if (parent.getId() == R.id.spinner_RPC) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_4 = 1;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_4 = 1;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_4 = runtime_Performance_Constraints_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_4 = 1.30;
                } else {
                    cs_4 = 1;
                }

            }
        }
        if (parent.getId() == R.id.spinner_MC) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_5 = 1.0;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_5 = 1.0;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_5 = memory_Constraints_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_5 = 1.21;
                } else {
                    cs_5 = 1;
                }

            }
        }
        if (parent.getId() == R.id.spinner_VOVME) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_6 = 1.0;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_6 = 0.87;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_6 = volatility_of_the_virtual_machine_environment_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_6 = 1.30;
                } else {
                    cs_6 = 1;
                }

            }
        }
        if (parent.getId() == R.id.spinner_RTT) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_7 = 1.0;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_7 = 0.94;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_7 = required_turnabout_time_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_7 = 1.15;
                } else {
                    cs_7 = 1;
                }

            }
        }
        if (parent.getId() == R.id.spinner_ACAP) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_8 = 1.46;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_8 = 1.19;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_8 = analyst_capability_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_8 = 0.71;
                } else {
                    cs_8 = 1;
                }

            }
        }
        if (parent.getId() == R.id.spinner_AEXP) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_9 = 1.29;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_9 = 1.13;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_9 = applications_experience_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_9 = 0.82;
                } else {
                    cs_9 = 1;
                }

            }
        }
        if (parent.getId() == R.id.spinner_SEC) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_10 = 1.42;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_10 = 1.17;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_10 = software_engineer_capability_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_10 = 0.70;
                } else {
                    cs_10 = 1;
                }

            }
        }
        if (parent.getId() == R.id.spinner_VMEXP) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_11 = 1.21;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_11 = 1.1;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_11 = virtual_machine_experience_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_11 = 1.0;
                } else {
                    cs_11 = 1;
                }

            }
        }
        if (parent.getId() == R.id.spinner_PLEXP) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_12 = 1.14;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_12 = 1.07;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_12 = programming_language_experience_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_12 = 1.0;
                } else {
                    cs_12 = 1;
                }

            }
        }
        if (parent.getId() == R.id.spinner_ASEM) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_13 = 1.24;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_13 = 1.10;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_13 = application_of_software_engineering_methods_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_13 = 0.82;
                } else {
                    cs_13 = 1;
                }

            }
        }
        if (parent.getId() == R.id.spinner_USTL) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_14 = 1.24;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_14 = 1.10;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_14 = use_of_software_tools_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_14 = 0.83;
                } else {
                    cs_14 = 1;
                }

            }
        }
        if (parent.getId() == R.id.spinner_RDS) {
            String selection = (String) parent.getItemAtPosition(position);
            if (!TextUtils.isEmpty(selection)) {
                if (selection.equals(getString(R.string.cs_verylow))) {
                    cs_15 = 1.23;
                } else if (selection.equals(getString(R.string.cs_low))) {
                    cs_15 = 1.08;
                } else if (selection.equals(getString(R.string.cs_high))) {
                    cs_15 = required_development_schedule_h;
                } else if (selection.equals(getString(R.string.cs_veryhigh))) {
                    cs_15 = 1.10;
                } else {
                    cs_15 = 1;
                }

            }
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (parent.getId() == R.id.spinner_RSR) {
            cs_1 = 1;
        }
        if (parent.getId() == R.id.spinner_SAD) {
            cs_2 = 1;
        }
        if (parent.getId() == R.id.spinner_CTP) {
            cs_3 = 1;
        }
        if (parent.getId() == R.id.spinner_RPC) {
            cs_4 = 1;
        }
        if (parent.getId() == R.id.spinner_MC) {
            cs_5 = 1;
        }
        if (parent.getId() == R.id.spinner_VOVME) {
            cs_6 = 1;
        }
        if (parent.getId() == R.id.spinner_RTT) {
            cs_7 = 1;
        }
        if (parent.getId() == R.id.spinner_ACAP) {
            cs_8 = 1;
        }
        if (parent.getId() == R.id.spinner_AEXP) {
            cs_9 = 1;
        }
        if (parent.getId() == R.id.spinner_SEC) {
            cs_10 = 1;
        }
        if (parent.getId() == R.id.spinner_VMEXP) {
            cs_11 = 1;
        }
        if (parent.getId() == R.id.spinner_PLEXP) {
            cs_12 = 1;
        }
        if (parent.getId() == R.id.spinner_ASEM) {
            cs_13 = 1;
        }
        if (parent.getId() == R.id.spinner_USTL) {
            cs_14 = 1;
        }
        if (parent.getId() == R.id.spinner_RDS) {
            cs_15 = 1;
        }
    }
}
