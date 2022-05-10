package properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class OwnerTests {
    @Test
    @Tag("owner")
    void loginTest() {
        String login = "";
        String password = "";

        System.out.println("login"+ login);
        System.out.println("password"+ password);

        String message = "I login with " + login + " and with password " + password;
        System.out.println(message);
    }
}
