package ltd.jezhu.promets.system.out;

import java.util.Collection;

/**
 * 分页出参
 * @author ymzhu
 * @date 2019/3/22 11:30
 */
public class PageInfo<T> {

    /**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 分页数据
     */
    private Collection<? extends T> data;

    public PageInfo() {
    }

    public PageInfo(Collection<? extends T> c, int pageNum, int pageSize, long total, int pages) {
        this.data = c;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.pages = pages;
    }

    public static <T> PageInfo<T> page(Collection<? extends T> data, int pageNum, int pageSize, long total, int pages) {
        return new PageInfo<>(data, pageNum, pageSize, total, pages);
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Collection<? extends T> getData() {
        return data;
    }

    public void setData(Collection<? extends T> data) {
        this.data = data;
    }
}

