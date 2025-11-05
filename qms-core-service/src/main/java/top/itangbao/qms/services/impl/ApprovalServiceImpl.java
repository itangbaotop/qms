package top.itangbao.qms.services.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.itangbao.qms.dto.ApprovalResult;
import top.itangbao.qms.dto.QmsReport;
import top.itangbao.qms.services.ApprovalService;
import top.itangbao.qms.strategy.IQmsApprovalStrategy;

import java.util.Map;


@Service
public class ApprovalServiceImpl implements ApprovalService {

    @Resource
    private Map<String, IQmsApprovalStrategy> strategyMap;

    @Override
    public ApprovalResult submitApproval(QmsReport report) {

        String strategyName = "clientAStrategy";

        // 2. 从 Map 中动态选择策略
        // 如果没配置，就用 "defaultStrategy"
        IQmsApprovalStrategy strategy = strategyMap.getOrDefault(strategyName, strategyMap.get("defaultStrategy"));

        // 3. 执行特定策略，核心代码完全不用变
        return strategy.execute(report);

    }
}
