package com.lushihao.sharewe.structure;

import java.util.HashSet;
import java.util.Set;

public class StringData {

    /**
     * 不含重复字符的最长子串长度
     *
     * @param str
     * @return
     */
    public int notRepeatMaxLengthChildStr(String str) {
        if (str.length() == 0) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }
        int length = 1;
        for (int i = 0; i < str.length() - 2; i++) {//
            if (str.substring(i).length() >= length + 1) {
                length = getNotRepeatMaxLengthChildStr(str.substring(i), length);
            }
        }
        return length;
    }

    /**
     * 不含重复字符的最长子串长度
     *
     * @param str
     * @return
     */
    private int getNotRepeatMaxLengthChildStr(String str, int length) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            set.add(str.charAt(i));
        }
        for (int i = length; i < str.length(); i++) {
            set.add(str.charAt(i));
            //当前数量是正确的，没有重复
            if (set.size() == i + 1) {
                length = set.size();
            } else {//此时有重复了
                break;
            }
        }
        return length;
    }

}
