import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


   public class ProductPage{

        public static void main(String[] args) {


            try (Playwright playwright = Playwright.create()) {
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
                Page page = browser.newPage();

                page.navigate("https://www.saucedemo.com/");
                assertThat(page).hasURL("https://www.saucedemo.com/");

                page.locator("#user-name").fill("standard_user");

                page.locator("#password").fill("secret_sauce");
                page.locator("#login-button").click();

                assertThat(page.locator(".title")).hasText("Products");
                assertThat(page.locator("[data-test=\"item-1-title-link\"]")).hasText("Sauce Labs Bolt T-Shirt");




            }
        }
    }

