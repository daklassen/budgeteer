package org.wickedsource.budgeteer.web.pages.user.selectproject;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.wickedsource.budgeteer.web.AbstractWebTestTemplate;
import org.wickedsource.budgeteer.web.pages.user.login.LoginPage;

public class SelectProjectPageTest extends AbstractWebTestTemplate {

    @Test
    public void test() {
        WicketTester tester = getTester();
        SelectProjectPage page = new SelectProjectPage(LoginPage.class, new PageParameters());
        tester.startPage(page);
        tester.assertRenderedPage(SelectProjectPage.class);
    }
}
