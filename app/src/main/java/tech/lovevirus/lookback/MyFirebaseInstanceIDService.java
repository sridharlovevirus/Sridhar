package tech.lovevirus.lookback;



import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

SQLiteHelper sql=new SQLiteHelper(MyFirebaseInstanceIDService.this);
    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//Toast.makeText(getApplicationContext(),"Token"+refreshedToken,Toast.LENGTH_LONG).show();
        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        //calling the method store token and passing token
        storeToken(refreshedToken);
    }

    private void storeToken(String token) {
      //  Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_LONG).show();
        //we will save the token in sharedpreferences later
        SharedPrefManager.getInstance(getApplicationContext()).saveDeviceToken(token);

        Log.d(TAG, "Store Token ok ok ok ok ok o ko kok o k ok ok o k: ");
    }
}