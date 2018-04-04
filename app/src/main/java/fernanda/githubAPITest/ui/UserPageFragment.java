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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fernanda.githubAPITest.App;
import fernanda.githubAPITest.R;
import fernanda.githubAPITest.RestAPI;
import fernanda.githubAPITest.injections.component.DaggerUserPageComponent;
import fernanda.githubAPITest.injections.module.UserPageModule;
import fernanda.githubAPITest.model.GitHub;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fernanda on 03/11/16.
 */

public class UserPageFragment extends Fragment implements UserPageContract.View {

    private Unbinder unbinder;

    @BindView(R.id.username) TextView username;
    @BindView(R.id.avatar) ImageView avatar;
    @BindView(R.id.nameField) TextView nameField;
    @BindView(R.id.emailField) TextView emailField;
    @BindView(R.id.bioField) TextView bioField;
    @BindView(R.id.followersField) TextView followersField;
    @BindView(R.id.followingField) TextView followingField;
    @BindView(R.id.repoNumField) TextView repoNumField;

    String user;

    @Inject
    UserPagePresenter presenter;

    public void setUsername(String user) {
        this.user = user;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadUser(user);
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
        getComponent();
    }



//        followersField.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callFollowers = service.followers(user);
//                callFollowers.enqueue(new Callback<List<GitHub>>() {
//                @Override
//                public void onResponse(Call<List<GitHub>> call, Response<List<GitHub>> response) {
//                    //Set the response to the textview
//                    List<GitHub> followers = response.body();
//                    String text = "";
//                    for(GitHub follower : followers) {
//                        text = text + follower.getLogin() + "\n";
//                    }
//                    showSimpleDialog("Followers", text);
//                }
//
//                @Override
//                public void onFailure(Call<List<GitHub>> call, Throwable t) {}
//             });
//            }
//        });
//
//        followingField.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callFollowing = service.following(user);
//                callFollowing.enqueue(new Callback<List<GitHub>>() {
//                    @Override
//                    public void onResponse(Call<List<GitHub>> call, Response<List<GitHub>> response) {
//                        //Set the response to the textview
//                        List<GitHub> theFollowing = response.body();
//                        String text = "";
//                        for(GitHub following : theFollowing) {
//                            text = text + following.getLogin() + "\n";
//                        }
//                        showSimpleDialog("Following", text);
//                    }
//                    @Override
//                    public void onFailure(Call<List<GitHub>> call, Throwable t) {}
//                });
//            }
//        });
//
//        repoNumField.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callRepo = service.repos(user);
//                callRepo.enqueue(new Callback<List<GitHub>>() {
//                    @Override
//                    public void onResponse(Call<List<GitHub>> call, Response<List<GitHub>> response) {
//                        //Set the response to the textview
//                        List<GitHub> repos = response.body();
//                        String text = "";
//                        for(GitHub repo : repos) {
//                            text = text + repo.getName() + "\n";
//                        }
//                        showSimpleDialog("Repositories", text);
//                    }
//                    @Override
//                    public void onFailure(Call<List<GitHub>> call, Throwable t) {}
//                });
//            }
//        });


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ((App) getActivity().getApplication()).getNetComponent().inject(this);
//    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void showSimpleDialog(String title, String message) {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(title);
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

    private void getComponent() {
        DaggerUserPageComponent.builder()
                .netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .userPageModule(new UserPageModule(this))
                .build().inject(this);
    }

    @Override
    public void showError(String s) {

    }

    @Override
    public void showUser(GitHub user) {
        username.setText(user.login);
        Picasso.with(getContext()).load(user.avatar_url).into(avatar);
        nameField.setText(user.name);
        emailField.setText(user.email);
        bioField.setText(user.bio);
        followingField.setText("Following: " + user.following);
        followersField.setText("Followers: " + user.followers);
        repoNumField.setText("Repos: " + user.public_repos);
    }
}