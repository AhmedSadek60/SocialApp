package com.example.zeina.data;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    ArrayList<Post> myDataSet = new ArrayList<Post>();
    Context context;

    // e3ml constructor lel adapter hena 3shan tb3at feh l array mn l main activity
    public PostsAdapter (Context context, ArrayList<Post> dat)
    {
        this.context = context;
        myDataSet=dat;
    }

// da constructor
// nfs el 7war

    @Override

    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // link l view holder bel xml layout bta3to
        // t2sod hna el card ?
        // kda asdy, btrbot l viewholder bel xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        // hamada msh bttktb asln w

       // System.out.println("hmada " + myDataSet.get(position).PersonName);

        // set l values fel design objects mn l array
        holder.textView.setText(myDataSet.get(position).getContext());
        holder.imageView.setImageBitmap(myDataSet.get(position).getPicture());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostDetails.class);
                intent.putExtra("post", myDataSet.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }
//bycrash
    //unmute 3ndak :D
    @Override
    public int getItemCount() {
        return myDataSet.size();//array size
        // bs kda :v
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // hena ht3arf el objects eli 3ndak fel design zy l textview wel imgview w hakza
        // w hna htrbot kol item bel id bta3o zy mbt3ml fel activity 3adi.
        public ImageView imageView;
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
             imageView = itemView.findViewById(R.id.Post_Picture);
             textView = itemView.findViewById(R.id.Post_Text);

        }
    }
}
