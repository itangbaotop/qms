package top.itangbao.qms.strategy;

import org.springframework.stereotype.Component;
import top.itangbao.qms.dto.ApprovalResult;
import top.itangbao.qms.dto.QmsReport;

@Component("defaultStrategy")
public class DefaultApprovalStrategy implements IQmsApprovalStrategy {


    public ApprovalResult execute(QmsReport report) {
        if (report.getReportScore() >= 70) {
            return new ApprovalResult("通过", "符合");
        } else {
            return new ApprovalResult("不通过", "不符合");
        }

    }

    public String getStrategyName() {
        return "default";
    }
}