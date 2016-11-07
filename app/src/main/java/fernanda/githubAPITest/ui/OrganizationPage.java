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
 * Created by fernanda on 07/11/16.
 */

public class OrganizationPage extends Fragment {

    @BindView(R.id.orgname) TextView orgname;
    @BindView(R.id.avatar_org) ImageView avatar;
    @BindView(R.id.orgnameField) TextView orgnameField;
    @BindView(R.id.htmlUrl) TextView htmlUrl;
    @BindView(R.id.descriptionField) TextView descriptionField;
    @BindView(R.id.orgPublicMembersField) TextView orgPublicMembersField;
    @BindView(R.id.orgMembersField) TextView orgMembersField;
    @BindView(R.id.publicRepoNumField) TextView repoNumField;

    @Inject
    Retrofit retrofit;

    Call<Github> callOrg;
    Call<List<Github>> callMembers;
    Call<List<Github>> callPublicMembers;
    Call<List<Github>> callRepo;

    public OrganizationPage() {
        super();
    }

    String organization = null;

    public void setOrgName(String organization) {
        this.organization = organization;
    }

    private Unbinder unbinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Create a retrofit call object
        final RestAPI service = retrofit.create(RestAPI.class);

        callOrg = service.user(organization);
        callOrg.enqueue(new Callback<Github>() {
            @Override
            public void onResponse(Call<Github> call, Response<Github> response) {
                //Set the response to the textview
                Github org = response.body();
                if(org != null) {
                    orgname.setText(org.getLogin());
                    Picasso.with(getContext()).load(org.getAvatar_url()).into(avatar);
                    orgnameField.setText(org.getName());
                    htmlUrl.setText(org.getHtml_url());
                    descriptionField.setText(org.getDescripion());
                    orgMembersField.setText("Members");
                    orgPublicMembersField.setText("Public Members");
                    repoNumField.setText("Public repos: " + org.getPublic_repos());
                } else {
                    orgnameField.setText("ORGANIZATION DOESN'T EXIST");
                }
            }

            @Override
            public void onFailure(Call<Github> call, Throwable t) {}
        });

        orgMembersField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMembers = service.orgMembers(organization);
                callMembers.enqueue(new Callback<List<Github>>() {
                    @Override
                    public void onResponse(Call<List<Github>> call, Response<List<Github>> response) {
                        //Set the response to the textview
                        List<Github> followers = response.body();
                        String text = "";
                        for(Github follower : followers) {
                            text = text + follower.getLogin() + "\n";
                        }
                        showSimpleDialog("Members", text);
                    }

                    @Override
                    public void onFailure(Call<List<Github>> call, Throwable t) {}
                });
            }
        });

        orgPublicMembersField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPublicMembers = service.orgPublicMembers(organization);
                callPublicMembers.enqueue(new Callback<List<Github>>() {
                    @Override
                    public void onResponse(Call<List<Github>> call, Response<List<Github>> response) {
                        //Set the response to the textview
                        List<Github> theFollowing = response.body();
                        String text = "";
                        for(Github following : theFollowing) {
                            text = text + following.getLogin() + "\n";
                        }
                        showSimpleDialog("Public Members", text);
                    }
                    @Override
                    public void onFailure(Call<List<Github>> call, Throwable t) {}
                });
            }
        });

        repoNumField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRepo = service.reposOrg(organization);
                callRepo.enqueue(new Callback<List<Github>>() {
                    @Override
                    public void onResponse(Call<List<Github>> call, Response<List<Github>> response) {
                        //Set the response to the textview
                        List<Github> repos = response.body();
                        String text = "";
                        for(Github repo : repos) {
                            text = text + repo.getName() + "\n";
                        }
                        showSimpleDialog("Repositories", text);
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
        View view = inflater.inflate(R.layout.fragment_org, container, false);
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
}
