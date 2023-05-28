package com.example.petmonsters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoreFragment extends Fragment {

    private LinearLayout layout, currentBox;
    private PetMonstersApplication app;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        getAppContext();

        layout = view.findViewById(R.id.store_main_layout);

        generateItems();

        return view;
    }

    private void generateItems(){
        for(int i = 0; i < app.getStore().toArray().length; i++){
            String sprite = app.getStore().get(i).getSprite();
            String name = app.getStore().get(i).getName();
            System.out.println(name);
            int type =  app.getStore().get(i).getType();

            generateImage(sprite, i, app.getContext(), name, type);
        }
    }

    private void getAppContext(){
        app = (PetMonstersApplication) getActivity().getApplicationContext();
    }

    public void generateImage(String sprite, int index, Context context, String name, int type) {

        if (index % 3 == 0) {
            currentBox = new LinearLayout(context);
            LinearLayout.LayoutParams boxParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            currentBox.setLayoutParams(boxParameters);
            boxParameters.setMargins(0, 10, 0, 10);
            boxParameters.setLayoutDirection(LinearLayout.HORIZONTAL);
            layout.addView(currentBox);

        }

        Resources res = app.getResources();
        int drawableId = res.getIdentifier(sprite, "drawable", getContext().getApplicationContext().getPackageName());
        Drawable img = app.getContext().getResources().getDrawable(drawableId);
        ImageView image = new ImageView(context);
        LinearLayout.LayoutParams contentParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        image.setLayoutParams(contentParameters);
        image.setImageDrawable(img);
        image.isClickable();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type){
                    case 0:
                        app.getPet().setHat(sprite);


                        break;
                    case 1:
                        app.getPet().setShoes(sprite);
                        break;
                }

                try { UpdatePet(app.getPet().getOwner(), type, sprite); }
                catch (ExecutionException |InterruptedException e) { e.printStackTrace(); }

                if(!app.getUser().getItems().contains(name)){
                    app.getUser().getItems().add(name);

                    try { UpdateUser(app.getUser().getUsername(), name, app.getUser().getItems()); }
                    catch (ExecutionException | InterruptedException e) { e.printStackTrace(); }
                }

                ((HomeActivity) getActivity()).showPet();

            }
        });

        contentParameters.width = 500;
        contentParameters.height = 500;

        currentBox.addView(image);
    }

    private void UpdatePet(String owner, int type, String sprite) throws ExecutionException, InterruptedException {
        if(DatabaseHandler.updatePet(owner, type, sprite)) Utilities.ShowShortToast(getContext().getApplicationContext(), "Item saved");
        else Utilities.ShowShortToast(getContext().getApplicationContext(), "Item not saved");
    }

    private void UpdateUser(String username, String name, ArrayList<String> items) throws ExecutionException, InterruptedException {
        if(DatabaseHandler.updateUser(username, name, items)) Utilities.ShowShortToast(getContext().getApplicationContext(), "Item purchased");
        else Utilities.ShowShortToast(getContext().getApplicationContext(), "Item not purchased");
    }
}