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

    @GET("/users/{username}")
    Call<Github> user(
            @Path("username") String username
    );
}
