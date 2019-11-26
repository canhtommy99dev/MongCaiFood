package com.alexmedia.mongcaifood.ui.thongbao;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alexmedia.mongcaifood.Adapter.AdapterDuLich;
import com.alexmedia.mongcaifood.Activity.ChoMongCaiActivity;
import com.alexmedia.mongcaifood.Model.ModelDulich;
import com.alexmedia.mongcaifood.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ThongBaoFragment extends Fragment {

    ListView listViewDuLich;
    List<ModelDulich> modelDuliches;
    AdapterDuLich adapterDuLich;
    DatabaseReference dataDuLich;
    public static final String ID = "id";
    public static final String TENDULICH = "tendulich";
    public static final String STREETVIEW = "linkstreetview";
    public static final String YOUTUBELINK = "youtubelink";
    public static final String BAIVIETGIOITHIEU = "baivietgioithieu";
    public static final String IMAGEDULICH = "imagedulich";
    Intent intent;

    public static ThongBaoFragment newInstance() {
        return new ThongBaoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.thong_bao_fragment, container, false);

        listViewDuLich = view.findViewById(R.id.listdulich);
        modelDuliches = new ArrayList<>();
        dataDuLich = FirebaseDatabase.getInstance().getReference("DuLich");
        adapterDuLich = new AdapterDuLich(getContext(),modelDuliches,R.layout.adapter_dulich);
        intent = getActivity().getIntent();
        listViewDuLich.setAdapter(adapterDuLich);
        listViewDuLich.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ChoMongCaiActivity.class);
                ModelDulich modelDulich = modelDuliches.get(position);
                intent.putExtra(ID,modelDulich.getId());
                intent.putExtra(TENDULICH,modelDulich.getNamedulich());
                intent.putExtra(STREETVIEW,modelDulich.getLinkgoogle());
                intent.putExtra(YOUTUBELINK,modelDulich.getLinkyoutube());
                intent.putExtra(BAIVIETGIOITHIEU,modelDulich.getBaidang());
                intent.putExtra(IMAGEDULICH,modelDulich.getImagedulich());
                startActivity(intent);
            }
        });
        dataDuLich.addValueEventListener(valueEventListener);
        return view;
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            modelDuliches.clear();
            for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                ModelDulich modelDulich = dataSnapshot1.getValue(ModelDulich.class);
                modelDuliches.add(modelDulich);
                adapterDuLich.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
