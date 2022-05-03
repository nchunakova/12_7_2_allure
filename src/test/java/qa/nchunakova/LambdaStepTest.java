package qa.nchunakova;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class LambdaStepTest {

    private static final String REPOSITORY = "nchunakova/12_7_2_allure";
    private static final String CREATE_ISSUE = "New issue";

    @Owner("nchunakova")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Проверка видимости кнопки New issue в репозитории (feature)")
    @Story("Работа с issues в репозитории (user story)")
    @Test
    @DisplayName("Selenide Lambda Steps Test")
    public void testGithubCreateIssue() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем реопзиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Кликаем на таб Issues", () -> {
            $(partialLinkText("Issues")).click();
        });
        step("Проверяем, что существует кнопка с названием " + CREATE_ISSUE, () -> {
            $(withText("New issue")).shouldHave(Condition.visible);
            Allure.getLifecycle().addAttachment(
                    "Исходники страницы",
                    "text/html",
                    "html",
                    WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
            );
        });
    }
}
