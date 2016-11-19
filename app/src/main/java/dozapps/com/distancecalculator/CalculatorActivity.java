package dozapps.com.distancecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    private Spinner fromSpinner, toSpinner;
    private EditText fromEditText, toEditText;

    private ArrayAdapter<String> fromAdapter, toAdapter;

    private String[] unitsArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initializeVariables();
        setupSpinners();
        textChanged();
    }

    private void initializeVariables() {

        fromSpinner = (Spinner) findViewById(R.id.fromSpinner);
        toSpinner = (Spinner) findViewById(R.id.toSpinner);
        fromEditText = (EditText) findViewById(R.id.fromEditText);
        toEditText = (EditText) findViewById(R.id.toEditText);

        toEditText.setInputType(0x00000000);

        unitsArray = new String[]{"Feet", "Mile"};

    }

    private void setupSpinners() {
        fromAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unitsArray);
        toAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unitsArray);

        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(fromAdapter);
        toSpinner.setAdapter(toAdapter);


    }

    private void textChanged() {

        fromEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                convertUnits(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void convertUnits(CharSequence s) {
        if (fromSpinner.getSelectedItem().toString() == toSpinner.getSelectedItem().toString()) {
            toEditText.setText(s);
        } else if (fromSpinner.getSelectedItem().toString() == unitsArray[1] &&
                toSpinner.getSelectedItem().toString() == unitsArray[0]) {

            Float value = Float.valueOf(s.toString());
            value *= 5280;

            toEditText.setText(value.toString());

        }
    }


}
