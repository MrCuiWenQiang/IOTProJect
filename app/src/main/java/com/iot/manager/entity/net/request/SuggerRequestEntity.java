package com.iot.manager.entity.net.request;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/23 0023.
 */
public class SuggerRequestEntity {
    private String content;

    public SuggerRequestEntity() {
    }

    public SuggerRequestEntity(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
