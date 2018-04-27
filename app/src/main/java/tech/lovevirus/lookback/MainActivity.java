package tech.lovevirus.lookback;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private Toolbar mainToolbar;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;

    private String current_user_id;

    private FloatingActionButton addPostBtn;

    private BottomNavigationView mainbottomNav;

    private HomeFragment homeFragment;
    private VideoFragment videoFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("LookBack");

        if(mAuth.getCurrentUser() != null) {

            mainbottomNav = findViewById(R.id.mainBottomNav);

            // FRAGMENTS
            homeFragment = new HomeFragment();
            videoFragment = new VideoFragment();
            profileFragment = new ProfileFragment();

            initializeFragment();

            mainbottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);

                    switch (item.getItemId()) {

                        case R.id.bottom_action_home:

                            replaceFragment(homeFragment, currentFragment);
                            return true;

                        case R.id.bottom_action_video:

                            replaceFragment(videoFragment, currentFragment);
                            return true;

                        case R.id.bottom_action_profile:

                            replaceFragment(profileFragment, currentFragment);
                            return true;

                        default:
                            return false;


                    }

                }
            });


            addPostBtn = findViewById(R.id.add_post_btn);
            addPostBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent newPostIntent = new Intent(MainActivity.this, NewPostActivity.class);
                    startActivity(newPostIntent);

                }
            });

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        final SQLiteHelper sqLiteHelper=new SQLiteHelper(MainActivity.this);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser == null){

            sendToLogin();

        } else {

            current_user_id = mAuth.getCurrentUser().getUid();

            firebaseFirestore.collection("Users").document(current_user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    if(task.isSuccessful()){

                        if(!task.getResult().exists()){

                            Intent setupIntent = new Intent(MainActivity.this, SetupActivity.class);
                            startActivity(setupIntent);
                            finish();

                        }
                        else
                        {
                            String name = task.getResult().getString("name");
                            String image = task.getResult().getString("image");
                            String rollno=task.getResult().getString("rollno");
                            sqLiteHelper.insertRecord(name,image,rollno);
                        }

                    } else {

                        String errorMessage = task.getException().getMessage();
                        Toast.makeText(MainActivity.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();


                    }

                }
            });

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final SQLiteHelper sqLiteHelper=new SQLiteHelper(MainActivity.this);
        switch (item.getItemId()) {

            case R.id.action_logout_btn:

                logOut();

               return true;

            case R.id.about:
                Intent AboutIntent = new Intent(MainActivity.this, About.class);
                startActivity(AboutIntent);
                return true;

            case R.id.slambook:

                Intent settingsIntent = new Intent(MainActivity.this, Slambook.class);
                startActivity(settingsIntent);

                return true;

            case R.id.msgbox:

                Intent msgIntent= new Intent(MainActivity.this, messagebox.class);
                startActivity(msgIntent);

                return true;

               default:
                   return false;


        }

    }

    private void logOut() {
        Toast.makeText(getApplicationContext(),"Bye see Again!!",Toast.LENGTH_SHORT).show();
        final SQLiteHelper sqLiteHelper=new SQLiteHelper(MainActivity.this);
        String ls1[] =new String[10];
        ls1=sqLiteHelper.viewdata();
final String url="http://konguengineering.000webhostapp.com/token1.php?d=1&r="+ls1[2];
//Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT).show();
       StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        sendToLogin();
                        mAuth.signOut();
                        sqLiteHelper.delete();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);




    }

    private void sendToLogin() {

        Intent loginIntent = new Intent(MainActivity.this, loginpage.class);
        startActivity(loginIntent);
        finish();

    }

    private void initializeFragment(){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.main_container, homeFragment);
        fragmentTransaction.add(R.id.main_container, videoFragment);
        fragmentTransaction.add(R.id.main_container, profileFragment);

        fragmentTransaction.hide(videoFragment);
        fragmentTransaction.hide(profileFragment);

        fragmentTransaction.commit();

    }

    private void replaceFragment(Fragment fragment, Fragment currentFragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(fragment == homeFragment){

            fragmentTransaction.hide(profileFragment);
            fragmentTransaction.hide(videoFragment);

        }

        if(fragment == profileFragment){

            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(videoFragment);

        }

        if(fragment == videoFragment){

            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(profileFragment);

        }
        fragmentTransaction.show(fragment);


        fragmentTransaction.commit();

    }

}
