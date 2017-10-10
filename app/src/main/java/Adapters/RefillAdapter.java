package Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crm.pharmbooks.PharmCRM.PrescriptionListActivity;
import com.crm.pharmbooks.PharmCRM.R;

import java.util.ArrayList;

import Model.RefillModel;

/**
 * Created by Dell on 09-Oct-17.
 */


    public class RefillAdapter extends RecyclerView.Adapter<RefillAdapter.MyViewHolder> {

        private ArrayList<RefillModel> refillList;


        public RefillAdapter(ArrayList<RefillModel> refillList) {
            this.refillList = refillList;
        }




        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView medName;
            public TextView medDose;
            public TextView lastRefill;
            public TextView endDate;
            public LinearLayout base;
            public MyViewHolder(View view) {
                super(view);
                medName = (TextView) view.findViewById(R.id.med_name);
                medDose= (TextView) view.findViewById(R.id.med_dose);
                lastRefill= (TextView) view.findViewById(R.id.med_last_refill);
                endDate= (TextView) view.findViewById(R.id.med_end);
                base=(LinearLayout)view.findViewById(R.id.refill_list_row_id);


            }
        }


        @Override
        public RefillAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.refill_list_row, parent, false);

        return new RefillAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RefillAdapter.MyViewHolder holder, int position) {


            RefillModel customerDetail = refillList.get(position);
            holder.medName.setText(customerDetail.getMedName());
            holder.medDose.setText(customerDetail.getDosage());
            holder.lastRefill.setText(customerDetail.getRefillDate());
            holder.endDate.setText(customerDetail.getEndDate());
            if(PrescriptionListActivity.LONG_CLICK_FLAG==1){
                if(PrescriptionListActivity.pos==position){
                    holder.base.setBackgroundColor(Color.parseColor("#9e9e9e"));
                }
            }else{
                holder.base.setBackgroundColor(Color.TRANSPARENT);
            }


        }

        @Override
        public int getItemCount()
        {
            return refillList.size();
        }



    }

