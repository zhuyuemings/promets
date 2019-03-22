package ltd.jezhu.promets.dao.user;

import ltd.jezhu.promets.dto.user.WxUserInfoDTO;
import ltd.jezhu.promets.system.dao.BaseDao;
import ltd.jezhu.promets.system.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 用户dao
 * @author ymzhu
 * @date 2019/3/22 15:41
 */
@Component
public class WxUserInfoDao {

    private final static String NAMESPACE_PREFIX = "nm.user.";
    private BaseDao baseDao;

    @Autowired
    public WxUserInfoDao(BaseDao baseDao) {
        Assert.notNull(baseDao, "baseDao must not be null!");
        this.baseDao = baseDao;
    }

    /**
     * 保存微信用户信息
     * @param dto dto
     * @return {@link int}
     * @author ymzhu
     * @date 2019/3/22 16:17
     */
    public int saveWxUserInfo(WxUserInfoDTO dto) throws SystemException {
        return baseDao.insert(NAMESPACE_PREFIX + "saveWxUserInfo");
    }

}
