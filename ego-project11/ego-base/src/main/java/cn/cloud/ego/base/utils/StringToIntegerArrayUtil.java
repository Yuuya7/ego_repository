package cn.cloud.ego.base.utils;

public class StringToIntegerArrayUtil {

    public static Integer[] run(String string) {

        String[] arrs = string.split(",");

        Integer[] ints = new Integer[arrs.length];
        for (int i = 0; i < arrs.length; i++) {
            ints[i] = Integer.parseInt(arrs[i]);
        }
        return ints;
    }

}
