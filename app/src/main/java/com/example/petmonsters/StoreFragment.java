package com.example.petmonsters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class StoreFragment extends Fragment {

    private LinearLayout layout, currentBox;
    private PetMonstersApplication app;

    private static final String ARG_PARAM1 = Constants.params_1;
    private static final String ARG_PARAM2 = Constants.params_2;

    private String mParam1;
    private String mParam2;

    public StoreFragment() {

    }

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
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        getAppContext();

        getScreenElements(view);

        generateItems();

        return view;
    }

    private void getScreenElements(View view){
        layout = view.findViewById(R.id.store_main_layout);
    }

    private void generateItems(){
        for(int i = 0; i < app.getStore().toArray().length; i++){
            String sprite = app.getStore().get(i).getSprite();
            String name = app.getStore().get(i).getName();
            System.out.println(name);
            int type =  app.getStore().get(i).getType();
            int value = app.getStore().get(i).getValue();

            generateImage(sprite, i, app.getContext(), name, type, value);
        }
    }

    private void getAppContext(){
        app = (PetMonstersApplication) getActivity().getApplicationContext();
    }

    public void generateImage(String sprite, int index, Context context, String name, int type, int value) {

        if (index % 3 == 0) {
            currentBox = new LinearLayout(context);
            LinearLayout.LayoutParams boxParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            currentBox.setLayoutParams(boxParameters);
            boxParameters.setMargins(0, 10, 0, 10);
            boxParameters.setLayoutDirection(LinearLayout.HORIZONTAL);
            layout.addView(currentBox);

        }

        LinearLayout layout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(500, 500, 1.0f);
        params.bottomMargin = 50;
        layout.setLayoutParams(params);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.isClickable();
        layout.setOnClickListener(view -> {

            if(!app.getUser().getItems().contains(sprite)){
                if(app.getUser().getMoney() > value) {

                    try {
                        app.getUser().setMoney(app.getUser().getMoney() - value);
                        app.getUser().getItems().add(sprite);
                        UpdateUser(app.getUser().getUsername(), app.getUser().getItems(), app.getUser().getMoney());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                else{
                    Utilities.ShowShortToast(getContext().getApplicationContext(), Constants.not_enough_currency);
                }
            }

            if(app.getUser().getItems().contains(sprite)){
                try {
                    UpdatePet(app.getPet().getOwner(), type, sprite);
                    switch (type){
                        case 0:
                            app.getPet().setHat(sprite);
                            break;
                        case 1:
                            app.getPet().setShoes(sprite);
                            break;
                    }
                }
                catch (Exception e) { System.out.println(e.getMessage()); }
            }

            ((HomeActivity) getActivity()).showPet();

        });

        Resources res = app.getResources();
        int drawableId = res.getIdentifier(sprite, Constants.identifier_type_drawable, getContext().getApplicationContext().getPackageName());
        Drawable img = app.getContext().getResources().getDrawable(drawableId);
        ImageView image = new ImageView(context);
        LinearLayout.LayoutParams contentParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        image.setLayoutParams(contentParameters);
        image.setImageDrawable(img);

        layout.addView(image);

        TextView valueText = new TextView(context);
        LinearLayout.LayoutParams valueParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        valueParams.gravity = Gravity.CENTER;
        valueText.setLayoutParams(valueParams);
        valueText.setText(Integer.toString(value));
        valueText.setTextColor(context.getColor(R.color.black));
        valueText.setTypeface(getResources().getFont(R.font.grobold));
        valueText.setTextSize(30);

        layout.addView(valueText);

        currentBox.addView(layout);
    }

    private void UpdatePet(String owner, int type, String sprite) throws Exception {
        if(DatabaseHandler.updatePet(owner, type, sprite)) Utilities.ShowShortToast(getContext().getApplicationContext(), Constants.item_saved);
        else Utilities.ShowShortToast(getContext().getApplicationContext(), Constants.item_not_saved);
    }

    private void UpdateUser(String username, ArrayList<String> items, int money) throws Exception {
        if(DatabaseHandler.updateUserMoney(username, money)) Utilities.ShowShortToast(getContext().getApplicationContext(), "Money updated");
        if(DatabaseHandler.updateUser(username, items)) Utilities.ShowShortToast(getContext().getApplicationContext(), Constants.item_purchased);
        else Utilities.ShowShortToast(getContext().getApplicationContext(), Constants.item_not_purchased);
    }
}