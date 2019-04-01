package io.renren.common.constant;

/**
 * Created by 王云龙 on 2018/8/3.
 * Modular
 */
public interface Constant {
    /***
     * 时间格式化类型
     */
    interface DATEFORMAT {
        String DATE_PATTERN = "yyyy-MM-dd";
        String DATE_PATTERN_NON = "yyyyMMdd";
        String DATE_PATTERN_TIME = "yyyy-MM-dd HH:mm:ss";
        String DATE_PATTERN_MONTH = "yyyyMM";
    }

    /***
     * 返回标识
     */
    interface RETURN_CODE {
        int SC_OK = 0;
        int SC_TOKENOUT = 400;  //token令牌失效
        int SC_INTERNAL_SERVER_ERROR = 500;  //系统异常
        int SC_UNAUTHORIZED = 300;     //无权限操作
        int SC_PARAMETER_ERROR = 302;     //参数异常
        int REPEAT_REQUEST = 304;       //重复请求
    }

    /***
     * 分页参数
     */
    interface PageValue {
        int pageSize1 = 10;
        int bannerPageSize = 5;
    }

    /**
     * 删除标识
     */
    interface ISDEL {
        int normal = 0;
        int del = 1;
    }
    
    /**
     * 用户状态
     */
    interface USER_STATUS {
    	int stop = 0;
        int normal = 1;
    }

    /***
     * 工单|任务描述反馈信息类型
     */
    interface DES_TYPE {
        int DESC = 1;   //描述
        int FEEDBACK = 2;  //反馈
    }

    /***
     * 工单操作类型
     */
    interface WORK_RECORD_TYPE {
        String CREATE = "1";
        String SURE = "2";
        String BRANCH = "3";
        String TURN = "4";
        String OVER = "5";
        String TURNOPER = "6";
    }

    /***
     * 工单执行状态
     */
    interface WORK_EXECUTE_STATUS {
        String STAY = "1";
        String SURE = "2";
        String TURN = "3";
        String OVER = "4";
        String FINISH = "5";
    }

    /***
     * 常用字典组编码
     */
    interface DICTIONARY_CODE {
        String IMPORTANT_LEVEL = "IMPORTANT_LEVEL";     //工单&任务 重要程度等级

        String APPROVAL_TYPE_CODE = "APPROVAL_TYPE";     //审批类型字典编码
    }

    /***
     * 附件对应模块类型
     */
    interface ATTACHEMENT_MODEL_TYPE{
        String WORK = "1";
        String TASK = "2";
        String APPROVAL = "3";
    }
    
    /***
     * 附件对应文件类型
     */
    interface ATTACHEMENT_TYPE{
    	String FOLDER = "0";
        String PICTURE = "1";
        String DOCUMENT ="2";
    }
    
    /***
     * 消息类型
     */
    interface MESSAGE_TYPE{
    	String WORK = "1";
        String TASK = "2";
        String APPROVE = "3";
        String BOARD = "4";
    }
    
    /**
     * 公告类型
     */
    interface BOARD_TYPE{
    	String BOARD = "1";
    	String SYSMESSAGE = "2";
    	String BANNER = "3";
    }
    
    /***
     * 消息状态
     */
    interface MESSAGE_STATUS{
    	int UNREAD = 0;//未读
        int READ = 1;//已读
    }
    
    /***
     * 文件上传大小
     */
    interface ATTACHEMEN_UPLOAD{
        int SIZE = 10485760;//10MB
    }

    /***
     * 任务操作类型
     */
    interface TASK_RECORD_TYPE{
        String CREATE = "1";
        String SURE = "2";
        String OVER = "3";
        String TURNOPER = "4";
    }

    /***
     * 任务执行状态
     */
    interface TASK_EXECUTE_STATUS{
        String STAY = "1";
        String SURE = "2";
        String OVER = "3";
        String FINISH = "4";
    }

    
    /**
     * 审批状态
     */
    interface ApprovalStatus {
        short WAIT_APPROVAL = 1;
        short APPROVED = 2;
        short UNAPPROVED = 3;
        short DEL_APPROVED = 4;
    }

    /**
     * 审批操作类型
     */
    interface ApprovalRecordAction {
        short CREATE = 0;
        short AGREE_DISPATCHER = 1;
        short AGREE_ONLY = 2;
        short DISAGREE = 3;
        short DELETE = 4;
    }

    /***
     * 工作台类型
     */
    interface CONSOLETYPE{
        short TASK = 1;
        short WORK = 2;
        short APPROVE = 3;
        short SELF = 4;
    }

    /***
     * 分段查询参数
     */
    interface SUBSECTION{
        int SIZE = 200;
    }

    /***
     * 生产线角色id
     */
    interface PRODUCTLINE{
        short ONE = 1;
        short TWO = 2;
    }
    
    /***
     * 生产线角色id
     */
    interface ROLEPERMISSION{
        String ONELINEHEAD = "sys:line:oneleader";
        String ONELINEEMP = "sys:line:oneemp";
        String TWOLINEHEAD = "sys:line:twoleader";
        String TWOLINEEMP = "sys:line:twoemp";
    }

    /***
     * 设置时间值(秒)
     */
    interface TIMES{
        long REPEATR_EQUEST_OUT_TIME = 3;      //接口请求限制时间
    }

    interface Perms{
        String TEAM_ONE_LEADER = "sys:line:twoleader";
        String TEAM_TWO_LEADER= "sys:line:oneleader";
    }

    /***
     * 是否转单
     */
    interface ISTRUN{
        int nomal = 0;
        int yes = 1;
    }

}
