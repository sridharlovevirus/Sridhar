package tech.lovevirus.lookback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {
Button retry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        retry=(Button)findViewById(R.id.retry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast.makeText(getApplicationContext(),"Retry to Connect",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), Welcome.class);
                startActivity(i);
                finish();

            }
        });
    }
}
