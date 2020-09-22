package com.example.dto.data;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AlipayGoodsDetailData implements Serializable {
    /**
     * goods_id	String	必填	32	商品的编号	apple-01
     */
    private String goods_id;
    /**
     * goods_name	String	必填	256	商品名称	ipad
     */
    private String goods_name;
    /**
     * quantity	Number	必填	10	商品数量	1
     */
    private Integer quantity;
    /**
     * price	Price	必填	9	商品单价，单位为元	2000
     */
    private BigDecimal price;
    /**
     * goods_category	String	可选	24	商品类目	34543238
     */
    private String goods_category;
    /**
     * categories_tree	String	可选	128	商品类目树，从商品类目根节点到叶子节点的类目id组成，类目id值使用|分割	124868003|126232002|126252004
     */
    private String categories_tree;
    /**
     * body	String	可选	1000	商品描述信息	特价手机
     */
    private String body;
    /**
     * show_url	String	可选	400	商品的展示地址	http://www.alipay.com/xxx.jpg
     */
    private String show_url;
}
