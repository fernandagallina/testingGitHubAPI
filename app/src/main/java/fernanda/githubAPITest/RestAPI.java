package fernanda.githubAPITest;

import java.util.List;

import fernanda.githubAPITest.model.GitHub;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestAPI {

    @GET("/repos/{owner}/{repo}/contributors")
    Single<List<GitHub>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );

    // Get user information
    @GET("/users/{username}")
    Observable<GitHub> user(
            @Path("username") String username
    );

    // Get followers of an specific user
    @GET("/users/{username}/followers")
    Single<List<GitHub>> followers(
            @Path("username") String username
    );

    // Get people that specific user is following
    @GET("/users/{username}/following")
    Single<List<GitHub>> following(
            @Path("username") String username
    );

    // Get user's repos
    @GET("/users/{username}/repos")
    Single<List<GitHub>> repos(
            @Path("username") String username
    );

    // Get organization information
    @GET("/orgs/{orgname}")
    Observable<GitHub> org(
            @Path("orgname") String orgname
    );

    // Get organization's members
    @GET("/orgs/{orgname}/members")
    Single<List<GitHub>> orgMembers(
            @Path("orgname") String orgname
    );

    // Get organization's public members
    @GET("/orgs/{orgname}/public_members")
    Single<List<GitHub>> orgPublicMembers(
            @Path("orgname") String orgname
    );

    // Get organization's repos
    @GET("/orgs/{orgname}/repos")
    Single<List<GitHub>> reposOrg(
            @Path("orgname") String orgname
    );
}
