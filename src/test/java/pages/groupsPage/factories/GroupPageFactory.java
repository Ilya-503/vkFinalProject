package pages.groupsPage.factories;import pages.groupPages.MyGroupPage;import pages.groupPages.OtherGroupPage;public class GroupPageFactory {    public static GroupPage getGroupPage() {        return GroupPage.isMyGroup() ? new MyGroupPage() : new OtherGroupPage();    }}