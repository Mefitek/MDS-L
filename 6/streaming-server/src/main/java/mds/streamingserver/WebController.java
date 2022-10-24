package mds.streamingserver;

import mds.streamingserver.components.MyResourceHttpRequestHandler;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class WebController
{
    MyResourceHttpRequestHandler handler;

    public WebController(MyResourceHttpRequestHandler handler)
    {
        this.handler = handler;
    }

    // File toho videa
    private final static File MP4_FILE = new File("C:\\Users\\mefit\\Desktop\\Bobek\\MDS\\C\\MDS-L\\6\\BigBuckBunny.mp4");

    // mapovaci metoda video se odkazuje na html a to se odkazuje zpatky na wholeFile()

    // http://localhost:8080/video
    @GetMapping("video")
    public String video()
    {
        return "videoMP4";
    }

    // http://localhost:8080/file
    @GetMapping(path = "/file", produces = "video/mp4")
    @ResponseBody //jelikoz to vraci i video nestaci jen GetMapping
    public FileSystemResource wholeFile()
    {
        return new FileSystemResource(MP4_FILE);
    }

    @GetMapping("byterange")
    public void byterange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, MP4_FILE);
        handler.handleRequest(request, response);
    }

    // /student?name=Test&id=123456
    // localhost:8080/player?sirka=1000&vyska=800&url="http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"&muted=true&autoplay=true
    // @RequestParam(defaultValue = "Matej")
    @RequestMapping(path ="player", method = {RequestMethod.GET, RequestMethod.POST})
    public String player(Model model,
                         @RequestParam String url,
                         @RequestParam(defaultValue = "1000px") String sirka,
                         @RequestParam(defaultValue = "true") boolean muted,
                         @RequestParam(defaultValue = "true") boolean autoplay)
    {
        if(StringUtils.isEmpty(url))
        {
            model.addAttribute("error", "true");
        }

        model.addAttribute("sirka", sirka);
        model.addAttribute("url", url);
        model.addAttribute("muted", muted);
        model.addAttribute("autoplay", autoplay);

        return "player";
    }

    @GetMapping("index")
    public String index()
    {
        return "index";
    }

}
