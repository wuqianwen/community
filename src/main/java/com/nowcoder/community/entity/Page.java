package com.nowcoder.community.entity;

/**
 * 封装分页的相关信息
 */

public class Page {
    // 当前页码
    private int current = 1;
    // 显示上限
    private int limit = 10;
    // 数据总数
    private int rows;
    // 查询路径(用于复用分页链接)
    private String path;

    @Override
    public String toString() {
        return "Page{" +
                "current=" + current +
                ", limit=" + limit +
                ", rows=" + rows +
                ", path='" + path + '\'' +
                '}';
    }

    public void setCurrent(int current) {
        if(current >= 1){
            this.current = current;
        }
    }

    public void setLimit(int limit) {
        if(limit >=1 && limit <= 100){
            this.limit = limit;
        }
    }

    public void setRows(int rows) {
        if(rows >= 0){
            this.rows = rows;
        }
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCurrent() {
        return current;
    }

    public int getLimit() {
        return limit;
    }

    public int getRows() {
        return rows;
    }

    public String getPath() {
        return path;
    }

    public int getOffset(){
        return (current-1) * limit;
    }

    public int getTotal(){
        if(rows%limit==0){
            return rows/limit;
        }
        return rows/limit + 1;
    }

    /**
     * 获取起始页码
     * @return
     */
    public int getFrom(){
        int from = current - 2;
        return from < 1 ? 1:from;
    }

    /**
     * 获取结束页码
     * @return
     */
    public int getTo(){
        int to = current + 2;
        int total = getTotal();
        return to > total ? total:to;
    }

}
