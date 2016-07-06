package com.ync365.px.model;

public class Paginator {

    int page = 1;// 当前页数
    int length = 20;// 每页数据大小
    long count = 0;// 总数据大小
    long pageCount;// 总页数
    long start;// 数据查询开始index

    public Paginator() {

    }

    public Paginator(int page, int length) {
        super();
        this.page = page;
        this.length = length;
        buildStart();
    }

    public int getPage() {
        buildPage();
        return page;
    }
    
    public int getPageReOne() {
        buildPage();
        return page-1;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
        buildStart();
        buildPageCount();
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    /**
     * 计算总页数
     */
    private void buildPageCount() {

        if (count % length == 0) {
            this.pageCount = count / length;
        } else {
            this.pageCount = count / length + 1;
        }
    }

    private void buildStart() {
        this.start = (page - 1) * length;
    }
    private void buildPage(){
        this.page = (int) (start/length) + 1;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Pageintor[page=" + page + ",length=" + length
                + ",pageCount=" + pageCount + ",count=" + count + ",start="
                + start + "]";
    }

}
