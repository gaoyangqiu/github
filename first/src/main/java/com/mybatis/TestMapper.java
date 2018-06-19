package com.mybatis;

import com.beans.Test;

/**
 * @Author: qgy
 * @Date: 2018/4/2 16:44
 * @Description:
 */
public interface TestMapper {
    Test selectByPrimaryKey(Integer userId);
}

