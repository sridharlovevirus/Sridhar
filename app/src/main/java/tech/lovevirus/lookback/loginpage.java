package tech.lovevirus.lookback;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginpage extends AppCompatActivity {
    EditText user, pass;
    private Button loginBtn;
    private Button loginRegBtn;

    private FirebaseAuth mAuth;

    private ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        mAuth = FirebaseAuth.getInstance();

user=(EditText)findViewById(R.id.username);

        pass=(EditText)findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);
        loginRegBtn = findViewById(R.id.retry);
        loginProgress = findViewById(R.id.login_progress);

        loginRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent regIntent = new Intent(loginpage.this, RegisterActivity.class);
                startActivity(regIntent);
                finish();


            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
loginBtn.setText("Check User!!");
loginBtn.setEnabled(false);
                String loginEmail = user.getText().toString();
                String loginPass = pass.getText().toString();

                if (!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)) {
                    loginProgress.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(loginEmail, loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                sendToMain();


                            } else {

                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(loginpage.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();
loginBtn.setEnabled(true);
loginBtn.setText("Login");
                            }

                            loginProgress.setVisibility(View.INVISIBLE);

                        }
                    });

                }


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

        Intent mainIntent = new Intent(loginpage.this, MainActivity.class);
        startActivity(mainIntent);
        finish();

    }
}