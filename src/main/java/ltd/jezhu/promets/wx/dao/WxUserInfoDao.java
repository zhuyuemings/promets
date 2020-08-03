package ltd.jezhu.promets.wx.dao;

import ltd.jezhu.promets.base.util.InjectUtils;
import ltd.jezhu.promets.base.dao.BaseDao;
import ltd.jezhu.promets.wx.dto.WxUserInfoDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户dao
 * @author ymzhu
 * @date 2019/3/22 15:41
 */
@Component
public class WxUserInfoDao {

    private final static String NAMESPACE_PREFIX = "wx.userinfo.";
    private final BaseDao baseDao;

    @Autowired
    public WxUserInfoDao(BaseDao baseDao) {
        this.baseDao = InjectUtils.check(baseDao);
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
     * 根据openId查询用户信息
     * @param openId openId
     * @return WxUserInfoDTO
     * @author ymzhu
     * @date 2019/3/23 2:05
     */
    public WxUserInfoDTO getWxUserInfoByOpenid(String openId) {
        if (StringUtils.isBlank(openId)) {
            return null;
        }
        WxUserInfoDTO dto = new WxUserInfoDTO();
        dto.setOpenId(openId);
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
     * 保存微信用户unionId(有则更新，无则插入)
     * @param openId  openId
     * @param unionId unionId
     * @return {@link boolean}
     * @author ymzhu
     * @date 2019/4/9 10:53
     */
    public boolean saveUnionid(String openId, String unionId) {
        if (StringUtils.isAnyBlank(openId, unionId)) {
            return false;
        }
        WxUserInfoDTO wxUserInfoDTO = this.getWxUserInfoByOpenid(openId);
        if (null == wxUserInfoDTO) {
            // 新增
            wxUserInfoDTO = new WxUserInfoDTO();
            wxUserInfoDTO.setOpenId(openId);
            wxUserInfoDTO.setUnionId(unionId);
            return this.saveWxUserInfo(wxUserInfoDTO) == 1;
        }
        // 检查更新（理论上不会改变，只会从无到有）
        if (!unionId.equals(wxUserInfoDTO.getUnionId())) {
            // 更新
            wxUserInfoDTO.setUnionId(unionId);
            return this.updateWxUserInfo(wxUserInfoDTO) == 1;
        }
        return true;
    }

}
