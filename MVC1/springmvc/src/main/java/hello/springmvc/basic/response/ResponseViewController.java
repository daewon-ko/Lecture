package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mv = new ModelAndView("/response/hello")
                .addObject("data", "hello!");

        return mv;
    }
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "안녕하세요 사람들");
        return "response/hello";
    }
    @RequestMapping("/response-view-v3")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "안녕하세요 response-view-v3입니당");
    }

}
