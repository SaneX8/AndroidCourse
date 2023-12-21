package com.example.androidkurssi;

//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.GridItemViewHolder> {


    /*private Context context;
    private List<Company> mItemList;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    public static final String TAG ="jotain:";

    public MyAdapter( List<Company> mItemList) {
        this.mItemList = mItemList;
        Log.e(TAG,"Yrityksiä: "+mItemList.size());

    }

    @Override
    public GridItemViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new GridItemViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(GridItemViewHolder holder, int position) {
        Company items = mItemList.get(position);
        holder.mTitle.setText(items.getName());
       // holder.mPosition.setText("" + items.getPosition());
        Log.e(TAG,"Item-id"+ items.getName());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
      private void onItemHolderClick(GridItemViewHolder itemHolder) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(null, itemHolder.itemView,
                    itemHolder.getAdapterPosition(), itemHolder.getItemId());
        }
    }
    public class GridItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTitle, mPosition;
        public MyAdapter mAdapter;
        public GridItemViewHolder(View itemView, MyAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            mTitle = (TextView) itemView.findViewById(R.id.nameText);
            //mPosition = (TextView) itemView.findViewById(R.id.item_position);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            //mAdapter.onItemHolderClick(this);
        }
    } */



//}

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Company> dataSet;
    public static final String TAG ="jotain:";

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.nameText);
           // this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            //this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public MyAdapter(ArrayList<Company> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getName());
        //textViewVersion.setText(dataSet.get(listPosition).getVersion());
        //imageView.setImageResource(dataSet.get(listPosition).getImage());
        Log.e(TAG,"Item-id"+ dataSet.get(listPosition).getName());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
