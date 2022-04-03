package com.example.misterbin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.misterbin.R;
import com.example.misterbin.model.SuggestedArticlesData;

import java.util.List;

public class SuggestedArticlesAdapter extends RecyclerView.Adapter<SuggestedArticlesAdapter.SuggestedArticlesViewHolder> {

    Context context;
    List<SuggestedArticlesData> suggestedArticlesDataList;

    public SuggestedArticlesAdapter(Context context, List<SuggestedArticlesData> suggestedArticlesDataList) {
        this.context = context;
        this.suggestedArticlesDataList = suggestedArticlesDataList;
    }

    @NonNull
    @Override
    public SuggestedArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.articlestoppicks_row_item, parent, false);

        //here we create a recyclerview item layout file
        return new SuggestedArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestedArticlesViewHolder holder, int position) {
        holder.articleName.setText(suggestedArticlesDataList.get(position).getArticleName());
        holder.authorName.setText(suggestedArticlesDataList.get(position).getAuthorName());
        holder.articleImage.setImageResource(suggestedArticlesDataList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return suggestedArticlesDataList.size();
    }

    public static final class SuggestedArticlesViewHolder extends RecyclerView.ViewHolder{

        ImageView articleImage;
        TextView articleName, authorName;

        public SuggestedArticlesViewHolder(@NonNull View itemView) {
            super(itemView);

            articleImage = itemView.findViewById(R.id.article_image5);
            articleName = itemView.findViewById(R.id.article_name);
            authorName = itemView.findViewById(R.id.author_name);
        }
    }
}
