import com.microsoft.playwright.Browser;
import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ErrorUser {
    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setSlowMo(1000)
            );

            Page page = browser.newPage();
            page.navigate("https://www.saucedemo.com/");

            page.locator("#user-name").fill("standard_user");
            page.locator("#password").fill("wrong_password");

            page.locator("#login-button").click();

            // Verify error message
            assertThat(page.locator("[data-test='error']"))
                    .containsText("Username and password do not match");

            System.out.println("✅ Invalid Login Test Passed");;
        }
    }
}