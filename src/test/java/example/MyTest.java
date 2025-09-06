import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

public class MyTest {
    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void setupAll() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }

    @BeforeEach
    void setup() {
        context = browser.newContext();
        page = context.newPage();
    }

    @Test
    void shouldOpenGoogle() {
        page.navigate("https://www.google.com");
        Assertions.assertTrue(page.title().contains("Google"));
    }

    @AfterEach
    void cleanup() {
        context.close();
    }

    @AfterAll
    static void teardown() {
        browser.close();
        playwright.close();
    }
}
