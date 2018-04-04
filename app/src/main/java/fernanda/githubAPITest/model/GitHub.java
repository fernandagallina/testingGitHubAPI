package fernanda.githubAPITest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GitHub implements Parcelable {

    public String login, email, name, bio, description;
    public String avatar_url, html_url;
    public int followers, following, public_repos;
    public int contributions;
    public int id;

    protected GitHub(Parcel in) {
        login = in.readString();
        email = in.readString();
        name = in.readString();
        bio = in.readString();
        description = in.readString();
        avatar_url = in.readString();
        html_url = in.readString();
        followers = in.readInt();
        following = in.readInt();
        public_repos = in.readInt();
        contributions = in.readInt();
        id = in.readInt();
    }

    public static final Creator<GitHub> CREATOR = new Creator<GitHub>() {
        @Override
        public GitHub createFromParcel(Parcel in) {
            return new GitHub(in);
        }

        @Override
        public GitHub[] newArray(int size) {
            return new GitHub[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(email);
        dest.writeString(name);
        dest.writeString(bio);
        dest.writeString(description);
        dest.writeString(avatar_url);
        dest.writeString(html_url);
        dest.writeInt(followers);
        dest.writeInt(following);
        dest.writeInt(public_repos);
        dest.writeInt(contributions);
        dest.writeInt(id);
    }
}