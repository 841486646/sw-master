package com.swtec.sw.service.impl;

import com.swtec.sw.service.BaseService;

public class BaseServiceImpl implements BaseService {
	@Override
	public int getBegin(int page, int rows) {
		int begin = 0;
        if (page > 1){
            begin = (page - 1) * rows;
        }
        return begin;
	}
}
