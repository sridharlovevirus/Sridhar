package tech.lovevirus.lookback;

import android.content.Context;
import android.content.Intent;
import android.nfc.tech.NfcA;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by sridhar on 25/4/18.
 */

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameViewHolder>
{
    public Context mCtx;
    private List<NameList> NameList;

    public NameAdapter(Context mCtx, List<NameList> nameList) {
        this.mCtx = mCtx;
        this.NameList = nameList;
    }
    @Override
    public NameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.slam, null);
        return new NameViewHolder(view);
    }
    @Override
    public void onBindViewHolder(NameViewHolder holder, int position) {
        NameList names = NameList.get(position);
        if(names.getFrom().equals("")) {
            holder.from.setText(names.getFrom() + "No Slam Availale!!");
            holder.msg.setText(names.getMsg() + "Try Agian Later");
        }
        else
        {
            holder.from.setText(names.getFrom());
            holder.msg.setText(names.getMsg());
        }
        String db=names.getDb();
        RequestOptions placeholderRequest = new RequestOptions();
        placeholderRequest.placeholder(R.drawable.default_image);
if(db.equals("")) {
    db="http;//konguengineering.000webhostapp.com/user.jpg";
    Glide.with(mCtx).setDefaultRequestOptions(placeholderRequest).load(db).into(holder.imageView);
}
else
{
    Glide.with(mCtx).setDefaultRequestOptions(placeholderRequest).load(db).into(holder.imageView);
}

    }
    @Override
    public int getItemCount() {
        return NameList.size();
    }
    public static class NameViewHolder extends RecyclerView.ViewHolder {
        Context ctx;
        TextView from,msg;
        ImageView imageView;

        public NameViewHolder(View itemView) {
            super(itemView);
            ctx=itemView.getContext();
           from=itemView.findViewById(R.id.username);
           msg=itemView.findViewById(R.id.msg);
           imageView=itemView.findViewById(R.id.developer);
        }
    }
}
