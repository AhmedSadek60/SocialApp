package com.example.zeina.data;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    ArrayList<String> comments = new ArrayList<String>();
    Context context;
    public CommentAdapter (Context context, ArrayList<String> commentData){
        comments=commentData;
        this.context=context;
    }

    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment, parent, false);
        return new CommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, int position) {
        holder.textView.setText(comments.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
       // CardView cardView;
       // public View view;
        public ViewHolder(View itemView) {
            super(itemView);
           // cardView=itemView.findViewById(R.id.card_view);
            textView = itemView.findViewById(R.id.comment_text);
        }
    }
}