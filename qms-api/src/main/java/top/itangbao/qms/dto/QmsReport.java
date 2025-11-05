package top.itangbao.qms.dto;


import lombok.Data;

@Data
public class QmsReport {

    private String tenantId;

    private String projectId;

    private String reportId;

    private String reportName;

    private String reportContent;

    private String reportType;

    private String reportStatus;

    private String reportTime;

    private String reportUser;

    private String reportUserPhone;

    private String reportUserEmail;

    private Integer reportScore;
}
