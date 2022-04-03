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
import com.example.misterbin.model.VideosOriginalsData;

import java.util.List;

public class VideosOriginalsAdapter extends RecyclerView.Adapter<VideosOriginalsAdapter.VideosOriginalsViewHolder> {

    private RecyclerViewClickListener listener;

    Context context;
    List<VideosOriginalsData> videosOriginalsDataList;

    public VideosOriginalsAdapter(Context context, List<VideosOriginalsData> videosOriginalsDataList, RecyclerViewClickListener listener) {
        this.context = context;
        this.videosOriginalsDataList = videosOriginalsDataList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VideosOriginalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.videosoriginals_row_item, parent, false);

        //here we create a recyclerview item layout file
        return new VideosOriginalsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosOriginalsViewHolder holder, int position) {
        holder.videoName.setText(videosOriginalsDataList.get(position).getVideoName());
        holder.videoDescription.setText(videosOriginalsDataList.get(position).getVideoDescription());
        holder.videoImage.setImageResource(videosOriginalsDataList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return videosOriginalsDataList.size();
    }

    public class VideosOriginalsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView videoImage;
        TextView videoName, videoDescription;

        public VideosOriginalsViewHolder(@NonNull View itemView) {
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
