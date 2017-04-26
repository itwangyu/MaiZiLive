package com.maizi.maizilive.bean;

/**
 * 作者 by yugai 时间 16/12/23.
 * ＊ 邮箱 784787081@qq.com
 */

public class PDResponse extends BaseBean{
    public enum DataType{
        ITEMS,ROOM
    }
    public static final int SUCCESS_CODE = 0;
    public int errno;
    public String errmsg;
    public PDRoom data;



    @Override
    public String toString() {
        return "PDResponse{" +
                "errno=" + errno +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }
}
