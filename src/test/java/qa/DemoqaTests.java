package qa;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;

@Tag("demoqa") // тэг чтобы запустить определенную команду
public class DemoqaTests extends TestBase {
    @Test
    @DisplayName("Successful fill registration test")
    void fillFormTest() {
        String name = "Vera";
        String lastName = "Podguk";
        String email = "testForm@mailinator.com";

        String expectedFullName = format("%s %s", name, lastName);
        step("Open registration form", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            // Removing AD because it is overlapping Submit button
            Selenide.executeJavaScript("document.getElementById('fixedban').remove()");
            // Removing Footer because it is overlapping Submit button
            Selenide.executeJavaScript("document.getElementsByTagName('footer')[0].remove()");
        });
        //executeJavaScript("$('footer').remove()"); спрятать футер
        //executeJavaScript("$('#fixedban').remove()"); спрятатть рекламу
        step("Fill registration form", () -> {
            $("[id=firstName]").setValue(name);
            $("[id=lastName]").setValue(lastName);
            $("[id=userEmail]").setValue(email);
            $("[for=gender-radio-2]").click();
            //$("#genterWrapper").$(byText("Female")).click();
            //делать поиск по тексту
            $("[id=userNumber]").setValue("1234543456");
            $("[id=dateOfBirthInput]").click();
            $("[class=react-datepicker__month-select]").selectOption("August");
            $("[class=react-datepicker__year-select]").selectOption("1984");
            $("[aria-label='Choose Friday, August 31st, 1984']").click();
            // $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
            // нажать именно на конкретное 30 - второй варик
            $("#subjectsInput").setValue("Math").pressEnter();
            $("[for=hobbies-checkbox-1]").click();
            //$("#hobbies-checkbox-1").parent().click();
            //$("#uploadPicture").uploadFromClasspath("text.txt");
            //$("#uploadPicture").uploadFile(new File("src/test/resources/text.txt"));
            // второй варик
            $("#currentAddress").setValue("Minsk, 220117");
            $("#state").scrollTo().click();
            $("#state").$(byText("NCR")).click();
            $("#city").click();
            $("#city").$(byText("Delhi")).click();
            $("#submit").scrollTo().click();
        });
        step("Verify form data", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text(expectedFullName),
                    text(email),
                    text("Female"),
                    text("1234543456"),
                    text("31 August,1984"),
                    text("Math"),
                    text("Sports"),
                    //text("text.txt"),
                    text("Minsk, 220117"),
                    text("NCR Delhi"));
            $("#closeLargeModal").click();
            //проверка что форма закрыта
            $("#closeLargeModal").isEnabled();
        });
        //$(".table-responsive").shouldHave(text(name +" "+ lastname), text("testForm@mailinator.com"), text("Other"));
        //$(".table-responsive").$(byText("Student Name"))
        ////                .parent().shouldHave(text(name +" "+ lastname));
    }
}

