package pages.groupsPage.factories.groupPage;

import pages.groupPages.MyGroupPage;
import pages.groupPages.StandardGroupPage;

public class GroupPageFactory {
    public static GroupPage getGroupPage() {
        System.out.println(GroupPage.isMyGroup());
        try {
            Thread.sleep(600);
        } catch (Exception e) {

        }
        System.out.println(GroupPage.isMyGroup());
            return GroupPage.isMyGroup() ? new MyGroupPage() : new StandardGroupPage();
    }
}
