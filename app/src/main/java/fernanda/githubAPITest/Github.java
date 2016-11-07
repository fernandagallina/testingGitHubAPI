package fernanda.githubAPITest;

/**
 * Created by fernanda on 03/11/16.
 */

public class Github {

    String login, email, name, bio, descripion;
    String avatar_url, html_url;
    int followers, following, public_repos;
    int contributions;
    int id;

    public String getDescripion() {
        return descripion;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getBio() {
        return bio;
    }

    public int getId() {
        return id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public int getContributions() {
        return contributions;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public int getPublic_repos() {
        return public_repos;
    }
}