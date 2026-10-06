import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.microsoft.playwright.options.SelectOption;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class VerifySorting {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
                            .setSlowMo(1000)
            );

            Page page = browser.newPage();

            page.navigate("https://www.saucedemo.com/");
            assertThat(page).hasURL("https://www.saucedemo.com/");

            page.locator("#user-name").fill("standard_user");
            page.locator("#password").fill("secret_sauce");
            page.locator("#login-button").click();

            // Verify Products page
            assertThat(page.locator(".title")).hasText("Products");


            //STEP 19 — Product Sorting
            page.locator(".product_sort_container").selectOption("lohi");

            assertThat(page.locator(".product_sort_container"))
                    .hasValue("lohi");

            Locator productPrices =
                    page.locator(".inventory_item_price");
            List<String> actualPrices =
                    productPrices.allTextContents();

//            //side button function
//            page.locator("#react-burger-menu-btn").click();
//            page.locator("//a[@id='dynamic_catalog_sidebar_link']").click();

        }
    }
}