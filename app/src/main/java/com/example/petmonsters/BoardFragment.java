package com.example.petmonsters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import kotlin.DeprecationLevel;

public class BoardFragment extends Fragment {

    private static final String ARG_PARAM1 = Constants.params_1;
    private static final String ARG_PARAM2 = Constants.params_2;

    private String mParam1;
    private String mParam2;

    public BoardFragment() {

    }

    public static BoardFragment newInstance(String param1, String param2) {
        BoardFragment fragment = new BoardFragment();
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

    private PetMonstersApplication app;
    private ArrayList<Message> messagesArray;
    private LinearLayout mainLayout, horizontalLayout, verticalLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_board, container, false);

        getAppContext();

        getScreenElements(view);

        try {
            getMessages();
            loadMessages();
        }
        catch (Exception e) { System.out.println(e.getMessage()); }


        return view;
    }

    private void getAppContext(){
        app = (PetMonstersApplication) getActivity().getApplicationContext();
    }

    private void getScreenElements(View view){
        mainLayout = view.findViewById(R.id.board_main_layout);
    }

    private void getMessages() throws Exception {
        messagesArray = DatabaseHandler.readMessages(app.getUser().getUsername());
    }

    private void loadMessages(){
        for(int i = 0; i < messagesArray.size(); i++){
            Message messageObj = messagesArray.get(i);
            String name = messageObj.getFrom();
            String to = messageObj.getTo();
            String title = messageObj.getTitle();
            String message = messageObj.getMessage();

            generateMessage(i, app.getContext(), name, to, title, message);
        }
    }

    private void generateMessage(int index, Context context, String _name, String _to, String _title, String _message){
        if (index % 2 == 0) {
            horizontalLayout = new LinearLayout(context);
            LinearLayout.LayoutParams boxParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            horizontalLayout.setLayoutParams(boxParameters);
            boxParameters.setMargins(0, 10, 0, 10);
            boxParameters.setLayoutDirection(LinearLayout.HORIZONTAL);
            mainLayout.addView(horizontalLayout);
        }


        verticalLayout = new LinearLayout(context);
        LinearLayout.LayoutParams verticalLayoutParams = new LinearLayout.LayoutParams(250, 400, 1.0f);
        verticalLayout.setLayoutParams(verticalLayoutParams);
        verticalLayoutParams.setMargins(50, 50, 50, 50);
        verticalLayout.setOrientation(LinearLayout.VERTICAL);
        verticalLayout.setBackgroundColor(getContext().getColor(R.color.note_background));

        NestedScrollView scrollView = new NestedScrollView(context);
        LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(scrollParams);

        LinearLayout miniLayout = new LinearLayout(context);
        LinearLayout.LayoutParams miniParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        miniLayout.setLayoutParams(miniParameters);
        miniLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView name = new TextView(context);
        name.setText(_name);
        LinearLayout.LayoutParams nameParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        nameParameters.bottomMargin = 30;
        nameParameters.leftMargin = 20;
        nameParameters.rightMargin = 20;
        name.setLayoutParams(nameParameters);
        name.setGravity(Gravity.CENTER);
        name.setTextColor(getContext().getColor(R.color.note_letter));
        name.setTextSize(20);
        name.setTypeface(getResources().getFont(R.font.grobold));


        ImageView deleteIv = new ImageView(context);
        deleteIv.setImageDrawable(app.getDrawable(R.drawable.chincheta));
        LinearLayout.LayoutParams deleteIvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        deleteIvParams.width = 75;
        deleteIvParams.topMargin = 10;
        deleteIvParams.height = 75;
        deleteIvParams.gravity = Gravity.CENTER;
        deleteIv.setLayoutParams(deleteIvParams);

        deleteIv.setOnClickListener(v -> {
            try {
                DatabaseHandler.deleteMessage(_name, _to, _title, _message);
                Utilities.ShowShortToast(app.getContext(), Constants.note_deleted_succesfully);
                horizontalLayout.removeView(verticalLayout);
                reload();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                Utilities.ShowShortToast(app.getContext(), Constants.error_deleting_note);
            }
        });


        verticalLayout.addView(deleteIv);
        verticalLayout.addView(name);

        TextView title = new TextView(context);
        title.setText(_title);
        LinearLayout.LayoutParams titleParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParameters.bottomMargin = 15;
        titleParameters.leftMargin = 20;
        titleParameters.rightMargin = 20;
        title.setLayoutParams(titleParameters);
        title.setGravity(Gravity.LEFT);
        title.setTextColor(getContext().getColor(R.color.note_letter));
        title.setTextSize(20);
        title.setTypeface(getResources().getFont(R.font.grobold));



        verticalLayout.addView(title);

        TextView message = new TextView(context);
        message.setText(_message);
        LinearLayout.LayoutParams messageParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        messageParameters.leftMargin = 20;
        messageParameters.rightMargin = 20;
        message.setLayoutParams(messageParameters);
        message.setGravity(Gravity.LEFT);
        message.setTextColor(getContext().getColor(R.color.note_letter));
        message.setTypeface(getResources().getFont(R.font.grobold));


        scrollView.addView(message);

        verticalLayout.addView(scrollView);
        horizontalLayout.addView(verticalLayout);
    }

    private void reload(){
        try {
            getMessages();
            loadMessages();
        }
        catch (Exception e) { System.out.println(e.getMessage()); }

    }
}