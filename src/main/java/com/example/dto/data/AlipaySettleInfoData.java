package com.example.dto.data;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AlipaySettleInfoData implements Serializable {
    /**
     * settle_detail_infos	SettleDetailInfo[]	必填	10	结算详细信息，json数组，目前只支持一条。
     */
    private List<AlipaySettleDetailInfoData> settle_detail_infos;
    /**
     * settle_period_time	String	可选	10	该笔订单的超期自动确认结算时间，到达期限后，将自动确认结算。此字段只在签约账期结算模式时有效。取值范围：1d～365d。d-天。 该参数数值不接受小数点。	7d
     */
    private String settle_period_time;
}
