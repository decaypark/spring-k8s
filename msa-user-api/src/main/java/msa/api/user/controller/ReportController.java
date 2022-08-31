package msa.api.user.controller;

import lombok.extern.slf4j.Slf4j;
import msa.api.user.vo.Member;
import msa.api.user.vo.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
public class ReportController {
    @Autowired
    ReportRepository reportRepository;

    @ResponseBody
    @GetMapping("/api2/reports")
    public List<Report> getReports(HttpServletRequest request) {
        return reportRepository.findAll();
    }

}
