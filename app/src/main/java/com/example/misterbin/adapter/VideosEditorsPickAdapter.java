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
import com.example.misterbin.model.VideosEditorsPickData;

import java.util.List;

public class VideosEditorsPickAdapter extends RecyclerView.Adapter<VideosEditorsPickAdapter.VideosEditorsPickViewHolder> {

    private RecyclerViewClickListener listener;

    Context context;
    List<VideosEditorsPickData> videosEditorsPickDataList;

    public VideosEditorsPickAdapter(Context context, List<VideosEditorsPickData> videosEditorsPickDataList,RecyclerViewClickListener listener) {
        this.context = context;
        this.videosEditorsPickDataList = videosEditorsPickDataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VideosEditorsPickViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.videoseditorspick_row_item, parent, false);

        //here we create a recyclerview item layout file
        return new VideosEditorsPickViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosEditorsPickViewHolder holder, int position) {
        holder.videoName.setText(videosEditorsPickDataList.get(position).getVideoName());
        holder.videoDescription.setText(videosEditorsPickDataList.get(position).getVideoDescription());
        holder.videoImage.setImageResource(videosEditorsPickDataList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return videosEditorsPickDataList.size();
    }

    public class VideosEditorsPickViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView videoImage;
        TextView videoName, videoDescription;

        public VideosEditorsPickViewHolder(@NonNull View itemView) {
            super(itemView);

            videoImage = itemView.findViewById(R.id.video_image);
            videoName = itemView.findViewById(R.id.video_name);
            videoDescription = itemView.findViewById(R.id.NormalText);

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
