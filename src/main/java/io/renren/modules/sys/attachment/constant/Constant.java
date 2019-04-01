package io.renren.modules.sys.attachment.constant;

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
        String DATE_PATTERN_TIME = "yyyy-MM-dd HH:mm:ss";
    }

    /***
     * 分页参数
     */
    interface PageValue {
        int pageSize1 = 10;
        int bannerPageSize = 5;
    }

    /***
     * 文件上传大小
     */
    interface ATTACHEMEN_UPLOAD{
        int SIZE = 10485760;//10MB
    }

}
