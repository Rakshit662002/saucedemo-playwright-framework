import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest {

    public static void main(String[] args) {


        try (Playwright playwright = Playwright.create()) {
            ;
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            Page page = browser.newPage();

            page.navigate("https://www.saucedemo.com/");
            assertThat(page).hasURL("https://www.saucedemo.com/");

            page.locator("#user-name").fill("standard_user");
            //assertThat(page).("standard_user");

            page.locator("#password").fill("secret_sauce");
            page.locator("#login-button").click();
            System.out.println(page.title());

            System.out.println("✅ Valid Login Test Passed");


        }
    }
}