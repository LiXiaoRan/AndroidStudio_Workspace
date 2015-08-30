package com.liran.lenovo.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * recycleview的适配器
 */
class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.myViewHolder> {

    private Context context;
    private List<String> mDatas;

    public HomeAdapter(Context mcontext, List<String> Datas) {
        context = mcontext;
        mDatas = Datas;
    }


    public interface onItemOnClickListener {
        void onClickListener(View itemView, int position);

        void onLongClickListener(View itemView, int position);
    }

    private onItemOnClickListener monItemOnClickListener;

    public void setonItemOnClickListener(onItemOnClickListener onItemOnClickListener) {
        monItemOnClickListener = onItemOnClickListener;
    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myViewHolder holder = new myViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position).toString());
            /*ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
            params.height = (int)(200+Math.random()*400);
            System.out.println("高=== "+params.height);
            holder.itemView.setLayoutParams(params);*/
        holder.linearLayout.getLayoutParams().height = (int) (100 + Math.random() * 200);
//            holder.linearLayout.setLayoutParams();

        if (monItemOnClickListener != null) {

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    monItemOnClickListener.onClickListener(v, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    monItemOnClickListener.onLongClickListener(v, position);
                    return false;
                }
            });

        }


    }


    public void addData(int position) {
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        LinearLayout linearLayout;

        public myViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_linearlayout);

        }
    }


}