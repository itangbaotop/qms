package top.itangbao.qms.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.itangbao.qms.dto.ApprovalResult;
import top.itangbao.qms.dto.QmsReport;

@Component("clientAStrategy") // 注意这个Bean的名字
public class ClientAApprovalStrategy implements IQmsApprovalStrategy {


    private static final Logger log = LoggerFactory.getLogger(ClientAApprovalStrategy.class);

    public ApprovalResult execute(QmsReport report) {
        log.info("clientA execute");
        if (report.getReportScore() >= 80) {
            return new ApprovalResult("通过", "符合");
        } else {
            return new ApprovalResult("不通过", "不符合");
        }
    }

    public String getStrategyName() {
        return "clientA";
    }
}
