package tech.lovevirus.lookback;


import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import static android.content.Context.CONNECTIVITY_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {

    public WebView wb;
public ProgressDialog progressDialog;
    public VideoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;

        view =inflater.inflate(R.layout.fragment_account, container, false);
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setTitle("MCA MEMORIES");
        progressDialog.setMessage("Touch me to Contious!!");
        progressDialog.show();
        progressDialog.dismiss();
        wb=(WebView)view.findViewById(R.id.webview);
        wb.getSettings().setAppCacheMaxSize( 1 * 1024 * 1024 ); // 1MB
        wb.getSettings().setAppCachePath( getActivity().getCacheDir().getAbsolutePath() );
        wb.getSettings().setAllowFileAccess( true );
        wb.getSettings().setAppCacheEnabled( true );
        wb.getSettings().setJavaScriptEnabled( true );
        wb.getSettings().setCacheMode( WebSettings.LOAD_CACHE_ELSE_NETWORK );
        wb.loadUrl("https://www.youtube.com/watch?v=m9hxM8mOtEo&index=1&list=PLDJ3-CFuFf5SlIL3orxkWPrmNmNMwQlGT");

        wb.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions

                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });


        // Force links and redirects to open in the WebView instead of in a browser
        wb.setWebViewClient(new WebViewClient());

        return view;
    }

}
