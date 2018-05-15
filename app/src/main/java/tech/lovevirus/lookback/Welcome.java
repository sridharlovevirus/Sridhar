package tech.lovevirus.lookback;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome extends AppCompatActivity {
    private static int time =12000;
    ProgressBar progressBar;
    LottieAnimationView lw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        lw=(LottieAnimationView)findViewById(R.id.animation_view);
     progressBar=(ProgressBar)findViewById(R.id.pro);
        lw.setVisibility(View.VISIBLE);

        final SQLiteHelper sqLiteHelper=new SQLiteHelper(Welcome.this);
        String ls1[] =new String[10];
        ls1=sqLiteHelper.viewdata();
        String token = SharedPrefManager.getInstance(getApplicationContext()).getDeviceToken();
        String url = null;
        final String url1;
        if(!ls1[0].equals("null")) {
             url = "http://konguengineering.000webhostapp.com/token1.php?token=" + token + "&rollno=" + ls1[2];
        }
        else
        {

        }
        url1=url;
      // Toast.makeText(getApplicationContext(),"token="+token,Toast.LENGTH_SHORT).show();
      //  Toast.makeText(getApplicationContext(),"rollno="+ls1[0],Toast.LENGTH_SHORT).show();
        if ( !ls1[0].equals("null"))
        {
            //Toast.makeText(getApplicationContext(),"method",Toast.LENGTH_SHORT).show();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url1,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            lw.setVisibility(View.INVISIBLE);
                         //   Toast.makeText(getApplicationContext(),"Loading Done!!",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), login.class);
                            startActivity(i);
                            finish();

                        }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                lw.setVisibility(View.INVISIBLE);
                Intent i = new Intent(getApplicationContext(), Feedback.class);
                startActivity(i);
                finish();

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }
    else
        {
            Toast.makeText(getApplicationContext(),"First Time App load take more time!!",Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i=new Intent(getApplicationContext(),login.class);
                    startActivity(i);
                    finish();
                }
            },time);

        }
    }

}
