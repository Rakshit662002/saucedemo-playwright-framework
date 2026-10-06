import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.microsoft.playwright.options.SelectOption;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductAddToCart {

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

            // Add to product
            page.locator("//button[@id='add-to-cart-sauce-labs-backpack']").click();
            page.locator("//button[@id='add-to-cart-sauce-labs-bike-light']").click();

            // Verify cart count
            assertThat(page.locator(".shopping_cart_link")).hasText("2");

            // Open cart
            page.locator(".shopping_cart_link").click();

            // Verify Cart URL
            assertThat(page).hasURL("https://www.saucedemo.com/cart.html");

            // Verify "Your Cart"
            assertThat(page.locator(".title")).hasText("Your Cart");

            // Verify product price
            //page.pause();

            assertThat(page.locator(
                    "//div[@data-test='inventory-item-price' and text()='29.99']"
            )).hasText("$29.99");


            page.locator("#remove-sauce-labs-backpack").click();
            page.locator("#remove-sauce-labs-bike-light").click();

            //click on continue shopping
            page.locator("#continue-shopping").click();
            assertThat(page.locator(".title"))
                    .hasText("Products");
            assertThat(page).hasURL("https://www.saucedemo.com/inventory.html");

            assertThat(page.locator("//div[text()='Sauce Labs Backpack']")).hasText("Sauce Labs Backpack");
            assertThat(page.locator("[data-test=\"inventory-item-sauce-labs-backpack-img\"]")).isVisible();

           //STEP 19 — Product Sorting
           page.locator(".product_sort_container").selectOption("lohi");

            assertThat(page.locator(".product_sort_container"))
                    .hasValue("lohi");

            //side button function
            page.locator("#react-burger-menu-btn").click();
            page.locator("//a[@id='dynamic_catalog_sidebar_link']").click();



           //page.locator(".product_sort_container").selectOption("");
            //page.locator(".inventory_item_name").count();


            //count the total product text
            /*Locator productNames = page.locator(".inventory_item_name");
            List<String> actualNames = productNames.allTextContents();
            System.out.println("Total Products: " + productNames.count());
            System.out.println("Actual Names: " + actualNames);

            //create a copy of actualNames.
            List<String> expectedNames =
                    new ArrayList<>(actualNames);

            // Sort expected list Z → A
            expectedNames.sort(Collections.reverseOrder());

            System.out.println("Expected Names: " + expectedNames);
            Assert.assertEquals(actualNames, expectedNames);

            */
            //Total prices count
            /*Locator productPrices = page.locator(".inventory_item_price");
            List<String> actualPrices = productPrices.allTextContents();

            System.out.println(("Prices "+actualPrices));*/













        }
    }
}

