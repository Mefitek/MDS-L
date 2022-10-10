package mds.spring;

import org.springframework.web.bind.annotation.*;

@ResponseBody
@RestController
@RequestMapping("basic") // pristup: localhost:8080/basic

public class BasicController
{
    @GetMapping
    public String testBasic()
    {
        return "<b>Hello MDS basic!</b>";
    }

    // Metoda se statickym HTML
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
