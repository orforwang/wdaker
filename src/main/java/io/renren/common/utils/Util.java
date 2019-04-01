package io.renren.common.utils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import net.coobird.thumbnailator.Thumbnails;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

@Component
public class Util {

    /**
     * 将文字转为汉语拼音
     *
     * @param chineselanguage 要转成拼音的中文
     */
    public static String toHanyuPinyin(String ChineseLanguage) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
                } else {// 如果字符不是中文,则不转换
                    hanyupinyin += cl_chars[i];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin;
    }

    /**
     * 获取文件名(除扩展名外)
     *
     * @param fileFullName 文件全名
     * @return
     */
    public static String getfileName(String fileFullName) {
        String fileName = "";
        String[] strs = fileFullName.split("\\.");
        if (strs != null && strs.length >= 2) {
            for (int i = 0; i < strs.length - 1; i++) {
                fileName = fileName + strs[i];
            }
        } else {
            fileName = fileFullName;
        }
        return fileName;
    }

    /**
     * 获取文件相对路径
     *
     * @return
     */
    public static String getFolderPath() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String folderName = sf.format(calendar.getTime());
//        String separator = File.separator;
        folderName = "/" + folderName + "/";
        return folderName;
    }

    /**
     * 获取文件的扩展名
     *
     * @param filename 文件名
     * @return
     */
    public static String getExtension(String filename) {
        String extension = "";
        String[] strs = filename.split("\\.");
        if (strs != null && strs.length >= 2) {
            extension = strs[strs.length - 1];
        }
        return extension;
    }

    /**
     * 获取随机文件名
     *
     * @param ext 文件扩展名
     * @return
     */
    public static String getFilePath(String ext) {
        String filename = System.currentTimeMillis() + formatNum("0000", (int) (Math.random() * 10000));
        filename = filename + "." + ext;
        return filename;
    }

    /**
     * 格式化数字(在前方加零满足位数)
     *
     * @param foramt("000000")需要几位就写几个零
     * @param num
     * @return
     */
    public static String formatNum(String foramt, Integer num) {
        DecimalFormat df = new DecimalFormat(foramt);
        return df.format(num);
    }

    /**
     * 创建缩略图
     *
     * @param srcFile
     * @param destFile
     * @param width
     * @param height
     */
    public static void createPreviewImage(String srcFile, String destFile, int width, int height) {
        try {
            Thumbnails.of(srcFile).size(100, 100).toFile(destFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 对象转化为字符串
     *
     * @param object
     * @return NULL对象:"" 非NULL对象:对应字符串
     */
    public static String toString(Object object) {
        String str = (object == null) ? "" : object.toString();
        return str.trim();
    }

    /**
     * 对象转化为Double
     *
     * @param object
     * @return
     */
    public static Double toDouble(Object object) {
        double val = 0;
        try {
            if (object == null)
                val = 0;
            val = Double.parseDouble(object.toString());
        } catch (Exception e) {
            val = 0;
        }
        return val;
    }

    /**
     * 对象转化为整数
     *
     * @param object
     * @return NULL对象:0 非NULL对象:对应整数
     */
    public static int toInt(Object object) {
        int val = 0;
        try {
            if (object == null)
                val = 0;
            val = Integer.valueOf(object.toString());
        } catch (Exception e) {
            val = 0;
        }
        return val;
    }

    /**
     * 对象转化为整数
     *
     * @param object
     * @return NULL对象:0 非NULL对象:对应整数
     */
    public static short toShort(Object object) {
        try {
            if (object == null) {
                return 0;
            }
            return Short.valueOf(object.toString());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 对象转化为长整数
     *
     * @param object
     * @return NULL对象:0 非NULL对象:对应整数
     */
    public static long toLong(Object object) {
        long val = 0;
        try {
            if (object == null)
                val = 0;
            val = (long) Double.parseDouble(object.toString());
        } catch (Exception e) {
            val = 0;
        }
        return val;
    }

    /**
     * 将对象转化为boolean
     *
     * @param object
     * @return
     */
    public static boolean toBoolean(Object object) {
        boolean val = false;
        try {
            if (object == null) {
                val = false;
            } else {
                val = Boolean.parseBoolean(object.toString());
            }
        } catch (Exception e) {
            val = false;
        }

        return false;
    }

    /*** 对listmap进行排序并记录排名
     * @param data 数据
     * @param sortField 排序字段
     * @prram isASC
     */
    public static void sortListMap(List<Map<String, Object>> data, String sortField, boolean isASC) {
        Integer sortasc = isASC ? 1 : -1;
        Collections.sort(data, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> arg0, Map<String, Object> arg1) {
                if (arg0.get(sortField) instanceof Comparable) {
                    return ((Comparable) arg0.get(sortField)).compareTo(arg1.get(sortField)) * sortasc;
                } else {
                    if (Util.toDouble(arg0.get(sortField)) > Util.toDouble(arg1.get(sortField))) {
                        return 1 * sortasc;
                    } else {
                        return -1 * sortasc;
                    }
                }
            }
        });
    }

    /***
     * 去除list重复数据
     * @param list
     * @return
     */
    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    /**
     * 把模糊查询的检索的关键字对特殊字符进行转换如“[”、“%”
     *
     * @param str 需要转换的字符串
     * @return 返回模糊查询的字符串
     */
    public static String ToLikeStr(String str) {
        if (str != null && str.length() > 0) {
            str = str.trim().replace("\\", "\\\\%").replace("%", "\\%").replace("_", "\\_");
        }
        return str;
    }

    /**
     * Stream 去重
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
    }

}