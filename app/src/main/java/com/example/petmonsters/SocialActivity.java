package com.example.petmonsters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SocialActivity extends AppCompatActivity {

    private LinearLayout layout, currentBox, currentHouse;
    private EditText addFriend_et;

    private ImageView addFriend_iv, showRequests_iv;

    private PetMonstersApplication app;

    private View.OnClickListener addFriendListener;
    private View.OnClickListener requestListener;

    private RequestsFragment requestsFragment;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        getAppContext();

        assignViewFields();
        createListeners();
        assignListeners();
        assignFragments();

        try {
            generateFriends();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void assignFragments(){
        requestsFragment = new RequestsFragment();
    }

    private void createListeners() {
        requestListener = new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    openRequests();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        addFriendListener = new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    addFriend();
                } catch (JSONException | ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                    Utilities.ShowShortToast(getApplicationContext(), "Unable to send request. Make sure this user exists.");
                }
            }
        };

    }

    private void assignListeners(){
        addFriend_iv.setOnClickListener(addFriendListener);
        showRequests_iv.setOnClickListener(requestListener);
    }

    private void getAppContext(){
        app = (PetMonstersApplication) getApplicationContext();
    }

    private void assignViewFields(){
        layout = findViewById(R.id.social_layout);
        addFriend_iv = findViewById(R.id.add_iv);
        showRequests_iv = findViewById(R.id.requests_iv);
        addFriend_et = findViewById(R.id.add_et);
    }

    private void addFriend() throws JSONException, ExecutionException, InterruptedException {
        User tempUser = DatabaseHandler.readUser(addFriend_et.getText().toString(), false);
        DatabaseHandler.CreateRequest(new Request(app.getUser().getUsername(), tempUser.getUsername()));
        Utilities.ShowShortToast(this, "Friend request sent.");
    }

    private void generateFriends() throws JSONException, ExecutionException, InterruptedException {
        for(int i = 0; i < app.getUser().getFriends().toArray().length; i++) {
            String name = app.getUser().getFriends().get(i).getUsername();
            updateFriendPetData(name);
            String s = app.getCurrentFriendPet().getSprite();
            generateHouse(i, name, s);
        }

    }

    public void generateHouse(int index, String name, String sprite) {

        if (index % 2 == 0) {
            currentBox = new LinearLayout(this);
            LinearLayout.LayoutParams boxParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            currentBox.setLayoutParams(boxParameters);
            boxParameters.setMargins(0, 10, 0, 10);
            boxParameters.setLayoutDirection(LinearLayout.HORIZONTAL);
            layout.addView(currentBox);

        }


        currentHouse = new LinearLayout(this);
        LinearLayout.LayoutParams houseParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,1);
        currentHouse.setLayoutParams(houseParameters);
        currentHouse.setOrientation(LinearLayout.VERTICAL);


        RelativeLayout currentContainer = new RelativeLayout(this);
        RelativeLayout.LayoutParams tempLayout = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        currentContainer.setLayoutParams(tempLayout);
        currentContainer.setGravity(Gravity.CENTER);

        currentHouse.isClickable();
        currentHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                app.setCurrentFriend(app.getUser().getFriends().get(index));

                try {
                    updateFriendPetData(name);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (Utilities.showAdd()){
                    Intent a = new Intent(SocialActivity.this, AddActivity.class);
                    a.putExtra("nextActivity", FriendActivity.class);
                    startActivity(a);
                } else {
                    Intent intent = new Intent(SocialActivity.this, FriendActivity.class);
                    startActivity(intent);
                }
            }
        });


        TextView friendName = new TextView(this);
        friendName.setText(name);
        LinearLayout.LayoutParams textParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        friendName.setLayoutParams(textParameters);
        friendName.setGravity(Gravity.CENTER);


        Drawable img = getResources().getDrawable(R.drawable.hat_halo);
        ImageView image = new ImageView(this);
        LinearLayout.LayoutParams contentParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        image.setLayoutParams(contentParameters);
        image.setImageDrawable(img);
        contentParameters.gravity = Gravity.CENTER;


        Resources res = app.getResources();
        int drawableId = res.getIdentifier(sprite, "drawable", getApplicationContext().getPackageName());

        Drawable friendPet = getResources().getDrawable(drawableId);
        ImageView imagePet = new ImageView(this);
        LinearLayout.LayoutParams petParameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        imagePet.setLayoutParams(petParameters);
        imagePet.setImageDrawable(friendPet);
        petParameters.setMargins(35, 40, 0, 0);
        petParameters.width = 200;
        petParameters.height = 200;

        currentHouse.addView(friendName);
        currentHouse.addView(currentContainer);
        currentContainer.addView(image);
        currentContainer.addView((imagePet));
        currentBox.addView(currentHouse);
    }

    private void updateFriendPetData(String friendName) throws JSONException, ExecutionException, InterruptedException {
        Pet pet = DatabaseHandler.readPet(friendName);
        app.setCurrentFriendPet(pet);
    }

    private void openRequests() throws JSONException, ExecutionException, InterruptedException {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (requestsFragment.isAdded()) {
            fragmentTransaction.remove(requestsFragment);
            layout.removeAllViews();

            generateFriends();}
        else{fragmentTransaction.add(R.id.fragment_container, requestsFragment);
            fragmentTransaction.show(requestsFragment);}
        fragmentTransaction.commit();
    }
}