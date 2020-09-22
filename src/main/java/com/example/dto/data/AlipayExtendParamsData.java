package com.example.dto.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlipayExtendParamsData implements Serializable {
    /**
     * sys_service_provider_id	String	可选	64	系统商编号
     * 该参数作为系统商返佣数据提取的依据，请填写系统商签约协议的PID	2088511833207846
     */
    private String sys_service_provider_id;
    /**
     * card_type	String	可选	32	卡类型	S0JP0000
     */
    private String card_type;
}
