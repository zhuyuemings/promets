package ltd.jezhu.promets.dao.base;

import ltd.jezhu.promets.exception.DaoException;
import ltd.jezhu.promets.dto.base.io.PageInfo;

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
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:51
     */

    Connection getConnection() throws DaoException;

    /**
     * 获取新连接
     * @return {@link null}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:51
     */
    Connection getNewConnection() throws DaoException;

    /**
     * 插入
     * @param statementName statementName
     * @return {@link int}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:52
     */
    int insert(final String statementName) throws DaoException;

    /**
     * 插入
     * @param statementName   statementName
     * @param parameterObject parameterObject
     * @return {@link int}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:52
     */
    int insert(final String statementName, final Object parameterObject) throws DaoException;

    /**
     * 更新
     * @param statementName statementName
     * @return {@link int}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:52
     */
    int update(String statementName) throws DaoException;

    /**
     * 更新
     * @param statementName   statementName
     * @param parameterObject parameterObject
     * @return {@link int}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:52
     */
    int update(final String statementName, final Object parameterObject) throws DaoException;

    /**
     * 删除
     * @param statementName statementName
     * @return {@link int}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:52
     */
    int delete(String statementName) throws DaoException;

    /**
     * 删除
     * @param statementName   statementName
     * @param parameterObject parameterObject
     * @return {@link int}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:52
     */
    int delete(final String statementName, final Object parameterObject) throws DaoException;

    /**
     * 对象查询
     * @param statementName statementName
     * @return {@link T}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:52
     */
    <T> T queryForObject(String statementName) throws DaoException;

    /**
     * 对象查询
     * @param statementName   statementName
     * @param parameterObject parameterObject
     * @return {@link T}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:53
     */
    <T> T queryForObject(final String statementName, final Object parameterObject) throws DaoException;

    /**
     * 列表查询
     * @param statementName statementName
     * @return {@link List}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:53
     */
    <E> List<E> queryForList(String statementName) throws DaoException;

    /**
     * 列表查询
     * @param statementName   statementName
     * @param parameterObject parameterObject
     * @return {@link List}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:53
     */
    <E> List<E> queryForList(final String statementName, final Object parameterObject) throws DaoException;

    /**
     * 批量更新
     * @param statementName statementName
     * @param list          list
     * @return {@link int}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:54
     */
    int batchUpdate(final String statementName, final List list) throws DaoException;

    /**
     * 批量插入
     * @param statementName statementName
     * @param list          list
     * @return {@link int}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:54
     */
    int batchInsert(final String statementName, final List list) throws DaoException;

    /**
     * 批量删除
     * @param statementName statementName
     * @param list          list
     * @return {@link int}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:54
     */
    int batchDelete(final String statementName, final List list) throws DaoException;

    /**
     * 根据位置查询列表
     * @param statementName statementName
     * @param begin         begin
     * @param end           end
     * @return {@link List}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:54
     */
    List queryForListBeginEnd(final String statementName, final int begin, final int end) throws DaoException;

    /**
     * 根据起止位置查询列表
     * @param statementName   statementName
     * @param parameterObject parameterObject
     * @param begin           begin
     * @param end             end
     * @return {@link List}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 9:55
     */
    List queryForListBeginEnd(final String statementName, final Object parameterObject, final int begin, final int end) throws DaoException;

    /**
     * 分页查询
     * @param statementName statementName
     * @param offsize       offsize
     * @param limit         limit
     * @return {@link PageInfo}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 11:36
     */
    PageInfo queryForPagingList(final String statementName, final int offsize, final int limit) throws DaoException;

    /**
     * 分页查询
     * @param statementName   statementName
     * @param parameterObject parameterObject
     * @param offsize         offsize
     * @param limit           limit
     * @return {@link PageInfo}
     * @throws DaoException DaoException
     * @author ymzhu
     * @date 2019/3/22 11:50
     */
    PageInfo queryForPagingList(final String statementName, final Object parameterObject, final int offsize, final int limit) throws DaoException;
}
