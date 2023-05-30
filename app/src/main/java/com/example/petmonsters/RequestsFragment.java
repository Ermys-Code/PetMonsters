package com.example.petmonsters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class RequestsFragment extends Fragment {
    private LinearLayout layout;
    private PetMonstersApplication app;


    private static final String ARG_PARAM1 = Constants.params_1;
    private static final String ARG_PARAM2 = Constants.params_2;


    private String mParam1;
    private String mParam2;

    public RequestsFragment() {

    }


    public static RequestsFragment newInstance(String param1, String param2) {
        RequestsFragment fragment = new RequestsFragment();
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
        View view = inflater.inflate(R.layout.fragment_requests, container, false);
        getAppContext();

        getScreenElements(view);

        try {
            getRequests();
        }
        catch (Exception e) { System.out.println(e.getMessage()); }

        return view;
    }

    private void getAppContext(){
        app = (PetMonstersApplication) getActivity().getApplicationContext();
    }

    private void getScreenElements(View view){
        layout = view.findViewById(R.id.requests_main_layout);

    }

    private void getRequests() throws Exception {
        ArrayList<Request> requests = DatabaseHandler.readRequest(app.getUser().getUsername());
        for(int i=0; i < requests.size(); i++){
            listRequests(requests.get(i).getFrom(), i, app.getContext());
        }
    }

    public void listRequests(String from, int index, Context context){
        LinearLayout container = new LinearLayout(context);
        LinearLayout.LayoutParams boxParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        container.setLayoutParams(boxParameters);
        boxParameters.setMargins(0, 10, 0, 10);
        boxParameters.setLayoutDirection(LinearLayout.HORIZONTAL);


        TextView name = new TextView(context);
        name.setText(from);
        LinearLayout.LayoutParams textParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        name.setLayoutParams(textParameters);
        name.setTextColor(getResources().getColor(R.color.black));
        name.setTypeface(getResources().getFont(R.font.grobold));
        name.setTextSize(40);
        textParameters.setMargins(20,0,0,0);

        View view_space = new View(context);
        LinearLayout.LayoutParams viewParam = new LinearLayout.LayoutParams(0, 0, 1);
        view_space.setLayoutParams(viewParam);

        ImageView accept = new ImageView(context);
        Drawable a = getResources().getDrawable(R.drawable.accept_firend_iv);
        accept.setImageDrawable(a);
        LinearLayout.LayoutParams contentParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        accept.setLayoutParams(contentParameters);
        accept.isClickable();
        accept.setOnClickListener(view -> {
            try {
                addFriend(from);
            }
            catch (Exception e) { System.out.println(e.getMessage()); }


            deleteRequest(from);

        });


        ImageView deny = new ImageView(context);
        Drawable b = getResources().getDrawable(R.drawable.deny_firend_iv);
        deny.setImageDrawable(b);
        deny.setLayoutParams(contentParameters);
        deny.isClickable();
        deny.setOnClickListener(view -> deleteRequest(from));
        contentParameters.width = 120;
        contentParameters.height = 120;
        contentParameters.setMargins(10,10,20,0);

        container.addView(name);
        container.addView(view_space);
        container.addView(accept);
        container.addView(deny);
        layout.addView(container);
    }

    public void deleteRequest(String from){
        try {
            DatabaseHandler.deleteRequest(from, app.getUser().getUsername());
            layout.removeAllViews();
            getRequests();
        }
        catch (Exception e) { System.out.println(e.getMessage()); }
    }

    public void addFriend(String newFriend) throws Exception {
        User friend = DatabaseHandler.readUser(newFriend,true);
        app.getUser().getFriends().add(friend);
        friend.getFriends().add(app.getUser());

        ArrayList<String> friends = new ArrayList<>();
        ArrayList<String> friendsOfFriend = new ArrayList<>();

        for( int i = 0; i < app.getUser().getFriends().size(); i++){
            friends.add(app.getUser().getFriends().get(i).getUsername());
        }

        for( int i = 0; i < friend.getFriends().size(); i++){
            friendsOfFriend.add(friend.getFriends().get(i).getUsername());
        }

        DatabaseHandler.updateUserFriends(app.getUser().getUsername(), friends);
        DatabaseHandler.updateUserFriends(friend.getUsername(), friendsOfFriend);
    }
}