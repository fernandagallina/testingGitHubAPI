package fernanda.githubAPITest.ui;

import fernanda.githubAPITest.model.GitHub;

public interface OrganizationPageContract {

    interface View {

        void showError(String message);

        void showSimpleDialog(String title, String message);

        void showOrganization(GitHub gitHub);
    }

    interface Actions extends ActionListener{

        void loadOrganization(String organization);

    }

    interface ActionListener {

        void loadMembers(String org);
    }
}
