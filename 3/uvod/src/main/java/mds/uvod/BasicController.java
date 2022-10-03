package mds.uvod;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@ResponseBody
@RestController
@RequestMapping("basic") // pristup: localhost:8080/basic

public class BasicController
{
    @GetMapping
    public String testBasic()
    {
        return "<b>Hello MDS!</b>";
    }

    // pristup: localhost:8080/basic/list
    @GetMapping("list")
    public List<String> testList()
    {
        return List.of("Hello","Hello2");
    }

    // pristup: localhost:8080/basic/test1
    @GetMapping("test1")
    // vstupni parametr name
    // pri zadavani adresy v prohlizeci musim zadat i parametr = "localhost:8080/basic/test1?name=jmeno"
    public String testParam1(@RequestParam String jmeno)
    {
        return String.format("Hello %s Welcome to our page!", jmeno);
    }

    @GetMapping("test2")
    // vstupni parametr jmeno s vychozi hodnotou "user"
    // uz to nevyhodi error kdyz nezadam vstupni parametr
    public String testParam2(@RequestParam(defaultValue = "user") String jmeno)
    {
        return String.format("Hello %s Welcome to our page!", jmeno);
    }

    @GetMapping("test2.5")
    // aby se parametr na strance ukazoval jinak nez v kodu
    // name = parametr anotace RequestParam, prejmenujeme ji jako jmeno
    // localhost:8080/basic/test2.5?jmeno=PETR
    public String testParam2_5(@RequestParam(defaultValue = "user", name = "jmeno") String name2)
    {
        return String.format("Hello %s Welcome to our page!", name2);
    }

    @GetMapping("test3")
    // jako vstupni parametr muzu dat list
    // localhost:8080/basic/test3?id=1,2,TRI,4
    public String testParam3(@RequestParam List<String> id)
    {
        return "All IDs are: " + id;
    }

    @GetMapping("form")
    public String helloForm()
    {
        // manualne napisu html stranku kterou pak vratim
       String html =
                    "<html>" +
                            "<body>" +
                                "<form method='post' action='hello'>" + //formular vyuziva metody post a vola jinou stranku pomoci action
                                    "<input type='text' name='name'/>" +
                                    "<input type='submit' value='Pozdrav!'%>" +
                                "</form>" +
                            "</body>" +
                    "</html>"
               ;

       return html;
    }

    // action hello kterou zavolala metoda post
    @RequestMapping(value = "hello", method= {RequestMethod.POST, RequestMethod.GET})
    public String helloTest(@RequestParam String name)
    {
        return String.format("<h2><b> Hello %s Welcome to our page! </b></h2>", name);
    }

}
