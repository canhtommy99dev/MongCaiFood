package com.alexmedia.mongcaifood;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.ViewHolder> {

    Context context;
    List<ModelComment> modelComments;

    public AdapterComment(Context context, List<ModelComment> modelComments) {
        this.context = context;
        this.modelComments = modelComments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptercomment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(modelComments.get(position).getImageComment()).into(holder.image1);
        holder.txtacc.setText("Người dùng: "+modelComments.get(position).getName());
        holder.txtReview.setText("Đánh giá: "+String.valueOf(modelComments.get(position).seek)+" SAO");
        holder.txtComment.setText(modelComments.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return modelComments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image1;
        TextView txtacc,txtComment,txtReview;

        public ViewHolder(View itemView) {
            super(itemView);
            image1 = itemView.findViewById(R.id.imgAvatarComment1);
            txtacc = itemView.findViewById(R.id.account_google_comment);
            txtComment = itemView.findViewById(R.id.txtcommentguest);
            txtReview = itemView.findViewById(R.id.txtreview);
        }
    }
}
