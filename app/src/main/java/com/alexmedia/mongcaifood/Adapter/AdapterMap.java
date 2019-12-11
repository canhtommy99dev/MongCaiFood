package com.alexmedia.mongcaifood.Adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.alexmedia.mongcaifood.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class AdapterMap implements GoogleMap.InfoWindowAdapter {

    private Activity context;

    public AdapterMap(Activity ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = context.getLayoutInflater()
                .inflate(R.layout.adapter_map, null);

        TextView tencuahang = view.findViewById(R.id.nameCuaHang);
        TextView diachi = view.findViewById(R.id.diachicuahang);
        tencuahang.setText(marker.getTitle());
        diachi.setText(marker.getSnippet());
        return view;
    }
}
