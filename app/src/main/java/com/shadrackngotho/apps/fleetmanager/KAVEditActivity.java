package com.shadrackngotho.apps.fleetmanager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by SHADRACK NGOTHO on 9/26/2016.
 */
public class KAVEditActivity extends AppCompatActivity implements View.OnClickListener {
    private kbwDBHelper dbHelper;
    EditText dateEditText;
    EditText journeyEditText;
    EditText fuelEditText;
    EditText paymentsEditText;
    EditText allowancesEditText;
    EditText miscellaneousEditText;

    private int mYear, mMonth, mDay;

    Button saveButton;
    LinearLayout buttonLayout;
    Button editButton, deleteButton;

    int tripID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tripID = getIntent().getIntExtra(MainActivity.KEY_EXTRA_CONTACT_ID, 0);

        setContentView(R.layout.kbw_edit);
        dateEditText = (EditText) findViewById(R.id.dateText);
        dateEditText.setInputType(InputType.TYPE_NULL);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        dateEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    showDialog();
                }
            }
        });

        journeyEditText = (EditText) findViewById(R.id.journeyText);
        fuelEditText = (EditText) findViewById(R.id.fuelText);
        paymentsEditText = (EditText) findViewById(R.id.paymentsText);
        allowancesEditText = (EditText) findViewById(R.id.allowancesText);
        miscellaneousEditText = (EditText) findViewById(R.id.miscellaneousText);

        saveButton = (Button) findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(this);
        buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
        editButton = (Button) findViewById(R.id.editButton);
        editButton.setOnClickListener(this);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);

        dbHelper = new kbwDBHelper(this);

        if(tripID > 0) {
            saveButton.setVisibility(View.GONE);
            buttonLayout.setVisibility(View.VISIBLE);

            Cursor rs = dbHelper.getTrip(tripID);
            rs.moveToFirst();

            String tripDate = rs.getString(rs.getColumnIndex(kbwDBHelper.TRIPS_COLUMN_DATE));
            String tripJourney = rs.getString(rs.getColumnIndex(kbwDBHelper.TRIPS_COLUMN_JOURNEY));
            int tripFuel = rs.getInt(rs.getColumnIndex(kbwDBHelper.TRIPS_COLUMN_FUEL));
            int tripPayments = rs.getInt(rs.getColumnIndex(kbwDBHelper.TRIPS_COLUMN_PAYMENTS));
            int tripAllowance = rs.getInt(rs.getColumnIndex(kbwDBHelper.TRIPS_COLUMN_ALLOWANCES));
            int tripMiscellaneous = rs.getInt(rs.getColumnIndex(kbwDBHelper.TRIPS_COLUMN_MISCELLANEOUS));

            if (!rs.isClosed()) {
                rs.close();
            }

            dateEditText.setText(tripDate);
            dateEditText.setFocusable(false);
            dateEditText.setClickable(false);

            journeyEditText.setText(tripJourney);
            journeyEditText.setFocusable(false);
            journeyEditText.setClickable(false);

            fuelEditText.setText(tripFuel + "");
            fuelEditText.setFocusable(false);
            fuelEditText.setClickable(false);

            paymentsEditText.setText(tripPayments + "");
            paymentsEditText.setFocusable(false);
            paymentsEditText.setClickable(false);

            allowancesEditText.setText(tripAllowance + "");
            allowancesEditText.setFocusable(false);
            allowancesEditText.setClickable(false);

            miscellaneousEditText.setText(tripMiscellaneous + "");
            miscellaneousEditText.setFocusable(false);
            miscellaneousEditText.setClickable(false);

        }
    }

    private void showDialog() {
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        dateEditText.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonSave:
                persistTrip();
                return;
            case R.id.editButton:
                saveButton.setVisibility(View.VISIBLE);
                buttonLayout.setVisibility(View.GONE);

                dateEditText.setEnabled(true);
                dateEditText.setFocusableInTouchMode(false);
                dateEditText.setClickable(false);

                journeyEditText.setEnabled(true);
                journeyEditText.setFocusableInTouchMode(true);
                journeyEditText.setClickable(true);

                fuelEditText.setEnabled(true);
                fuelEditText.setFocusableInTouchMode(true);
                fuelEditText.setClickable(true);

                paymentsEditText.setEnabled(true);
                paymentsEditText.setFocusableInTouchMode(true);
                paymentsEditText.setClickable(true);

                allowancesEditText.setEnabled(true);
                allowancesEditText.setFocusableInTouchMode(true);
                allowancesEditText.setClickable(true);

                miscellaneousEditText.setEnabled(true);
                miscellaneousEditText.setFocusableInTouchMode(true);
                miscellaneousEditText.setClickable(true);

                return;
            case R.id.deleteButton:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteTrip)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dbHelper.deleteTrip(tripID);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Delete Trip?");
                d.show();
                return;
        }
    }
    public void persistTrip() {
        if(tripID > 0) {
            if(dbHelper.updateTrip(tripID,
                    dateEditText.getText().toString(),
                    journeyEditText.getText().toString(),
                    Integer.parseInt(fuelEditText.getText().toString()),
                    Integer.parseInt(paymentsEditText.getText().toString()),
                    Integer.parseInt(allowancesEditText.getText().toString()),
                    Integer.parseInt(miscellaneousEditText.getText().toString())
            )) {
                Toast.makeText(getApplicationContext(), "Trip Update Successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Trip Update Failed", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if (dbHelper.insertTrip(dateEditText.getText().toString(),
                    journeyEditText.getText().toString(),
                    Integer.parseInt(fuelEditText.getText().toString()),
                    Integer.parseInt(paymentsEditText.getText().toString()),
                    Integer.parseInt(allowancesEditText.getText().toString()),
                    Integer.parseInt(miscellaneousEditText.getText().toString())
            )) {
                Toast.makeText(getApplicationContext(), "Trip Inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Could not Insert Trip", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

}

