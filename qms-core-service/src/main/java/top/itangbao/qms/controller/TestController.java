package top.itangbao.qms.controller;


import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.itangbao.qms.dto.ApprovalResult;
import top.itangbao.qms.dto.QmsReport;
import top.itangbao.qms.services.ApprovalService;

@RestController
@RequestMapping("/test")
public class TestController {

    // java "-Dloader.path=./lib/" -jar qms-core-service-1.0-SNAPSHOT.jar

    @Resource
    private ApprovalService approvalService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/hello")
    public ApprovalResult hello() {
        // 1. 从 Controller 中获取参数，封装成 DTO
        QmsReport report = new QmsReport();
        report.setReportName("test");
        report.setReportType("test");
        report.setReportContent("test");
        report.setReportStatus("test");
        report.setReportScore(71);

        return approvalService.submitApproval(report);
    }
}
