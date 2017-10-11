package Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crm.pharmbooks.PharmCRM.PrescriptionRefillActivity;
import com.crm.pharmbooks.PharmCRM.R;

import java.util.ArrayList;

import Model.RefillEditModel;
import Model.RefillModel;

/**
 * Created by Dell on 09-Oct-17.
 */


    public class EditRefillAdapter extends RecyclerView.Adapter<EditRefillAdapter.MyViewHolder> {

        private ArrayList<RefillEditModel> refillList;


        public EditRefillAdapter(ArrayList<RefillEditModel> refillList) {
            this.refillList = refillList;
        }




        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView medName;
            public TextView medDose;
            public Button inc,dec;
            public MyViewHolder(View view) {
                super(view);
                medName = (TextView) view.findViewById(R.id.med_name);
                medDose= (TextView) view.findViewById(R.id.med_dose);
                inc = (Button)view.findViewById(R.id.increment);
                dec = (Button)view.findViewById(R.id.decrement);

            }
        }


        @Override
        public EditRefillAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.refill_list_edit_row, parent, false);

        return new EditRefillAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final EditRefillAdapter.MyViewHolder holder, int position) {


            final RefillEditModel customerDetail = refillList.get(position);
            holder.medName.setText(customerDetail.getMedName());
            holder.medDose.setText(customerDetail.getDosage());
            if(PrescriptionRefillActivity.clicked_pos==position) {
                holder.inc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int day = Integer.parseInt(holder.medDose.getText().toString());
                        day++;
                        holder.medDose.setText(day + "");
                        int add = customerDetail.getIncrement();
                        customerDetail.setIncrement(++add);
                        Log.d("Mytag009",customerDetail.getIncrement()+"");
                    }
                });
                holder.dec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int day = Integer.parseInt(holder.medDose.getText().toString());
                        day--;
                        holder.medDose.setText(day + "");
                        int add = customerDetail.getDecrement();
                        customerDetail.setDecrement(++add);
                        Log.d("Mytag009",customerDetail.getDecrement()+"");
                    }
                });
            }

        }

        @Override
        public int getItemCount()
        {
            return refillList.size();
        }



    }

