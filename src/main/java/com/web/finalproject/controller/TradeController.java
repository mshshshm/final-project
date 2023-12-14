package com.web.finalproject.controller;

import com.sun.tools.jconsole.JConsoleContext;
import com.web.finalproject.dto.TradeDetailResponseDto;
import com.web.finalproject.dto.TradeForm;
import com.web.finalproject.dto.TradeResponseDto;
import com.web.finalproject.dto.TradeUpdateDto;
import com.web.finalproject.service.ImageService;
import com.web.finalproject.service.SignService;
import com.web.finalproject.service.TradeService;
import com.web.finalproject.utils.FileUtils;
import com.web.finalproject.vo.ImageVO;
import com.web.finalproject.vo.TradeVO;
import jakarta.servlet.http.HttpSession;
import org.hibernate.validator.constraints.ModCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Controller
public class TradeController {

    @Autowired
    TradeService tradeService;
    @Autowired
    ImageService imageService;

    @Autowired
    FileUtils fileUtils;


    @GetMapping("/trade/add")
    public String addForm(HttpSession session) {
        if (session.getAttribute("logStatus") != "Y") {
            return "sign/login";
        }

        return "trade/tradeForm";
    }


    @PostMapping("/trade/add")
    public String saveTrade(TradeForm tradeForm, HttpSession session, RedirectAttributes redirectAttributes) {

        if (session.getAttribute("logStatus") != "Y") {
            return "sign/login";
        }

        String logId = (String) session.getAttribute("logId");
        String logName = (String) session.getAttribute("logName");

        tradeForm.setUserId(logId);
        tradeForm.setUsername(logName);

        int no = tradeService.save(tradeForm);
        List<ImageVO> images = fileUtils.uploadFiles(tradeForm.getFiles());

        if (images.isEmpty()) {
            ImageVO image = ImageVO.builder()
                    .uploadfilename("no-image.jpg")
                    .storefilename("no-image.jpg")
                    .tradeid(no)
                    .build();
            images.add(image);
        }


        imageService.saveAll(no, images);
        return "redirect:/trade";
    }

    @GetMapping("/trade")
    public ModelAndView trades(String tradeTitle) {
        ModelAndView mav = new ModelAndView();
        List<TradeResponseDto> trades = tradeService.findAll(tradeTitle);
        mav.addObject("trades",trades);
        mav.setViewName("trade/trade");
        return mav;
    }

    @GetMapping("/trade/{no}")
    public String tradeDetail(@PathVariable int no, Model model, HttpSession session) {

        TradeDetailResponseDto trade = tradeService.findById(no);

        String userid = trade.getUserid();
        String content = trade.getContent();
        String logId = (String) session.getAttribute("logId");
        model.addAttribute("trade", trade);

        System.out.println("trade = " + trade.toString());

        System.out.println("trade.getStatus() = " + trade.getStatus());

        if (session.getAttribute("logStatus") != "Y" || !userid.equals(logId)) {
            return "trade/tradeDetail";
        }

        return "trade/myTradeDetail";
    }

    @ResponseBody
    @DeleteMapping("/trade/{no}")
    public String deleteTrade(@PathVariable int no, HttpSession session) {
        if (session.getAttribute("logStatus") != "Y") {
            return "sign/login";
        }
        String logId = (String) session.getAttribute("logId");
        TradeDetailResponseDto trade = tradeService.findById(no);
        String userid = trade.getUserid();
        if (!logId.equals(userid)) {
            return "/trade";
        }
        tradeService.delete(no);
        return "redirect:/trade";
    }

    @GetMapping("/trade/{no}/edit")
    public String editTradeForm(@PathVariable int no, Model model, HttpSession session) {
        if (session.getAttribute("logStatus") != "Y") {
            return "sign/login";
        }

        String logId = (String) session.getAttribute("logId");
        TradeDetailResponseDto trade = tradeService.findById(no);
        String userid = trade.getUserid();
        if (!logId.equals(userid)) {
            return "/trade";
        }
        model.addAttribute("trade", trade);
        return "/trade/tradeEdit";
    }

    @PostMapping("/trade/{no}/edit")
    public String editTrade(@PathVariable int no, HttpSession session, TradeUpdateDto tradeUpdateDto) {
        if (session.getAttribute("logStatus") != "Y") {
            return "sign/login";
        }

        String logId = (String) session.getAttribute("logId");
        TradeDetailResponseDto trade = tradeService.findById(no);
        String userid = trade.getUserid();

        if (!logId.equals(userid)) {
            return "/trade";
        }

        List<ImageVO> images = fileUtils.uploadFiles(tradeUpdateDto.getFiles());

        if (!images.isEmpty()) {
            imageService.delete(no);
            imageService.saveAll(no, images);
        }
        tradeService.update(no, tradeUpdateDto);

        return "redirect:/trade";
    }

    @ResponseBody
    @PostMapping("/trade/{no}/updateStatus")
    public String updateStatus(@PathVariable int no) {
        tradeService.updateStatus(no ,0);
        return "redirect:/trade";
    }

}
