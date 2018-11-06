package com.kunminx.viabus.repertory.bean;

import java.util.List;

/**
 * @author KunMinX
 * @date 2018/11/6
 */
public class DoneListBean {

    private String date;
    private List<TodoBean> todoList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TodoBean> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<TodoBean> todoList) {
        this.todoList = todoList;
    }
}
