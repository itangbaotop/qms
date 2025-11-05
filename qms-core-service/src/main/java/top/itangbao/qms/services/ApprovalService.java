package top.itangbao.qms.services;

import top.itangbao.qms.dto.ApprovalResult;
import top.itangbao.qms.dto.QmsReport;

public interface ApprovalService {

    ApprovalResult submitApproval(QmsReport report);
}
