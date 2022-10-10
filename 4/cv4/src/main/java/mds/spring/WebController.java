/*
V třídě WebController vytvořte 3 metody, které budou splňovat následující:

•Metoda myself bude naslouchat na adrese myself . Bude použita statická šablona, kde
bude napsáno vaše jméno a id. Obojí bude v elementu <h1> a jméno bude červeně a
ID tučně v kombinací s jinou barvou dle vaší volby.

• Další metody budou alice a bob , každá bude naslouchat na jiné alice , bob

•Obě metody budou využívat jednu dynamickou šablonu (template.html) a 2 rozdílné
css soubory, se jmény odpovídající metodám (tzn. a lice.css a bob.css)

•Alice bude napsána jako <h1>, kurzívou a červeně

• Bob bude taktéž v <h1>, podtrženě a modrou barvou

• Css lze dynamicky měnit: th:href css /' + name } + css
 */

package mds.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; //kvuli dynamicky
import org.springframework.web.bind.annotation.GetMapping;

@Controller //ne jenom RestController, protoze chci aby mi to vyhledavalo i ty sablony
public class WebController
{
    @GetMapping("static_page") //pristup: localhost:8080/static_page
    public String staticPage()
    {
        return "staticpage"; //bude hledat HTML sablonu staticpage v mds.spring/resources/templates
    }

    @GetMapping("dynamic_page") //pristup: localhost:8080/dynamic_page
    public String dynamicPage(Model model) //budeme tam nacitat model Spring UI
    {
        model.addAttribute("name","House");
        return "dynamicpage"; //bude hledat HTML sablonu dynamicpage v mds.spring/resources/templates
    }

    @GetMapping("myself") //pristup: localhost:8080/myself
    public String myselfPage()
    {
        return "myself"; //bude hledat HTML sablonu myself v mds.spring/resources/templates
    }

    @GetMapping("alice") //pristup: localhost:8080/alice
    public String alicePage(Model model) //budeme tam nacitat model Spring UI
    {
        model.addAttribute("name","alice");
        return "template"; //bude hledat HTML sablonu alice v mds.spring/resources/templates
    }

    @GetMapping("bob") //pristup: localhost:8080/alice
    public String bobPage(Model model) //budeme tam nacitat model Spring UI
    {
        model.addAttribute("name","bob");
        return "template"; //bude hledat HTML sablonu bob     v mds.spring/resources/templates
    }
}
