package tech.lovevirus.lookback;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by sridhar on 18/1/18.
 */

public class MySingleton
{
    private static  MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private  static Context mCtx;
    private MySingleton(Context context)
    {
        mCtx=context;
        mRequestQueue =getRequestQueue();
        mImageLoader=new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
    }
    public static synchronized MySingleton getmInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance=new MySingleton(context);
        }
        return mInstance;
    }
    public RequestQueue getRequestQueue()
    {
        if(mRequestQueue==null)
        {
            mRequestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req)
    {
        getRequestQueue().add(req);
    }
    public ImageLoader getmImageLoader()
    {
        return mImageLoader;
    }
}
