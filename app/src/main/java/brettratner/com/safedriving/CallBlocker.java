package brettratner.com.safedriving;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
* Created by BRETT on 4/8/15.
*/
public class CallBlocker extends Activity {

    private Button blocked, unblocked;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callblocker);
//        int mobileNum;
        blocked = (Button) findViewById(R.id.block);
        unblocked=(Button) findViewById(R.id.unblock);

        blocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CALL IS BLOCKED");
                Context context = getApplicationContext();
                CharSequence text = "Calls are Blocked";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        unblocked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("CALL IS UNBLOCKED");
                Context context = getApplicationContext();
                CharSequence text = "Calls are UnBlocked";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });


    }
}