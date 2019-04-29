package com.example.cocomo_ii;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FP_ACTIVITY extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    public static double internal_logical_file_s = 7;
    public static double internal_logical_file_a = 10;
    public static double internal_logical_file_c = 15;

    public static final double external_logical_file_s = 5;
    public static final double external_logical_file_a = 7;
    public static final double external_logical_file_c = 10;

    public static final double external_input_s = 3;
    public static final double external_input_a = 4;
    public static final double external_input_c = 6;

    public static final double external_output_s = 4;
    public static final double external_output_a = 5;
    public static final double external_output_c = 7;

    public static final double external_inquiry_s = 3;
    public static final double external_inquiry_a = 4;
    public static final double external_inquiry_c = 6;

    public static final double lang_java = 38;
    public static final double lang_python = 1;
    public static final double lang_cplus = 53;

    //to be used for calculation
    public static double internal_logical_file_final;
    public static double external_logical_file_final;
    public static double external_input_final;
    public static double external_output_final;
    public static double external_inquiry_final;
    public static double lang_final;
    public static double complexity_factor;

    public static String input1;
    public static String input2;
    public static String input3;
    public static String input4;
    public static String input5;
    public static String DI;
    @BindView(R.id.average_comp_factor)
    EditText comp_factor;
    @BindView(R.id.internal_logical_file)
    EditText internal_log_file;
    @BindView(R.id.external_logical_file)
    EditText external_log_file;
    @BindView(R.id.external_input)
    EditText external_input;
    @BindView(R.id.external_output)
    EditText external_output;
    @BindView(R.id.external_inquiry)
    EditText external_inquiry;

    Spinner programming_language;
    Spinner project_level;
    Button nextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fp__activity);
        ButterKnife.bind(this);

        programming_language = findViewById(R.id.spinner_lan_factor);
        project_level = findViewById(R.id.spinner_level);
        nextActivity = findViewById(R.id.clicktoproceed);
        comp_factor = findViewById(R.id.average_comp_factor);
        internal_log_file = findViewById(R.id.internal_logical_file);
        external_log_file = findViewById(R.id.external_logical_file);
        external_input = findViewById(R.id.external_input);
        external_output = findViewById(R.id.external_output);
        external_inquiry = findViewById(R.id.external_inquiry);
        setupspinner();
        getTextAll();
        programming_language.setOnItemSelectedListener(this);
        project_level.setOnItemSelectedListener(this);
        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity();
            }
        });
    }


    private void setupspinner() {
        ArrayAdapter srSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_project_level, android.R.layout.simple_spinner_item);
        srSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        project_level.setAdapter(srSpinnerAdapter);
        ArrayAdapter srrSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_language, android.R.layout.simple_spinner_item);
        srrSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        programming_language.setAdapter(srrSpinnerAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        input1 = internal_log_file.getText().toString();
        input2 = external_log_file.getText().toString();
        input3 = external_input.getText().toString();
        input4 = external_output.getText().toString();
        input5 = external_inquiry.getText().toString();
        DI = comp_factor.getText().toString();
        if (input1.isEmpty() || input2.isEmpty()) {
        } else {

            complexity_factor = Double.parseDouble(DI);
            Double inputd1 = Double.parseDouble(input1);
            Double inputd2 = Double.parseDouble(input2);
            Double inputd3 = Double.parseDouble(input3);
            Double inputd4 = Double.parseDouble(input4);
            Double inputd5 = Double.parseDouble(input5);

            if (parent.getId() == R.id.spinner_lan_factor) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.python))) {
                        // Toast.makeText(FP_ACTIVITY.this, "external logical file" + input2, Toast.LENGTH_LONG).show();
                        lang_final = lang_python;
                    }
                    if (selection.equals(getString(R.string.cplus))) {
                        lang_final = lang_cplus;
                        //  Toast.makeText(FP_ACTIVITY.this, "internal inquiry file" + input5, Toast.LENGTH_LONG).show();

                    }
                    if (selection.equals(getString(R.string.java))) {
                        lang_final = lang_java;
                        // Toast.makeText(FP_ACTIVITY.this, "external input file" + input3, Toast.LENGTH_LONG).show();

                    }
                }
            }
            if (parent.getId() == R.id.spinner_level) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.cs_average))) {

                        internal_logical_file_final = inputd1 * internal_logical_file_a;
                        external_logical_file_final = inputd2 * external_logical_file_a;
                        external_input_final = inputd3 * external_input_a;
                        external_output_final = inputd4 * external_output_a;
                        external_inquiry_final = inputd5 * external_inquiry_a;
                        // Toast.makeText(FP_ACTIVITY.this, "DI file" + DI, Toast.LENGTH_LONG).show();


                    } else if (selection.equals(getString(R.string.cs_complex))) {
                        internal_logical_file_final = inputd1 * internal_logical_file_c;
                        //  Toast.makeText(FP_ACTIVITY.this, "internal logical file" + input1, Toast.LENGTH_LONG).show();
                        external_logical_file_final = inputd2 * external_logical_file_c;
                        external_input_final = inputd3 * external_input_c;
                        external_output_final = inputd4 * external_output_c;
                        external_inquiry_final = inputd5 * external_inquiry_c;

                    } else {
                        internal_logical_file_final = inputd1 * internal_logical_file_s;
                        external_logical_file_final = inputd2 * external_logical_file_s;
                        external_input_final = inputd3 * external_input_s;
                        // Toast.makeText(FP_ACTIVITY.this, "internal logical file final" + String.valueOf(internal_logical_file_final), Toast.LENGTH_LONG).show();
                        external_output_final = inputd4 * external_output_s;
                        external_inquiry_final = inputd5 * external_inquiry_s;
                    }
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (parent.getId() == R.id.spinner_lan_factor) {
            lang_final = lang_java;
            Toast.makeText(FP_ACTIVITY.this, String.valueOf(lang_final), Toast.LENGTH_LONG).show();


        }
        if (parent.getId() == R.id.spinner_level) {
            input1 = internal_log_file.getText().toString();
            input2 = external_log_file.getText().toString();
            input3 = external_input.getText().toString();
            input4 = external_output.getText().toString();
            input5 = external_inquiry.getText().toString();
            DI = comp_factor.getText().toString();
            Double inputd1 = Double.parseDouble(input1);
            Double inputd2 = Double.parseDouble(input2);
            Double inputd3 = Double.parseDouble(input3);
            Double inputd4 = Double.parseDouble(input4);
            Double inputd5 = Double.parseDouble(input5);
            internal_logical_file_final = inputd1 * internal_logical_file_s;
            external_logical_file_final = inputd2 * external_logical_file_s;
            external_input_final = inputd3 * external_input_s;
            external_output_final = inputd4 * external_output_s;
            external_inquiry_final = inputd5 * external_inquiry_s;
        }
    }

    private void loadActivity() {
        getTextAll();
        if ((input1.isEmpty() || input2.isEmpty() || input3.isEmpty() || input4.isEmpty() || input5.isEmpty() || DI.isEmpty())) {
            Toast.makeText(FP_ACTIVITY.this, "Please Fill the Required Details", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(FP_ACTIVITY.this, SecondFp.class));

        }
    }

    public void getTextAll() {
        input1 = internal_log_file.getText().toString();
        input2 = external_log_file.getText().toString();
        input3 = external_input.getText().toString();
        input4 = external_output.getText().toString();
        input5 = external_inquiry.getText().toString();
        DI = comp_factor.getText().toString();
    }

}
