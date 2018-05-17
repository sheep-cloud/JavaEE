package cn.colg.core;

import cn.hutool.core.util.RandomUtil;
import tk.mybatis.mapper.genid.GenId;

/**
 * 全局主键
 *
 * @author colg
 */
public class UUIdGenId implements GenId<String> {

    @Override
    public String genId(String table, String column) {
        return RandomUtil.simpleUUID();
    }

}
