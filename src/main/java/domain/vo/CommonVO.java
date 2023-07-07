package domain.vo;

import java.util.List;

public class CommonVO<V> {
    private Integer pageNumber; //当前页
    private Integer pageSize; //记录数
    private Integer totalCount; //总记录数
    private List<V> objList;

    public CommonVO(Integer pageNumber, Integer pageSize, Integer totalCount, List<V> objList) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.objList = objList;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<V> getObjList() {
        return objList;
    }

    public void setObjList(List<V> objList) {
        this.objList = objList;
    }

}
