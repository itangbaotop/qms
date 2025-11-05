package top.itangbao.qms.strategy;

import top.itangbao.qms.dto.ApprovalResult;
import top.itangbao.qms.dto.QmsReport;

public interface IQmsApprovalStrategy {

    /**
     * 执行审批
     * @param report 质检报告
     * @return 审批结果
     */
    ApprovalResult execute(QmsReport report);

    /**
     * 标识这个策略适用于哪个租户或项目
     */
    String getStrategyName();
}
