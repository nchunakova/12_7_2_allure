package qa.nchunakova;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

@Owner("nchunakova")
@Severity(SeverityLevel.BLOCKER)
@Feature("Проверка видимости кнопки New issue в репозитории (feature)")
@Story("Работа с issues в репозитории (user story)")
public class SelenideTestWithListener {

    @Test
    @DisplayName("Basic Selenide Test With Listener")
    public void testGithubIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys("nchunakova/12_7_2_allure");
        $(".header-search-input").submit();

        $(linkText("nchunakova/12_7_2_allure")).click();
        $(partialLinkText("Issues")).click();
        $(withText("New issue")).shouldHave(Condition.visible);
    }
}
