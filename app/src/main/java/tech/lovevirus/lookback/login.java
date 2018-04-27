package tech.lovevirus.lookback;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class login extends AppCompatActivity {
VideoView background;
Button login,register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
               background=(VideoView)findViewById(R.id.videoview);
          Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.bg);
        background.setVideoURI(uri);
        background.start();
        background.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
login=(Button)findViewById(R.id.retry);
register=(Button)findViewById(R.id.signUp);
register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent register=new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(register);
    }
});
login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent login=new Intent(getApplicationContext(),loginpage.class);
        startActivity(login);
    }
});

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {

            sendToMain();

        }


    }

    private void sendToMain() {

        Intent mainIntent = new Intent(login.this, MainActivity.class);
        startActivity(mainIntent);
        finish();

    }
}
