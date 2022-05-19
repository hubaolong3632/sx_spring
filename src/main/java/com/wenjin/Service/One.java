package com.wenjin.Service;

import com.javaSpring.note.GitBean;
import com.javaSpring.note.Scope;

@GitBean("one") //标识需要被扫描
@Scope("one") // 标识原型类(可以被new多次相同)
public class One {
    String srt;

    public String getSrt() {
        return srt;
    }

    public void setSrt(String srt) {
        this.srt = srt;
    }
}
