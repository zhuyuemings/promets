package ltd.jezhu.promets.system.dao;

import ltd.jezhu.promets.system.exception.SystemException;
import ltd.jezhu.promets.system.out.PageInfo;

import java.sql.Connection;
import java.util.List;

/**
 * 公共dao
 * @author ymzhu
 * @date 2019/3/22 9:49
 */
public interface BaseDao {

    /**
     * 获取连接
     * @return {@link Connection}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:51
     */

    Connection getConnection() throws SystemException;

    /**
     * 获取新连接
     * @return {@link null}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:51
     */
    Connection getNewConnection() throws SystemException;

    /**
     * 插入
     * @param statementName statementName
     * @return {@link int}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:52
     */
    int insert(final String statementName) throws SystemException;

    /**
     * 插入
     * @param statementName   statementName
     * @param parameterObject parameterObject
     * @return {@link int}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:52
     */
    int insert(final String statementName, final Object parameterObject) throws SystemException;

    /**
     * 更新
     * @param statementName statementName
     * @return {@link int}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:52
     */
    int update(String statementName) throws SystemException;

    /**
     * 更新
     * @param statementName   statementName
     * @param parameterObject parameterObject
     * @return {@link int}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:52
     */
    int update(final String statementName, final Object parameterObject) throws SystemException;

    /**
     * 删除
     * @param statementName statementName
     * @return {@link int}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:52
     */
    int delete(String statementName) throws SystemException;

    /**
     * 删除
     * @param statementName   statementName
     * @param parameterObject parameterObject
     * @return {@link int}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:52
     */
    int delete(final String statementName, final Object parameterObject) throws SystemException;

    /**
     * 对象查询
     * @param statementName statementName
     * @return {@link T}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:52
     */
    <T> T queryForObject(String statementName) throws SystemException;

    /**
     * 对象查询
     * @param statementName   statementName
     * @param parameterObject parameterObject
     * @return {@link T}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:53
     */
    <T> T queryForObject(final String statementName, final Object parameterObject) throws SystemException;

    /**
     * 列表查询
     * @param statementName statementName
     * @return {@link List}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:53
     */
    <E> List<E> queryForList(String statementName) throws SystemException;

    /**
     * 列表查询
     * @param statementName   statementName
     * @param parameterObject parameterObject
     * @return {@link List}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:53
     */
    <E> List<E> queryForList(final String statementName, final Object parameterObject) throws SystemException;

    /**
     * 批量更新
     * @param statementName statementName
     * @param list          list
     * @return {@link int}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:54
     */
    int batchUpdate(final String statementName, final List list) throws SystemException;

    /**
     * 批量插入
     * @param statementName statementName
     * @param list          list
     * @return {@link int}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:54
     */
    int batchInsert(final String statementName, final List list) throws SystemException;

    /**
     * 批量删除
     * @param statementName statementName
     * @param list          list
     * @return {@link int}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:54
     */
    int batchDelete(final String statementName, final List list) throws SystemException;

    /**
     * 根据位置查询列表
     * @param statementName statementName
     * @param begin         begin
     * @param end           end
     * @return {@link List}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:54
     */
    List queryForListBeginEnd(final String statementName, final int begin, final int end) throws SystemException;

    /**
     * 根据起止位置查询列表
     * @param statementName   statementName
     * @param parameterObject parameterObject
     * @param begin           begin
     * @param end             end
     * @return {@link List}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 9:55
     */
    List queryForListBeginEnd(final String statementName, final Object parameterObject, final int begin, final int end) throws SystemException;

    /**
     * 分页查询
     * @param statementName statementName
     * @param offsize       offsize
     * @param limit         limit
     * @return {@link PageInfo}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 11:36
     */
    PageInfo queryForPagingList(final String statementName, final int offsize, final int limit) throws SystemException;

    /**
     * 分页查询
     * @param statementName   statementName
     * @param parameterObject parameterObject
     * @param offsize         offsize
     * @param limit           limit
     * @return {@link PageInfo}
     * @throws SystemException SystemException
     * @author ymzhu
     * @date 2019/3/22 11:50
     */
    PageInfo queryForPagingList(final String statementName, final Object parameterObject, final int offsize, final int limit) throws SystemException;
}
