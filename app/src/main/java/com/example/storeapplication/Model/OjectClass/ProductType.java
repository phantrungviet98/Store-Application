package com.example.storeapplication.Model.OjectClass;

import java.util.List;

public class ProductType {
    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public int getParentProductTypeId() {
        return parentProductTypeId;
    }

    public void setParentProductTypeId(int parentProductTypeId) {
        this.parentProductTypeId = parentProductTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public List<ProductType> getChilList() {
        return chilList;
    }

    public void setChilList(List<ProductType> chilList) {
        this.chilList = chilList;
    }

    int productTypeId, parentProductTypeId;
    String productTypeName;
    List<ProductType> chilList;

}
