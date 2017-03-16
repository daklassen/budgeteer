package org.wickedsource.budgeteer.web.pages.user.selectproject;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;
import org.wickedsource.budgeteer.web.AbstractWebTestTemplate;

public class SelectProjectPageTest extends AbstractWebTestTemplate {

    @Test
    public void test() {
        WicketTester tester = getTester();
        SelectProjectPage page = new SelectProjectPage();
        tester.startPage(page);
        tester.assertRenderedPage(SelectProjectPage.class);
    }
}
