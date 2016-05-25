package com.swtec.sw.persist.enums;
/**
 * 单位类型
 * @author 
 *chengkang
 */
public enum CompanyType {
	
	block("块"), tao("套"), platform("台"), second("次"), workingDay("工作日"), whole("全部");

    private final String info;

    private CompanyType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

}
