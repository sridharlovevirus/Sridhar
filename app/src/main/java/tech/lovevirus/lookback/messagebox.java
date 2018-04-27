package tech.lovevirus.lookback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class messagebox extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayAdapter<String> adapter;
    List<NameList> nameList;
    NameAdapter adapter1;
    LottieAnimationView lw;
    ProgressBar pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagebox);
        lw=(LottieAnimationView)findViewById(R.id.animation_view);
        pro=(ProgressBar)findViewById(R.id.pro);
        pro.setVisibility(View.VISIBLE);
        lw.setVisibility(View.INVISIBLE);
        final SQLiteHelper sqLiteHelper=new SQLiteHelper(messagebox.this);
        String ls1[] =new String[10];
        ls1=sqLiteHelper.viewdata();
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
nameList=new ArrayList<>();
String url="http://konguengineering.000webhostapp.com/readslambook.php?rollno="+ls1[2];
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pro.setVisibility(View.INVISIBLE);
                       try {

                            JSONArray array = new JSONArray(response);
                          // Toast.makeText(getApplicationContext(),array.length()+"",Toast.LENGTH_SHORT).show();

                           if(array.length()>=1)
                            {
                                lw.setVisibility(View.INVISIBLE);

                            }
                            else
                            {
                                lw.setVisibility(View.VISIBLE);

                            }
                            for (int i = 0; i < array.length(); i++) {


                                JSONObject name = array.getJSONObject(i);


                                nameList.add(new NameList(
                                        name.getString("from"),
                                        name.getString("msg"),
                                        name.getString("db")

                                ));
                            }


                            adapter1 = new NameAdapter(messagebox.this, nameList);
                            recyclerView.setAdapter(adapter1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);

    }
}
