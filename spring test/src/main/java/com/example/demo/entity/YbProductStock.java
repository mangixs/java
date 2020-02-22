package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;


@TableName(value = "yb_product_stock")
public class YbProductStock extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品SKU
     */
    @TableField("sku")
    private String sku;

    /**
     * 实际数量
     */
    @TableField("stock")
    private Integer stock;

    /**
     * 在途数量
     */
    @TableField("on_way_stock")
    private Integer onWayStock;

    /**
     * 可用数量
     */
    @TableField("available_stock")
    private Integer availableStock;

    /**
     * 库位
     */
    @TableField("location")
    private String location;

    /**
     * 仓库编码
     */
    @TableField("warehouse_code")
    private String warehouseCode;

    /**
     * 欠货数量
     */
    @TableField("left_stock")
    private Integer leftStock;

    /**
     * 是否需要推送到mongodb 0为未推送，1为已推送
     */
    @TableField("is_in_mongo")
    private Integer isInMongo;

    /**
     * 仓库id
     */
    @TableField("warehouse_id")
    private String warehouseId;

    /**
     * 是否已经单独同步万邑通的库存,0为未获取，1为已获取
     */
    @TableField("is_get_wyt_stock")
    private Boolean isGetWytStock;

    /**
     * 是否同步：1否，2是
     */
    @TableField("is_sync")
    private Integer isSync;

    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField("update_at")
    private LocalDateTime updateAt;

    /**
     * 最早入库时间
     */
    @TableField("first_instock_date")
    private String firstInstockDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getOnWayStock() {
        return onWayStock;
    }

    public void setOnWayStock(Integer onWayStock) {
        this.onWayStock = onWayStock;
    }

    public Integer getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Integer getLeftStock() {
        return leftStock;
    }

    public void setLeftStock(Integer leftStock) {
        this.leftStock = leftStock;
    }

    public Integer getIsInMongo() {
        return isInMongo;
    }

    public void setIsInMongo(Integer inMongo) {
        isInMongo = inMongo;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Boolean getIsGetWytStock() {
        return isGetWytStock;
    }

    public void setIsGetWytStock(Boolean getWytStock) {
        isGetWytStock = getWytStock;
    }

    public Integer getIsSync() {
        return isSync;
    }

    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public String getFirstInstockDate() {
        return firstInstockDate;
    }

    public void setFirstInstockDate(String firstInstockDate) {
        this.firstInstockDate = firstInstockDate;
    }
}
