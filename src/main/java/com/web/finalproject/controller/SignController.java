package com.web.finalproject.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.web.finalproject.service.SignService;
import com.web.finalproject.vo.SignVO;

@Controller
@RequestMapping("/sign")
public class SignController {
    @Autowired
    SignService service;
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/check")
    public String SignCheckPage() {
        return "sign/check";
    }

    @GetMapping("/signForm")
    public String SignInfoPage() {
        return "sign/signForm";
    }

    @GetMapping("/sign3")
    public String Sign3() {
        return "sign/sign3";
    }

    @GetMapping("/idCheck")
    public ModelAndView idCheck(String userid) {

        int result = service.idCheck(userid);

        ModelAndView mav = new ModelAndView();
        mav.addObject("result", result);
        mav.addObject("userid", userid);
        mav.setViewName("sign/idCheck");

        return mav;
    }

    @PostMapping("/userFormOk")
    public ModelAndView userFormOk(SignVO vo) {
        ModelAndView mav = new ModelAndView();

        try {
            int result = service.usersInsert(vo);

            if (result > 0) {
                mav.setViewName("redirect:sign3");
            } else {
                mav.setViewName("sign/signFormResult");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mav.setViewName("sign/signFormResult");
        }
        return mav;
    }

    @GetMapping("/login")
    public String login() {
        return "sign/login";
    }

    @PostMapping("/loginOk")
    public ModelAndView loginOk(String userid, String userpwd, HttpSession session) {
        ModelAndView mav = new ModelAndView();

        SignVO logVO = service.loginSelect(userid, userpwd);


        if (logVO != null) {
            session.setAttribute("logId", logVO.getUserid());
            session.setAttribute("logName", logVO.getUsername());
            session.setAttribute("logStatus", "Y");

            mav.setViewName("redirect:/");
        } else {
            mav.setViewName("redirect:login");
        }
        return mav;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/");
        return mav;
    }

    @GetMapping("/findId")
    public String FindId() {
        return "sign/findId";
    }

    @GetMapping("/findId2")
    public String FindId2(@RequestParam("username") String username, @RequestParam("email") String email, Model model, SignVO vo) {
        vo.setUsername(username);
        vo.setEmail(email);
        System.out.println(email);
        SignVO svo = service.findId(vo);
        model.addAttribute("svo", svo);
        return "sign/findId2";
    }

    @GetMapping("/findPwd")
    public String FindPwd() {
        return "sign/findPwd";
    }

    @RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
    @ResponseBody
    public String mailCheckGET(String email) throws Exception {
        Random random = new Random();

        int checkNum = random.nextInt(888888) + 111111;

        String setFrom = "asdg2795@naver.com";
        String toMail = email;
        String title = "안녕하세요";
        String content = "TEOTS��й�ȣ ã�� ������ȣ�Դϴ�. " + "<br><br>" + "������ȣ��" + checkNum + "�Դϴ�." + "<br>"
                + "�ش� ������ȣ�� ������ȣ Ȯ�ζ��� �����Ͽ� �ּ���.";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content, true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String num = Integer.toString(checkNum);
        return num;
    }

    @GetMapping("/findPwd2")
    public String FindPwd2() {
        return "sign/findPwd2";
    }

    @GetMapping("/findPwd3")
    public String FindPwd3() {
        return "sign/findPwd3";
    }
}
