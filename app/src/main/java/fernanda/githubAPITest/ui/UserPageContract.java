package fernanda.githubAPITest.ui;

import fernanda.githubAPITest.model.GitHub;

/**
 * Created by fernandagallina on 4/4/18.
 */

public interface UserPageContract {

    interface View {

        void showError(String s);

        void showUser(GitHub gitHub);
    }

    interface Actions {

        void loadUser(String user);
    }
}
