package brettratner.com.safedriving;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

public class accelerometer extends Activity implements SensorEventListener {

    private float lastX, lastY, lastZ;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private boolean accIsRead = false;
    private int timer= 10;


    private float deltaXMax = 0;
    private float deltaYMax = 0;
    private float deltaZMax = 0;

    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;

    private float vibrateThreshold = 0;

    private TextView currentX, currentY, currentZ, maxX, maxY, maxZ;

    public Vibrator v;

    @Override
 /* This checks to see if there is an accelerometer to read the information from and
    if there is then it will start reading values from the accelerometer. And if there is
    no accelerometer than this will just print out that there is no accelerometer available.

  */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceleromter);
        initializeViews();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            // there is an accelerometer

            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = accelerometer.getMaximumRange() / 2;
        } else {
            System.out.println("There is no Accelerometer");
          }

        //initialize vibration
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

    }
/*
    This displays the current x,y,and z values that is being read from the accelerometer
    which are stored into the variables called currentX, currentY, and currentZ. This
    also displays the maximum value that was reached using the accelerometer which are
    stored in the variables called maxX, maxY, maxZ.
 */
    public void initializeViews() {
        currentX = (TextView) findViewById(R.id.currentX);
        currentY = (TextView) findViewById(R.id.currentY);
        currentZ = (TextView) findViewById(R.id.currentZ);

        maxX = (TextView) findViewById(R.id.maxX);
        maxY = (TextView) findViewById(R.id.maxY);
        maxZ = (TextView) findViewById(R.id.maxZ);
    }

    //onResume() register the accelerometer for listening the events
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //onPause() unregister the accelerometer for stop listening the events
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        /*
         display the current x,y,z accelerometer values
          */
        displayCurrentValues();
        /*
         display the max x,y,z accelerometer values
          */
        displayMaxValues();

        /*
        get the change of the x,y,z values of the accelerometer
        dividing all the values by ten nullifies the affect of
        gravity on the accelerometer.
         */
        deltaX = Math.abs(lastX - event.values[0]/10);
        deltaY = Math.abs(lastY - event.values[1]/10);
        deltaZ = Math.abs(lastZ - event.values[2]/10);

    }



    // display the current x,y,z accelerometer values
    public void displayCurrentValues() {
        currentX.setText(Float.toString(deltaX));
        currentY.setText(Float.toString(deltaY));
        currentZ.setText(Float.toString(deltaZ));

/*
    accISRead is checking to see if the alert box is popped up so that
    when the alert box is active it will stop reading in data from the
    accelerometer. The deltaX, deltaY, and deltaZ are the values the the
    change in acceleration must be at in order for the alert bod to appear.
    There is a 10 second countdown that will automatically close the alert
    box if the user does not click the yes button which will assume the user
    is the driver.
 */

        if(accIsRead == false && (deltaX > 1.5 || deltaY > 1.5 || deltaZ > 1.5)) {


            accIsRead = true;

            final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

//            AlertDialog alertDialog = new AlertDialog.Builder(this).create();


            alertDialog.setTitle("Driving Buddy" /*\t\t\t\t\t\t\t\t\t\t\t\t\t + timer*/);
//            if (timer <= 10){
//                System.out.println("Timer: " + timer);
//            timer--;
//        }


            alertDialog.setMessage("Are you a passenger?");
            alertDialog.setButton("YES", new DialogInterface.OnClickListener                                                               () {
                public void onClick(DialogInterface dialog, int which) {
                    accIsRead = false;

                }


            });
            Runnable hideDialog = new Runnable() {
                public void run() {
                    alertDialog.cancel();
                    accIsRead = false;

                }
            };
            alertDialog.show();


            executor.schedule(hideDialog, timer, SECONDS);



        }

    }

    /*
    This displays the max x,y,z accelerometer values on the screen.
     */
    public void displayMaxValues() {
        if (deltaX > deltaXMax) {
            deltaXMax = deltaX;
            maxX.setText(Float.toString(deltaXMax));
        }
        if (deltaY > deltaYMax) {
            deltaYMax = deltaY;

            maxY.setText(Float.toString(deltaYMax));
        }
        if (deltaZ > deltaZMax) {
            deltaZMax = deltaZ;
            maxZ.setText(Float.toString(deltaZMax));
        }
    }
}