package fernanda.githubAPITest.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fernanda.githubAPITest.App;
import fernanda.githubAPITest.Github;
import fernanda.githubAPITest.R;
import fernanda.githubAPITest.RestAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by fernanda on 03/11/16.
 */

public class UserPage extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.username) TextView username;
    @BindView(R.id.avatar) ImageView avatar;
    @BindView(R.id.nameField) TextView nameField;
    @BindView(R.id.emailField) TextView emailField;
    @BindView(R.id.bioField) TextView bioField;
    @BindView(R.id.followersField) TextView followersField;
    @BindView(R.id.followingField) TextView followingField;
    @BindView(R.id.repoNumField) TextView repoNumField;

    @Inject
    Retrofit retrofit;

    Call<Github> callUser;
    Call<List<Github>> callFollowers;
    Call<List<Github>> callFollowing;
    Call<List<Github>> callRepo;

    public UserPage() {
        super();
    }
    String user = null;

    public void setUsername(String user) {
        this.user = user;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Create a retrofit call object
        final RestAPI service = retrofit.create(RestAPI.class);

        callUser = service.user(user);
        callUser.enqueue(new Callback<Github>() {
            @Override
            public void onResponse(Call<Github> call, Response<Github> response) {
                //Set the response to the textview
                Github user = response.body();
                if(user != null) {
                    username.setText(user.getLogin());
                    Picasso.with(getContext()).load(user.getAvatar_url()).into(avatar);
                    nameField.setText(user.getName());
                    emailField.setText(user.getEmail());
                    bioField.setText(user.getBio());
                    followingField.setText("Following: " + user.getFollowing());
                    followersField.setText("Followers: " + user.getFollowers());
                    repoNumField.setText("Repos: " + user.getPublic_repos());
                } else {
                    nameField.setText("USER DOESN'T EXIST");
                }
            }

            @Override
            public void onFailure(Call<Github> call, Throwable t) {}
        });

        followersField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callFollowers = service.followers(user);
                callFollowers.enqueue(new Callback<List<Github>>() {
                @Override
                public void onResponse(Call<List<Github>> call, Response<List<Github>> response) {
                    //Set the response to the textview
                    List<Github> followers = response.body();
                    String text = "";
                    for(Github follower : followers) {
                        text = text + follower.getLogin() + "\n";
                    }
                    showSimpleDialog(text);
                }

                @Override
                public void onFailure(Call<List<Github>> call, Throwable t) {}
             });
            }
        });

        followingField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callFollowing = service.following(user);
                callFollowing.enqueue(new Callback<List<Github>>() {
                    @Override
                    public void onResponse(Call<List<Github>> call, Response<List<Github>> response) {
                        //Set the response to the textview
                        List<Github> theFollowing = response.body();
                        String text = "";
                        for(Github following : theFollowing) {
                            text = text + following.getLogin() + "\n";
                        }
                        showSimpleDialog(text);
                    }
                    @Override
                    public void onFailure(Call<List<Github>> call, Throwable t) {}
                });
            }
        });

        repoNumField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRepo = service.repos(user);
                callRepo.enqueue(new Callback<List<Github>>() {
                    @Override
                    public void onResponse(Call<List<Github>> call, Response<List<Github>> response) {
                        //Set the response to the textview
                        List<Github> repos = response.body();
                        String text = "";
                        for(Github repo : repos) {
                            text = text + repo.getName() + "\n";
                        }
                        showSimpleDialog(text);
                    }
                    @Override
                    public void onFailure(Call<List<Github>> call, Throwable t) {}
                });
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).getNetComponent().inject(this);
    }


    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void showSimpleDialog(String message) {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
//        builder.setTitle("AlertDialog Title");
        builder.setMessage(message);
        builder.setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        // Create the AlertDialog object and return it
        builder.create().show();
    }

}



//        call = service.contributors("square", "retrofit");
//
//    //Enque the call
//    call.enqueue(new Callback<List<Github>>() {
//        @Override
//        public void onResponse(Call<List<Github>> call, Response<List<Github>> response) {
//            //Set the response to the textview
//            List<Github> contributors = response.body();
//            String text = "";
//            for(Github contributor : contributors) {
//                text = text + contributor.getLogin() + " " + contributor.getContributions() + " " + contributor.getId() + "\n";
//            }
//            textView.setText(text);
//        }
//
//        @Override
//        public void onFailure(Call<List<Github>> call, Throwable t) {
//            //Set the error to the textview
//            textView.setText(t.toString());
//        }
//    });
