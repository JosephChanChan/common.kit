package com.joseph.common.kit.sign.model;

import lombok.Data;

/**
 * @author Joseph
 * @since 2021-12-19 14:34
 */
@Data
public class SignModel {

    /**
     * 已按规则构造好的签名原始串
     */
    private String rawMaterial;

    /**
     * 待验签的签名
     */
    private String targetSign;


    public boolean necessaryParamValid() {
        return (null != rawMaterial && rawMaterial.length() > 0) &&
                (null != targetSign && targetSign.length() > 0);
    }

    public boolean rawMaterialCheck() {
        return null == rawMaterial || rawMaterial.length() == 0;
    }

}
