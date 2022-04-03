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
import com.example.misterbin.model.ArticlesDiscoverData;

import java.util.List;

public class ArticlesDiscoverAdapter extends RecyclerView.Adapter<ArticlesDiscoverAdapter.ArticlesDiscoverViewHolder> {

    private RecyclerViewClickListener listener;

    Context context;
    List<ArticlesDiscoverData> articlesDiscoverDataList;

    public ArticlesDiscoverAdapter(Context context, List<ArticlesDiscoverData> articlesDiscoverDataList, RecyclerViewClickListener listener) {
        this.context = context;
        this.articlesDiscoverDataList = articlesDiscoverDataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArticlesDiscoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.articlestoppicks_row_item, parent, false);

        //here we create a recyclerview item layout file
        return new ArticlesDiscoverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesDiscoverViewHolder holder, int position) {
        holder.articleName.setText(articlesDiscoverDataList.get(position).getArticleName());
        holder.authorName.setText(articlesDiscoverDataList.get(position).getAuthorName());
        holder.articleImage.setImageResource(articlesDiscoverDataList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return articlesDiscoverDataList.size();
    }

    public class ArticlesDiscoverViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView articleImage;
        TextView articleName, authorName;

        public ArticlesDiscoverViewHolder(@NonNull View itemView) {
            super(itemView);

            articleImage = itemView.findViewById(R.id.article_image5);
            articleName = itemView.findViewById(R.id.article_name);
            authorName = itemView.findViewById(R.id.author_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener
    {
        void onClick(View v, int position);
    }

}
