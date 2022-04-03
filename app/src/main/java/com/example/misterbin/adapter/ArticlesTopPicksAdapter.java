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
import com.example.misterbin.model.ArticlesTopPicksData;

import java.util.List;

public class ArticlesTopPicksAdapter extends RecyclerView.Adapter<ArticlesTopPicksAdapter.ArticlesTopPicksViewHolder> {

    private RecyclerViewClickListener listener;

    Context context;
    List<ArticlesTopPicksData> articlesTopPicksDataList;

    public ArticlesTopPicksAdapter(Context context, List<ArticlesTopPicksData> articlesTopPicksDataList, RecyclerViewClickListener listener) {
        this.context = context;
        this.articlesTopPicksDataList = articlesTopPicksDataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArticlesTopPicksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.articlestoppicks_row_item, parent, false);

        //here we create a recyclerview item layout file
        return new ArticlesTopPicksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesTopPicksViewHolder holder, int position) {
        holder.articleName.setText(articlesTopPicksDataList.get(position).getArticleName());
        holder.authorName.setText(articlesTopPicksDataList.get(position).getAuthorName());
        holder.articleImage.setImageResource(articlesTopPicksDataList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return articlesTopPicksDataList.size();
    }

    public class ArticlesTopPicksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView articleImage;
        TextView articleName, authorName;

        public ArticlesTopPicksViewHolder(@NonNull View itemView) {
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
