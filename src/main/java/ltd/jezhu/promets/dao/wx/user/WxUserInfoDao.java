package ltd.jezhu.promets.dao.wx.user;

import ltd.jezhu.promets.dao.base.BaseDao;
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
    public int saveWxUserInfo(WxUserInfoDTO dto) {
        return baseDao.insert(NAMESPACE_PREFIX + "saveWxUserInfo", dto);
    }

    /**
     * 根据openid查询用户信息
     * @param openid openid
     * @return WxUserInfoDTO
     * @author ymzhu
     * @date 2019/3/23 2:05
     */
    public WxUserInfoDTO getWxUserInfoByOpenid(String openid) {
        if (StringUtils.isBlank(openid)) {
            return null;
        }
        WxUserInfoDTO dto = new WxUserInfoDTO();
        dto.setOpenId(openid);
        return baseDao.queryForObject(NAMESPACE_PREFIX + "getWxUserInfoByOpenid", dto);
    }

    /**
     * 更新微信用户信息
     * @param dto dto
     * @return int
     * @author ymzhu
     * @date 2019/3/23 2:05
     */
    public int updateWxUserInfo(WxUserInfoDTO dto) {
        return baseDao.update(NAMESPACE_PREFIX + "updateWxUserInfo", dto);
    }

    /**
     * 保存微信用户unionid(有则更新，无则插入)
     * @param openid  openid
     * @param unionid unionid
     * @return {@link boolean}
     * @author ymzhu
     * @date 2019/4/9 10:53
     */
    public boolean saveUnionid(String openid, String unionid) {
        if (StringUtils.isAnyBlank(openid, unionid)) {
            return false;
        }
        WxUserInfoDTO wxUserInfoDTO = this.getWxUserInfoByOpenid(openid);
        if (null == wxUserInfoDTO) {
            // 新增
            wxUserInfoDTO = new WxUserInfoDTO();
            wxUserInfoDTO.setOpenId(openid);
            wxUserInfoDTO.setUnionId(unionid);
            return this.saveWxUserInfo(wxUserInfoDTO) == 1;
        }
        // 检查更新（理论上不会改变，只会从无到有）
        if (!unionid.equals(wxUserInfoDTO.getUnionId())) {
            // 更新
            wxUserInfoDTO.setUnionId(unionid);
            return this.updateWxUserInfo(wxUserInfoDTO) == 1;
        }
        return true;
    }

}
