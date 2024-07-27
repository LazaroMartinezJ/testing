package com.example.gymlogsp20;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.gymlogsp20.db.AppDatabase;
import com.example.gymlogsp20.db.GymLogDAO;
import com.example.gymlogsp20.GymLog;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView mMainDisplay;

    EditText mExercise;
    EditText mWeight;
    EditText mReps;

    Button mSubmitButton;

    GymLogDAO mGymLogDAO;

    List<GymLog> mGymLogs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainDisplay = findViewById(R.id.mainGymLogDisplay);
        mMainDisplay.setMovementMethod(new ScrollingMovementMethod());
        mExercise = findViewById(R.id.mainExerciseEditText);
        mWeight = findViewById(R.id.mainWeightEditText);
        mReps = findViewById(R.id.mainRepsEditText);

        mSubmitButton = findViewById(R.id.mainSubmitButton);

        mGymLogDAO = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getGymLogDao();

        refreshDisplay();
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GymLog log = getValuesFromDisplay();

                mGymLogDAO.insert(log);
                refreshDisplay();
            }
        });

    }

    private GymLog getValuesFromDisplay(){
        String exercise = "No record food";
        double weight = 0.0;
        int reps = 0;

        exercise = mExercise.getText().toString();

        try{
            weight = Double.parseDouble(mWeight.getText().toString());
        } catch (NumberFormatException e){
            Log.d("GYMLOG","Couldn't convert to weight");
        }

        try{
            reps = Integer.parseInt(mReps.getText().toString());
        } catch (NumberFormatException e){
            Log.d("GYMLOG", "Couldn't convert reps");
        }

        GymLog log = new GymLog(exercise,reps,weight);
        return log;
    }

    private void refreshDisplay(){
        mGymLogs = mGymLogDAO.getGymLogs();
        if(mGymLogs.size() >= 0){
            mMainDisplay.setText(R.string.noLogsMessage);
        }

        StringBuilder sb = new StringBuilder();
        for(GymLog log : mGymLogs){
            sb.append(log);
            sb.append("\n");
            sb.append("=-=-=-=-=-=-=-=-");
            sb.append("\n");
        }
        mMainDisplay.setText(sb.toString());
    }


}
















