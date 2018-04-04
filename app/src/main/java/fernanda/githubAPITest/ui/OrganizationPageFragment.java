package fernanda.githubAPITest.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fernanda.githubAPITest.App;
import fernanda.githubAPITest.R;
import fernanda.githubAPITest.injections.component.DaggerOrganizationPageComponent;
import fernanda.githubAPITest.injections.module.OrganizationPageModule;
import fernanda.githubAPITest.model.GitHub;

/**
 * Created by fernanda on 07/11/16.
 */

public class OrganizationPageFragment extends Fragment implements OrganizationPageContract.View {

    @BindView(R.id.orgname) TextView orgname;
    @BindView(R.id.avatar_org) ImageView avatar;
    @BindView(R.id.orgnameField) TextView orgnameField;
    @BindView(R.id.htmlUrl) TextView htmlUrl;
    @BindView(R.id.descriptionField) TextView descriptionField;
    @BindView(R.id.orgPublicMembersField) TextView orgPublicMembersField;
    @BindView(R.id.orgMembersField) TextView orgMembersField;
    @BindView(R.id.publicRepoNumField) TextView repoNumField;

    @Inject
    OrganizationPagePresenter presenter;

    //    Call<GitHub> callOrg;
//    Call<List<GitHub>> callMembers;
//    Call<List<GitHub>> callPublicMembers;
    private Unbinder unbinder;

//    Call<List<GitHub>> callRepo;

    String organization;

    public void setOrgName(String organization) {
        this.organization = organization;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.loadOrganization(organization);

        orgMembersField.setOnClickListener(__ -> presenter.loadMembers(organization));
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
        getComponent();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showSimpleDialog(String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Dismiss", (dialog, id) -> dialog.cancel());
        builder.create().show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        orgnameField.setText("ORGANIZATION DOESN'T EXIST");
    }

    @Override
    public void showOrganization(GitHub gitHub) {
        orgname.setText(gitHub.login);
        Picasso.with(getContext()).load(gitHub.avatar_url).into(avatar);
        orgnameField.setText(gitHub.name);
        htmlUrl.setText(gitHub.html_url);
        descriptionField.setText(gitHub.description);
        orgMembersField.setText("Members");
        orgPublicMembersField.setText("Public Members");
        repoNumField.setText("Public repos: " + gitHub.public_repos);
    }

    private void getComponent() {
        DaggerOrganizationPageComponent.builder()
                .netComponent(((App) getActivity().getApplicationContext()).getNetComponent())
                .organizationPageModule(new OrganizationPageModule(this))
                .build().inject(this);
    }
}


//        orgPublicMembersField.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callPublicMembers = service.orgPublicMembers(organization);
//                callPublicMembers.enqueue(new Callback<List<GitHub>>() {
//                    @Override
//                    public void onResponse(Call<List<GitHub>> call, Response<List<GitHub>> response) {
//                        //Set the response to the textview
//                        List<GitHub> theFollowing = response.body();
//                        String text = "";
//                        for (GitHub following : theFollowing) {
//                            text = text + following.getLogin() + "\n";
//                        }
//                        showSimpleDialog("Public Members", text);
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<GitHub>> call, Throwable t) {
//                    }
//                });
//            }
//        });

//        repoNumField.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callRepo = service.reposOrg(organization);
//                callRepo.enqueue(new Callback<List<GitHub>>() {
//                    @Override
//                    public void onResponse(Call<List<GitHub>> call, Response<List<GitHub>> response) {
//                        //Set the response to the textview
//                        List<GitHub> repos = response.body();
//                        String text = "";
//                        for (GitHub repo : repos) {
//                            text = text + repo.getName() + "\n";
//                        }
//                        showSimpleDialog("Repositories", text);
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<GitHub>> call, Throwable t) {
//                    }
//                });
//            }
//        });