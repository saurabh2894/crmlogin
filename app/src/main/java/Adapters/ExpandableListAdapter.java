package Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crm.pharmbooks.PharmCRM.PrescriptionListActivity;
import com.crm.pharmbooks.PharmCRM.R;
import com.crm.pharmbooks.PharmCRM.RefillListActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Dell on 04/09/17.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    LinearLayout base,child;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }



    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.prescription_list_row, null);
        }
        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
        child = (LinearLayout) convertView.findViewById(R.id.pres_list_layout);


        if (PrescriptionListActivity.ACTIVITY_FLAG.equals("Presc")) {
            if (PrescriptionListActivity.CHILDLONG_CLICK_FLAG == 1 && PrescriptionListActivity.LONG_CLICK_FLAG == 0) {
                if ((childPosition == PrescriptionListActivity.childpos) && (groupPosition == PrescriptionListActivity.grouppos)) {
                    child.setBackgroundResource(R.color.colorAccent);
                }
            } else if (PrescriptionListActivity.CHILDLONG_CLICK_FLAG == 0) {
                child.setBackgroundColor(Color.TRANSPARENT);
            }
        } else if (PrescriptionListActivity.ACTIVITY_FLAG.equals("Refill")){

         if (RefillListActivity.REFILL_FLAG == 1) {
             if (RefillListActivity.child_pos.containsKey(groupPosition)) {
                 if (RefillListActivity.child_pos.get(groupPosition).contains(childPosition)) {
                     child.setBackgroundColor(Color.parseColor("#FFFF00"));
                 }else {
                     child.setBackgroundColor(Color.TRANSPARENT);
                 }
             }else {
                 child.setBackgroundColor(Color.TRANSPARENT);
             }
         }
            else {
                child.setBackgroundColor(Color.TRANSPARENT);
            }
        }


        //txtListChild.setTypeface(null, Typeface.BOLD);
        txtListChild.setText(childText);
        txtListChild.setTextSize(15);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {

        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.customer_list_row, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.CName);
        base = (LinearLayout) convertView.findViewById(R.id.customer_list_layout);

        if (PrescriptionListActivity.ACTIVITY_FLAG.equals("Presc")){
            if (PrescriptionListActivity.LONG_CLICK_FLAG == 1 && PrescriptionListActivity.CHILDLONG_CLICK_FLAG == 0) {
                if (groupPosition == PrescriptionListActivity.pos) {
                    base.setBackgroundResource(R.color.colorAccent);
                }
            } else if (PrescriptionListActivity.LONG_CLICK_FLAG == 0) {
                base.setBackgroundColor(Color.TRANSPARENT);
            }
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);
            lblListHeader.setTextSize(24);

        }else if(PrescriptionListActivity.ACTIVITY_FLAG.equals("Refill")){
            if (RefillListActivity.REFILL_FLAG == 1) {
                if (RefillListActivity.child_pos.containsKey(groupPosition)) {
                    base.setBackgroundColor(Color.parseColor("#FFFF00"));
                }
                else {
                    base.setBackgroundColor(Color.TRANSPARENT);
                }
            }
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);
            lblListHeader.setTextSize(24);
        }


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}