package com.kunminx.viabus.repertory.bean;

/**
 * @author KunMinX
 * Create at 2018/11/6
 */
public class DataBean {

    private DoneListBean doneListBean;
    private TodoListBean todoListBean;
    private int type;

    public DoneListBean getDoneListBean() {
        return doneListBean;
    }

    public void setDoneListBean(DoneListBean doneListBean) {
        this.doneListBean = doneListBean;
    }

    public TodoListBean getTodoListBean() {
        return todoListBean;
    }

    public void setTodoListBean(TodoListBean todoListBean) {
        this.todoListBean = todoListBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
