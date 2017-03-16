package org.wickedsource.budgeteer.web.pages.user.login;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.wickedsource.budgeteer.service.user.User;
import org.wickedsource.budgeteer.service.user.UserService;
import org.wickedsource.budgeteer.web.BudgeteerSession;
import org.wickedsource.budgeteer.web.Mount;
import org.wickedsource.budgeteer.web.pages.base.dialogpage.DialogPage;
import org.wickedsource.budgeteer.web.pages.user.selectproject.SelectProjectPage;

import javax.servlet.http.HttpServletRequest;

@Mount("/login")
public class LoginPage extends DialogPage {

    @SpringBean
    private UserService userService;

    public LoginPage() {

        HttpServletRequest request = (HttpServletRequest) getRequestCycle().getRequest().getContainerRequest();
        AccessToken accessToken = ((KeycloakPrincipal) request.getUserPrincipal()).getKeycloakSecurityContext().getToken();

        User user = userService.login(accessToken.getName());
        BudgeteerSession.get().login(user);
        setResponsePage(new SelectProjectPage());
    }
}
