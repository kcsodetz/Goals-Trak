package edu.sodetzpurdue.goals_trak;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Ken Sodetz on 2/25/2017.
 */

public class DataAdapter extends BaseAdapter{
    private final ArrayList mData;

    public DataAdapter(Map<String, GoalsManager> map) {
        mData = new ArrayList();
        mData.addAll(map.entrySet());
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Map.Entry<String, GoalsManager> getItem(int position) {
        return (Map.Entry) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        //TODO Implement ID logic
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null){
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_goals_list, parent, false);
        }
        else {
            result = convertView;
        }
        Map.Entry<String, GoalsManager> item = getItem(position);
        System.out.println(item.getKey());
        // TODO replace findViewById by ViewHolder
        ((TextView) result.findViewById(android.R.id.text1)).setText(item.getKey());
        //((TextView) result.findViewById(android.R.id.text2)).setText(item.getValue());
        return result;
    }
}
