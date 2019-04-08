package com.iot.manager.entity.net.request;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/23 0023.
 */
public class HandListRequestEntity {
    private String searchType;//查询类型，0标识私有设备，1标识他人设备
    private int pageNo;
    private int pageSize;

    public HandListRequestEntity() {
    }

    public HandListRequestEntity(String searchType, int pageNo, int pageSize) {
        this.searchType = searchType;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
