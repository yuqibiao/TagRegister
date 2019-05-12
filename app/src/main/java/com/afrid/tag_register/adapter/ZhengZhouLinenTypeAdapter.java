package com.afrid.tag_register.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afrid.tag_register.R;
import com.afrid.tag_register.bean.json.return_data.GetHospitalLinenReturn;

import java.util.List;

/**
 * 功能：部门选择数据Adapter
 *
 * @author yu
 * @version 1.0
 * @date 2017/6/27
 */

public class ZhengZhouLinenTypeAdapter extends RecyclerView.Adapter<ZhengZhouLinenTypeAdapter.DepartmentsViewHolder> {

    private Context mContext;
    private List<GetHospitalLinenReturn.ResultDataBean> dataBeanList;

    public ZhengZhouLinenTypeAdapter(Context context) {
        this.mContext = context;
    }

    public ZhengZhouLinenTypeAdapter(Context context, List<GetHospitalLinenReturn.ResultDataBean> deptList) {
        this.mContext = context;
        this.dataBeanList = deptList;
    }

    @Override
    public ZhengZhouLinenTypeAdapter.DepartmentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(mContext).inflate(R.layout.rv_item_warehouse, parent, false);
        DepartmentsViewHolder viewHolder = new DepartmentsViewHolder(item);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ZhengZhouLinenTypeAdapter.DepartmentsViewHolder holder, int position) {
        holder.getTv_dept_name().setText(dataBeanList.get(position).getLinenName());
    }

    @Override
    public int getItemCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    public void setDataBeanList(List<GetHospitalLinenReturn.ResultDataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
        notifyDataSetChanged();
    }

    public static class DepartmentsViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_dept_name;

        public DepartmentsViewHolder(View itemView) {
            super(itemView);
            tv_dept_name = (TextView) itemView.findViewById(R.id.tv_dept_name);
        }

        public TextView getTv_dept_name() {
            return tv_dept_name;
        }
    }


}
