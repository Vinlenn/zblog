package com.vinlen.blog.common;

import java.util.List;

public class ListResult<T>  {
    private int count;
    private List<T> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public ListResult(int count, List<T> list) {
        this.count = count;
        this.list = list;
    }
}
