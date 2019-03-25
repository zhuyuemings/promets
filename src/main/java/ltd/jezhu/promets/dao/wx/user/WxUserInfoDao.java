package ltd.jezhu.promets.dao.wx.user;

import ltd.jezhu.promets.dao.base.BaseDao;
import ltd.jezhu.promets.exception.DaoException;
import ltd.jezhu.promets.dto.wx.user.WxUserInfoDTO;
import org.apache.commons.lang3.StringUtils;
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

    private final static String NAMESPACE_PREFIX = "wx.userinfo.";
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
    public int saveWxUserInfo(WxUserInfoDTO dto) throws DaoException {
        return baseDao.insert(NAMESPACE_PREFIX + "saveWxUserInfo", dto);
    }

    /**
     * 根据openid查询用户信息
     * @param openid openid
     * @return WxUserInfoDTO
     * @author ymzhu
     * @date 2019/3/23 2:05
     */
    public WxUserInfoDTO getWxUserInfoByOpenid(String openid) throws DaoException {
        if (StringUtils.isBlank(openid)) {
            return null;
        }
        WxUserInfoDTO dto = new WxUserInfoDTO();
        dto.setOpenid(openid);
        return baseDao.queryForObject(NAMESPACE_PREFIX + "getWxUserInfoByOpenid", dto);
    }

    /**
     * 更新微信用户信息
     * @param dto dto
     * @return int
     * @author ymzhu
     * @date 2019/3/23 2:05
     */
    public int updateWxUserInfo(WxUserInfoDTO dto) throws DaoException {
        return baseDao.update(NAMESPACE_PREFIX + "updateWxUserInfo", dto);
    }

}
