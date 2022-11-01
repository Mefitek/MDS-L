package mds.streamingserver;

import mds.streamingserver.components.MyResourceHttpRequestHandler;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
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
    private final static File MP4_FILE = new File("C:\\Users\\mefit\\Pictures\\MDS\\Videosoubory\\test8.mkv");

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

    // -- Ukol - stream


    // -- Adaptivni stream

    private final static String HLS_PATH = "C:\\Users\\mefit\\Pictures\\MDS\\HLS\\";
    private final static String DASH_PATH = "C:\\Users\\mefit\\Pictures\\MDS\\MPEG-DASH\\";

    //
    @RequestMapping(value = {"/dash/{file}", "/hls/{file}", "/hls/{quality}/{file}"}, method = RequestMethod.GET)
    public void adaptive_streaming(@PathVariable("file") String file,
                                   @PathVariable(value="quality", required=false) String quality,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws ServletException, IOException
    {
        //funkce: jako byterange, ale nebude se predavat jen 1 soubor, ale bude to fungovat globalnejs
        File STREAM_FILE = null;

        String handle = (String)request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        switch (handle)
        {
            case "/dash/{file}":
                STREAM_FILE = new File (DASH_PATH + file);
                break;

            case "/hls/{file}":
                STREAM_FILE = new File(HLS_PATH + file);
                break;

            case "/hls/{quality}/{file}":
                STREAM_FILE = new File(HLS_PATH + quality + "\\" +file);
                break;

            default:
        }

        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, STREAM_FILE);
        handler.handleRequest(request, response);

    }

    @RequestMapping(value = "dashPlayer", method = {RequestMethod.GET, RequestMethod.POST})
    public String dashPlayer(@RequestParam String url,
                             Model model)
    {
        model.addAttribute("url", url); //predavani url modelem
        return "dashPlayer";
    }


    @GetMapping("gallery")
    public String gallery()
    {
        return "gallery";
    }

    @GetMapping("index")
    public String index()
    {
        return "index";
    }

}
