package pages.groupsPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;

public class GroupCard {

    private static final By PARTICIP_AMOUNT = byClassName("friends-in-group_label");
    private static final String TITLE_ATRRIBUTE = "title";

    private String title, participAmount;

    private final SelenideElement rootElem;

    protected GroupCard(SelenideElement rootElem) {
        this.rootElem = rootElem;
        title = rootElem
                .getAttribute(TITLE_ATRRIBUTE); // get unique ID & print it             check SHOULD
        participAmount = rootElem
                .$(PARTICIP_AMOUNT)
                .shouldBe(visible.because("Нет элемента для отрисовки кол-ва участников группы"))
                .getText()
                .split(" ")[0];
    }

    public String getGroupCardTitle() {
        return title;
    }

    public String getGroupCardParticipAmount() {
        return participAmount;
    }
}
