//package brettratner.com.safedriving;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
///**
// * Created by BRETT on 4/8/15.
// */
//public class CallBlocker extends Activity {
//
//    /** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_callblocker);
//        int mobileNum;
//
//        ((Button)findViewById(R.id.block)).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                String mobileNumer = ((EditText) findViewById(R.id.mobileNum)).getText().toString();
//
//                //How to block entered mobileNumber
//
//            }
//        });
//
//        ((Button)findViewById(R.id.unblock)).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//
//                String mobileNumer = ((EditText)findViewById(R.id.mobileNum)).getText().toString();
//
//                //How to unblock entered mobileNumber
//
//            }
//        });
//
//
//
//    }
//}