package fernanda.githubAPITest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by fernanda on 03/11/16.
 */

public interface RestAPI {

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Github>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );

    // Get user information
    @GET("/users/{username}")
    Call<Github> user(
            @Path("username") String username
    );

    // Get followers of an specific user
    @GET("/users/{username}/followers")
    Call<List<Github>> followers(
            @Path("username") String username
    );

    // Get people that specific user is following
    @GET("/users/{username}/following")
    Call<List<Github>> following(
            @Path("username") String username
    );

    // Get user's repos
    @GET("/users/{username}/repos")
    Call<List<Github>> repos(
            @Path("username") String username
    );
}
