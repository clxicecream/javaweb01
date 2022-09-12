package com.example.project01.Object;

import java.util.List;

public class pageBean<T> {
    public int total_count;//总记录数
    public int total_page;//总页码
    public List<T> list;//当前页的所有对象
    public int current_page;//当前页码
    public int rows;//记录数，每页最多对象数

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public pageBean(int total_count, int total_page, List<T> list, int current_page, int rows) {
        this.total_count = total_count;
        this.total_page = total_page;
        this.list = list;
        this.current_page = current_page;
        this.rows = rows;
    }
    public pageBean(){

    }



}
