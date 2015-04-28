package brettratner.com.safedriving;

/**
 * Created by BRETT on 4/8/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.Toast;


public class Setting extends /*ActionBar*/Activity {

    public static final String PREFERENCES_NAME = "DataStorage";

//    public static final String IS_HUMAN_KEY = "IsHuman";
    public static final String IS_PASSENGER_KEY = "IsPassenger";
    public static final String START_UP_SPEED_KEY = "startUpSpeed";
    public static final String IS_GPS_KEY = "IsGps";
    public static final String IS_BLUETOOTH_KEY = "IsBluetooth";
    public static final String IS_PRIVACY_KEY = "IsPrivacy";


//    protected CheckBox mIsHumanCheckBox;
    protected Switch mIsPassengerSwitch;
    protected NumberPicker mStartUpSpeedPicker;
    protected Switch mIsGpsSwitch;
    protected Switch mIsBluetoothSwith;
    protected Switch mIsPrivavySwitch;
    protected Button mSaveButton;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

// Get handles to UI elements
//        mIsHumanCheckBox = (CheckBox) findViewById(R.id.is_human);
        mSaveButton = (Button) findViewById(R.id.savebutton);
        mIsPassengerSwitch = (Switch) findViewById(R.id.passengerbutton);
        mStartUpSpeedPicker = (NumberPicker) findViewById(R.id.speedpicker);
        mIsGpsSwitch = (Switch) findViewById(R.id.gpsbutton);
        mIsBluetoothSwith = (Switch) findViewById(R.id.bluetoothbutton);
        mIsPrivavySwitch = (Switch) findViewById(R.id.privacybutton);

        mSaveButton.setOnClickListener(mSaveOnClick);

        mSharedPreferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

         setFieldsFromSavedDate();

        NumberPicker np = (NumberPicker) findViewById(R.id.speedpicker);
        np.setMinValue(10);// sets min value to 10
        np.setMaxValue(25);// sets max value to 25
        np.setWrapSelectorWheel(false);
        np.setVerticalFadingEdgeEnabled(true);
        np.setScrollbarFadingEnabled(true);
        np.setScrollBarDefaultDelayBeforeFade(1000);
        np.setScrollBarFadeDuration(1000);
        np.getVerticalFadingEdgeLength();


        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {

                // TODO Auto-generated method stub
                System.out.println("this was: " + oldVal);
                System.out.println("this  is: " + newVal);
//                String Old = "Old Value : ";
//
//                String New = "New Value : ";

            }
        });


    }
    protected void setFieldsFromSavedDate(){
       int startUpSpeed = mSharedPreferences.getInt(START_UP_SPEED_KEY, 15);
       boolean isPassenger = mSharedPreferences.getBoolean(IS_PASSENGER_KEY,false);
       boolean isGps = mSharedPreferences.getBoolean(IS_GPS_KEY, false);
       boolean isBluetooth = mSharedPreferences.getBoolean(IS_BLUETOOTH_KEY,false);
//       boolean isHuman = mSharedPreferences.getBoolean(IS_HUMAN_KEY, false);
       boolean isPrivacy = mSharedPreferences.getBoolean(IS_PRIVACY_KEY, false);


        mStartUpSpeedPicker.setValue(startUpSpeed);
        mIsPassengerSwitch.setChecked(isPassenger);
        mIsGpsSwitch.setChecked(isGps);
        mIsBluetoothSwith.setChecked(isBluetooth);
        mIsPrivavySwitch.setChecked(isPrivacy);

//        mIsHumanCheckBox.setChecked(isHuman);

    }

    protected OnClickListener mSaveOnClick = new OnClickListener() {
        @Override
        public void onClick (View view){
            int startUpSpeed = mStartUpSpeedPicker.getValue();//this may not work
            boolean isPassenger = mIsPassengerSwitch.isChecked();
            boolean isGps = mIsGpsSwitch.isChecked();
            boolean isBluetooth = mIsBluetoothSwith.isChecked();
            boolean isPrivacy = mIsPrivavySwitch.isChecked();
//            boolean isHuman = mIsHumanCheckBox.isChecked();


            SharedPreferences.Editor editor = mSharedPreferences.edit();
//            editor.putBoolean(IS_HUMAN_KEY, isHuman);
            editor.putBoolean(IS_PASSENGER_KEY, isPassenger);
            editor.putBoolean(IS_GPS_KEY, isGps);
            editor.putBoolean(IS_BLUETOOTH_KEY, isBluetooth);
            editor.putBoolean(IS_PRIVACY_KEY,isPrivacy);
            editor.putInt(START_UP_SPEED_KEY,startUpSpeed);

            //This is the commit
            if(editor.commit()) {
                Toast.makeText(getApplicationContext(),"Data saved", Toast.LENGTH_SHORT).show();
            }


        }

    };






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;


    }





    public void onSwitchClicked(View view){
        //is the toggle on
        boolean gpson = ((Switch) findViewById(R.id.gpsbutton)).isChecked();
        boolean bluetooth = ((Switch) findViewById(R.id.bluetoothbutton)).isChecked();



        if (gpson){
            System.out.println(gpson);
//                String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
//
//                if(!provider.contains("gps")){ //if gps is disabled
//                    final Intent poke = new Intent();
//                    poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
//                    poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
//                    poke.setData(Uri.parse("3"));
//                    sendBroadcast(poke);
//                }

            Intent intent=new Intent("android.location.GPS_ENABLED_CHANGE");
            intent.putExtra("enabled", true);
            sendBroadcast(intent);


        } else{
            System.out.println(gpson);

            Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
            intent.putExtra("enabled", false);
            sendBroadcast(intent);

//                String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
//
//                if(provider.contains("gps")){ //if gps is enabled
//                    final Intent poke = new Intent();
//                    poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
//                    poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
//                    poke.setData(Uri.parse("3"));
//                    sendBroadcast(poke);
//                }


        }

        if (bluetooth){




        } else{

        }




    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
