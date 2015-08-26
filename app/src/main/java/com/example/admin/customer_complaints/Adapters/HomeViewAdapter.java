package com.example.admin.customer_complaints.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.customer_complaints.R;

/**
 * Created by admin on 24-Aug-15.
 */
public class HomeViewAdapter extends RecyclerView.Adapter<HomeViewAdapter.ViewHolder> {

    public String[] text;
    public int[] icons;
    OnItemClickListener mItemClickListener;

    public HomeViewAdapter(String[] text, int[] icons) {
        this.text = text;
        this.icons = icons;
    }

    @Override
    public HomeViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.final_home_view, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.homeIcon.setImageResource(icons[position]);
        holder.homeText.setText(text[position]);
    }

    @Override
    public int getItemCount() {
        return text.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView homeIcon;
        TextView homeText;


        public ViewHolder(View itemView) {
            super(itemView);
            homeIcon = (ImageView) itemView.findViewById(R.id.home_icon);
            homeText = (TextView) itemView.findViewById(R.id.home_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}

