package com.example.saad.afinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class LandmarkList  extends ArrayAdapter<LandMark> {

   private Activity context;
   private List<LandMark> landMarkList;

   public LandmarkList(Activity context, List<LandMark> landMarkList){

       super(context, R.layout.list_layout, landMarkList);
       this.context = context;
       this.landMarkList = landMarkList;
   }

   public View getView(int position, View convertView, ViewGroup parent){
       LayoutInflater inflater = context.getLayoutInflater();

       View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

       TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);

       LandMark landMark = landMarkList.get(position);

       textViewName.setText(landMark.getLandmarkname());

       return listViewItem;
   }

}
