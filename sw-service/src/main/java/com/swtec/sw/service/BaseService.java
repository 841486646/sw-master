package com.swtec.sw.service;

/**
 * 业务基类
 * @author shaowei
 *
 */
public interface BaseService {
	 /**
     * 根据页码及总行数获得分页起始记录游标.
     * @param page 页码
     * @param rows 每页行数
     * @return 记录起始游标
     */
    int getBegin(int page, int rows);
}
