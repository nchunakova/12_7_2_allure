package qa.nchunakova;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnnotatedStepTest {

    private static final String REPOSITORY = "nchunakova/12_7_2_allure";
    private static final String CREATE_ISSUE = "New issue";

    @Owner("nchunakova")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Проверка видимости кнопки New issue в репозитории (feature)")
    @Story("Работа с issues в репозитории (user story)")
    @Test
    @DisplayName("Selenide Annotated Steps Test")
    public void testGithubIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeButtonWithName(CREATE_ISSUE);
    }
}
