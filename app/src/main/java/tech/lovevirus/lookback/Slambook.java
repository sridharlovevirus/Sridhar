package tech.lovevirus.lookback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Slambook extends AppCompatActivity {
Button share;
EditText msg;
Spinner sp;
CheckBox ck;

    private ProgressBar loginProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slambook);
       ck=(CheckBox)findViewById(R.id.checkBox);

       final SQLiteHelper sqLiteHelper=new SQLiteHelper(Slambook.this);
loginProgress=(ProgressBar)findViewById(R.id.loadbar);
loginProgress.setVisibility(View.INVISIBLE);
        final RequestQueue queue= Volley.newRequestQueue(this);
     share=(Button)findViewById(R.id.retry);
        sp=(Spinner)findViewById(R.id.spinner);

        List<String> list=new ArrayList<String>();
        list.add("ARCHANADEVI M-15MCR001");
        list.add("GOKULRAJ V-15MCR002");
        list.add("JANARTHANAN S-15MCR003");
        list.add("RADHIKA R-15MCR004");
        list.add("THAMARAISELVAN P-15MCR006");
        list.add("ABILASHA R-15MCL007");
        list.add("ABINAYA P-15MCL008");
        list.add("AMBIKA V-15MCL009");
        list.add("ANANDHARAJ P-15MCL010");
        list.add("ANBALAGAN G-15MCL011");
        list.add("ANUSUYA R-15MCL012");
        list.add("ARULSARAVANAN R-15MCL013");
        list.add("ARUNBHUVANESH G-15MCL014");
        list.add("ARUNSHANKAR K-15MCL015");
        list.add("BALA DIVYA S-15MCL016");
        list.add("BALA RAMYA T-15MCL017");
        list.add("BALAJI D-15MCL018");
        list.add("BALAMURUGAVEL G-15MCL019");
        list.add("BASKAR J-15MCL020");
        list.add("BASKAR N-15MCL021");
        list.add("BHUVANESHWARAN V-15MCL022");
        list.add("CATHERINE A-15MCL023");
        list.add("DAYAANAND V M-15MCL024");
        list.add("DHANDAPANI S-15MCL025");
        list.add("DHARANYA V-15MCL026");
        list.add("DINESH BALAJI S-15MCL027");
        list.add("DINESH S-15MCL028");
        list.add("DIVYA DARSHINI.R-15MCL029");
        list.add("GAYATHRI T-15MCL030");
        list.add("GOBAL S-15MCL031");
        list.add("GOWTHAM N S-15MCL032");
        list.add("GOWTHAM R-15MCL033");
        list.add("GOWTHAM S-15MCL034");
        list.add("GUNASEKAR K-15MCL035");
        list.add("HARIDASS T-15MCL036");
        list.add("HARIHARAN A-15MCL037");
        list.add("INDIRA SUTHAR T-15MCL038");
        list.add("INTHUMATHI E-15MCL039");
        list.add("JAGATHEESHKUMAR S-15MCL040");
        list.add("JANARTHANAN J-15MCL041");
        list.add("JITHU P-15MCL042");
        list.add("JOTHIMANI S-15MCL043");
        list.add("KABILAN R-15MCL044");
        list.add("KALAISELVI S-15MCL045");
        list.add("KARTHIGA R-15MCL046");
        list.add("KARTHIK P-15MCL047");
        list.add("KARUPPUSAMY.V-15MCL048");
        list.add("KAVIN B-15MCL049");
        list.add("KAVIN PRASATH G-15MCL050");
        list.add("KAVINESH T-15MCL051");
        list.add("KAVINKUMAR D-15MCL052");
        list.add("KEERTHANA S-15MCL053");
        list.add("KIRUTHIKA D-15MCL054");
        list.add("KIRUTHIKA M-15MCL055");
        list.add("KISHORE KHAN B-15MCL056");
        list.add("KRISHNAPRIYA S-15MCL057");
        list.add("KUMARAVEL G-15MCL058");
        list.add("KURINJI NATHAN K-15MCL059");
        list.add("LAVANYA S-15MCL060");
        list.add("LOGANYA  M V-15MCL061");
        list.add("MADHANRAJ M-15MCL062");
        list.add("MAITHEEN FARMANNULLA S-15MCL063");
        list.add("MANIKANDAN.R-15MCL064");
        list.add("MANISHA M-15MCL065");
        list.add("MANJULA S-15MCL066");
        list.add("MANOJ D-15MCL067");
        list.add("MATHANKUMAR S-15MCL068");
        list.add("MATHIYAZHAKAN T-15MCL069");
        list.add("MEIVANNAN P-15MCL070");
        list.add("MOHAMED NAFEEL HUSSAIN L-15MCL071");
        list.add("MOHAN K-15MCL072");
        list.add("MOHANAPRIYA.S-15MCL073");
        list.add("MOHANRAJ R-15MCL074");
        list.add("MOUNISHYAA M-15MCL075");
        list.add("MYTHILI M-15MCL076");
        list.add("NAVEENKUMAR D-15MCL077");
        list.add("NAVEEN SHANKAR T-15MCL078");
        list.add("NAVEENKUMAR R-15MCL079");
        list.add("NITHIYA G-15MCL080");
        list.add("NITHYA PRIYA O K-15MCL081");
        list.add("NIVETHA T-15MCL082");
        list.add("NIVETHITHA A-15MCL083");
        list.add("NIVITHA P V-15MCL084");
        list.add("PARKAVI T-15MCL085");
        list.add("PAVITHRA K-15MCL086");
        list.add("PAVITHRA R-15MCL087");
        list.add("PONKUMAR S-15MCL088");
        list.add("POORNA PRIYA K-15MCL089");
        list.add("POOVIZHI  M-15MCL090");
        list.add("PRABHU P-15MCL091");
        list.add("PRABU S-15MCL092");
        list.add("PRADEEBKUMAR M-15MCL093");
        list.add("PRADEEPKUMAR.G-15MCL094");
        list.add("PRETHIVI.K-15MCL095");
        list.add("PRIYADHARSHAN B-15MCL096");
        list.add("PRIYADHARSHINI.V-15MCL097");
        list.add("PRIYANKA.A-15MCL098");
        list.add("PRIYANKA.P-15MCL099");
        list.add("PRIYANKA S-15MCL100");
        list.add("RADHIKA R-15MCL101");
        list.add("RAJESH KUMAR JPK-15MCL102");
        list.add("RAJKUMAR K-15MCL103");
        list.add("RAMYABHARATHI R-15MCL104");
        list.add("REVANTH D-15MCL105");
        list.add("SARAVANAKUMAR.M-15MCL106");
        list.add("SAVITHA P-15MCL108");
        list.add("SIBI RAJAVEL.T-15MCL110");
        list.add("SIBI SHANKAR S-15MCL111");
        list.add("SINDURI.S-15MCL112");
        list.add("SIVA V-15MCL113");
        list.add("SOUNDARYA S-15MCL114");
        list.add("SOUNTHARIYA P-15MCL115");
        list.add("SOWMYAA M-15MCL116");
        list.add("SRI RAGAVI S P-15MCL117");
        list.add("SRIDHAR K K-15MCL118");
        list.add("SRINIVASAN S-15MCL119");
        list.add("SUBHASHINI C-15MCL120");
        list.add("SUDHIYA.J-15MCL121");
        list.add("SUGANTHI PRIYA.P-15MCL123");
        list.add("SUGANTHI.P-15MCL122");
        list.add("SUGANYA M-15MCL124");
        list.add("SWARNAMBIKA.SY-15MCL125");
        list.add("SWARNESH M-15MCL126");
        list.add("TAMILMANI C-15MCL127");
        list.add("THANGAMANI.N-15MCL128");
        list.add("THARANI S-15MCL129");
        list.add("VIDHYA.R-15MCL130");
        list.add("VIDHYASRI M-15MCL131");
        list.add("VIGNESH.K-15MCL132");
        list.add("VIGNESHWARAN.T-15MCL133");
        list.add("VIKNESH.K.R-15MCL134");
        list.add("VINITHA.G-15MCL135");
        list.add("VISHNUPRIYA K-15MCL136");
       msg=(EditText)findViewById(R.id.editText);


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
     share.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            share.setText("Writting Ur Slam");
            share.setEnabled(false);
            loginProgress.setVisibility(View.VISIBLE);
            String ls1[] =new String[10];
            ls1=sqLiteHelper.viewdata();
             final String fromname = ls1[0];
             final String rollno=String.valueOf(sp.getSelectedItem());
             final String roll[]=rollno.split("-");
             final String msg1=msg.getText().toString();
             Boolean ck1=ck.isChecked();
            // Toast.makeText(getApplicationContext(),ck1+"",Toast.LENGTH_SHORT).show();
             String from,aimag;
             final String img=ls1[1];
             if(ck1)
             {
                 from=fromname;
                 aimag=img;
             }
             else
             {
                 from="Anonymous User";
                 aimag="http://konguengineering.000webhostapp.com/user.jpg";

             }
             final String aimg1=aimag;
             final String fromname1 = from;
             if(!msg1.equals("") && !roll[0].equals("") && !fromname.equals(""))
             {
                String URL1=Youtube.URL+"writeslambook.php";
                StringRequest postrequest=new StringRequest(Request.Method.POST, URL1, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {

Toast.makeText(getApplicationContext(),"Your Slambook writted!!!",Toast.LENGTH_SHORT).show();
                      Intent i=new Intent(getApplicationContext(),MainActivity.class);

                     startActivity(i);
                      finish();
                   }
                }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                   }
                })
                {
                   @Override
                   protected Map<String,String> getParams()
                   {
                      Map<String,String> params=new HashMap<String,String>();
                      params.put("f",fromname1);
                      params.put("t",roll[1]);
                      params.put("msg",msg1);
                      params.put("db",aimg1);
                      return params;
                   }
                };
                queue.add(postrequest);
             }
             else
             {
                Toast.makeText(getApplicationContext(),"Add All information",Toast.LENGTH_SHORT).show();
             }
             }

        });

    }
}
