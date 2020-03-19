/**
 * Copyright © 2019 国网信通产业集团. All rights reserved.
 *
 * @Title:EsProductAttributeValue.java
 * @Prject: com.ytz.product.elasticsearch.document
 * @Package: com.ytz.product.elasticsearch.document
 * @author: yangtianzeng
 * @date: 2020/3/16 20:48
 * @version: V1.0
 */
package com.ytz.product.elasticsearch.document;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @ClassName: EsProductAttributeValue
 * @Description: 搜索中 的  商品属性信息
 * @author: yangtianzeng
 * @date: 2020/3/16 20:48
 */
@Data
public class EsProductAttributeValue implements Serializable {
    private static final long serialVersionUID = -3313433547137089826L;
    private Long id;
    private Long productAttributeId;
    //属性值
    @Field(type = FieldType.Keyword)
    private String value;
    //属性参数：0->规格；1->参数
    private Integer type;
    //属性名称
    @Field(type=FieldType.Keyword)
    private String name;
}
