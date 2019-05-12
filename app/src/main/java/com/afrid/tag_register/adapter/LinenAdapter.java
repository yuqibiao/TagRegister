package com.afrid.tag_register.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.afrid.tag_register.R;
import com.afrid.tag_register.bean.SubReceiptListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：扫描后得条目对应得Adapter
 *
 * @author yu
 * @version 1.0
 * @date 2017/6/27
 */

public class LinenAdapter extends RecyclerView.Adapter<LinenAdapter.LinenViewHolder>{

    private Context mContext;

    private List<SubReceiptListBean> mData;

    private List<Integer> unCheckList = new ArrayList<>();

    public LinenAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public LinenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.rv_item_linen ,parent , false);
        LinenViewHolder viewHolder   = new LinenViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LinenViewHolder holder, final int position) {
        holder.getTv_name().setText(""+mData.get(position).getTagTypeName());
        Integer tagNum = mData.get(position).getTagNum();
        Integer actualTagNum = mData.get(position).getActualTagNum();
        holder.getTv_num().setText(""+tagNum);
        if (actualTagNum!=null){
            holder.getTv_actual_num().setText(""+actualTagNum);
        }else{
            holder.getTv_actual_num().setText("库中没记录");
        }
        if (tagNum == actualTagNum){
            holder.getCb_linen().setChecked(true);
        }else{
            holder.getCb_linen().setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return mData==null ? 0 :mData.size();
    }

    public void setmData(List<SubReceiptListBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public static class LinenViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name;
        private TextView tv_num;
        private TextView tv_actual_num;
        private CheckBox cb_linen;

        public LinenViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_num = (TextView) itemView.findViewById(R.id.tv_num);
            tv_actual_num = (TextView) itemView.findViewById(R.id.tv_actual_num);
            cb_linen = (CheckBox) itemView.findViewById(R.id.cb_linen);
        }

        public TextView getTv_name() {
            return tv_name;
        }

        public void setTv_name(TextView tv_name) {
            this.tv_name = tv_name;
        }

        public TextView getTv_num() {
            return tv_num;
        }

        public void setTv_num(TextView tv_num) {
            this.tv_num = tv_num;
        }

        public TextView getTv_actual_num() {
            return tv_actual_num;
        }

        public void setTv_actual_num(TextView tv_actual_num) {
            this.tv_actual_num = tv_actual_num;
        }

        public CheckBox getCb_linen() {
            return cb_linen;
        }

        public void setCb_linen(CheckBox cb_linen) {
            this.cb_linen = cb_linen;
        }
    }

    public List<String> getCheckedPosition(){
        List<String> positions = new ArrayList<>();
        for (int i=0 ; i<mData.size() ; i++){
            if(!unCheckList.contains(new Integer(i))){
                positions.add(mData.get(i).getTagTypeName());
            }
        }
        return positions;
    }
}
