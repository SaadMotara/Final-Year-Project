package com.example.saad.firebase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class landmarkList extends ArrayAdapter<landmark> {

    private Activity context;
    private List<landmark> landmarkList;

    public landmarkList(Activity context, List<landmark> landmarkList){

       super(context, R.layout.listlayout, landmarkList);
        this.context = context;
        this.landmarkList = landmarkList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listItem = inflater.inflate(R.layout.listlayout, null, true);

        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

        landmark landmark = landmarkList.get(position);

        textViewName.setText(landmark.getLandmarkname());

        return listItem;
    }


}
