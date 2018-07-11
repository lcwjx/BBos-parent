package com.lee.utils;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class PageBean {
    @JSONField(serialize = false)
    private int currentPage;//当前页码
    @JSONField(serialize = false)
    private int pageSize;//每页显示的记录数
    @JSONField(serialize = false)
    private DetachedCriteria detachedCriteria;//查询条件
    private int total;//总记录数
    private List rows;//当前页需要展示的数据集合

    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }
    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }
}
