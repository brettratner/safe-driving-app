package brettratner.com.safedriving;

/**
 * Created by BRETT on 4/8/15.
 */

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Switch;


public class Setting extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


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




        } else{

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
