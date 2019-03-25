package ltd.jezhu.promets.base.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import ltd.jezhu.promets.base.exception.DaoException;
import ltd.jezhu.promets.base.io.PageInfo;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * 公共dao实现
 * @author ymzhu
 * @date 2019/3/22 15:00
 */
@Repository
public class BaseDaoImpl implements BaseDao {

    private static final Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public BaseDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        Assert.notNull(sqlSessionTemplate, "sqlSessionTemplate must not be null!");
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public BaseDaoImpl() {
    }

    public SqlSessionTemplate getSqlSessionTemplate() {
        return this.sqlSessionTemplate;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public Connection getConnection() throws DaoException {
        try {
            return this.sqlSessionTemplate.getConnection();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Connection getNewConnection() throws DaoException {
        DataSource ds;
        try {
            ds = this.sqlSessionTemplate.getConfiguration().getEnvironment().getDataSource();
            return ds.getConnection();
        } catch (SQLException e) {
            log.error("getNewConnection {}", e);
            String msg = e.getMessage();
            if (null == msg || "".equals(msg)) {
                msg = "未知异常信息";
            }
            throw new DaoException(msg, e);
        }
    }

    @Override
    public int insert(final String statementName) throws DaoException {
        return this.insert(statementName, null);
    }

    @Override
    public int insert(final String statementName, final Object parameterObject) throws DaoException {
        try {
            if (log.isDebugEnabled() && parameterObject != null) {
                log.debug(parameterObject.toString());
            }
            return this.sqlSessionTemplate.insert(statementName, parameterObject);
        } catch (Throwable e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int update(String statementName) throws DaoException {
        return this.update(statementName, null);
    }

    @Override
    public int update(final String statementName, final Object parameterObject) throws DaoException {
        try {
            if (log.isDebugEnabled() && parameterObject != null) {
                log.debug(parameterObject.toString());
            }
            return this.sqlSessionTemplate.update(statementName, parameterObject);
        } catch (Throwable e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int delete(String statementName) throws DaoException {
        return this.delete(statementName, null);
    }

    @Override
    public int delete(final String statementName, final Object parameterObject) throws DaoException {
        try {
            if (log.isDebugEnabled() && parameterObject != null) {
                log.debug(parameterObject.toString());
            }
            return this.sqlSessionTemplate.delete(statementName, parameterObject);
        } catch (Throwable e) {
            throw new DaoException(e);
        }
    }

    @Override
    public <T> T queryForObject(String statementName) throws DaoException {
        try {
            return this.sqlSessionTemplate.selectOne(statementName);
        } catch (Throwable e) {
            throw new DaoException(e);
        }
    }

    @Override
    public <T> T queryForObject(final String statementName, final Object parameterObject) throws DaoException {
        try {
            if (log.isDebugEnabled() && parameterObject != null) {
                log.debug(parameterObject.toString());
            }
            return this.sqlSessionTemplate.selectOne(statementName, parameterObject);
        } catch (Throwable e) {
            throw new DaoException(e);
        }
    }

    @Override
    public <E> List<E> queryForList(String statementName) throws DaoException {
        return this.queryForList(statementName, null);
    }

    @Override
    public <E> List<E> queryForList(final String statementName, final Object parameterObject) throws DaoException {
        try {
            if (log.isDebugEnabled() && parameterObject != null) {
                log.debug(parameterObject.toString());
            }
            return this.sqlSessionTemplate.selectList(statementName, parameterObject);
        } catch (Throwable e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int batchUpdate(final String statementName, final List list) throws DaoException {
        SqlSession session = null;
        int count;
        try {
            Integer updateCounts = 0;
            session = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
            int iNum = 0;
            for (Object parameterObject : list) {
                session.update(statementName, parameterObject);
                ++iNum;
                if (iNum >= 5000) {
                    updateCounts = updateCounts + this.calculateBatchNum(session.flushStatements());
                }
            }
            updateCounts = updateCounts + this.calculateBatchNum(session.flushStatements());
            session.commit();
            count = updateCounts;
        } catch (Throwable e) {
            throw new DaoException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return count;
    }

    @Override
    public int batchInsert(final String statementName, final List list) throws DaoException {
        SqlSession session = null;
        int count;
        try {
            Integer updateCounts = 0;
            session = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
            int iNum = 0;
            for (Object parameterObject : list) {
                session.insert(statementName, parameterObject);
                ++iNum;
                if (iNum >= 5000) {
                    updateCounts = updateCounts + this.calculateBatchNum(session.flushStatements());
                }
            }
            updateCounts = updateCounts + this.calculateBatchNum(session.flushStatements());
            session.commit();
            count = updateCounts;
        } catch (Throwable e) {
            throw new DaoException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return count;
    }

    @Override
    public int batchDelete(final String statementName, final List list) throws DaoException {
        SqlSession session = null;
        int count;
        try {
            Integer updateCounts = 0;
            session = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH);
            int iNum = 0;
            for (Object parameterObject : list) {
                session.delete(statementName, parameterObject);
                ++iNum;
                if (iNum >= 5000) {
                    updateCounts = updateCounts + this.calculateBatchNum(session.flushStatements());
                }
            }
            updateCounts = updateCounts + this.calculateBatchNum(session.flushStatements());
            session.commit();
            count = updateCounts;
        } catch (Throwable e) {
            throw new DaoException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return count;
    }

    @Override
    public List queryForListBeginEnd(final String statementName, final int begin, final int end) throws DaoException {
        return this.queryForListBeginEnd(statementName, null, begin, end);
    }

    @Override
    public List queryForListBeginEnd(final String statementName, final Object parameterObject, final int begin, final int end) throws DaoException {
        if (begin < 0) {
            throw new DaoException("begin number(" + begin + ") < 0;");
        } else if (begin > end) {
            throw new DaoException("begin number(" + begin + ") < end number (" + end + ");");
        } else {
            try {
                if (log.isDebugEnabled() && parameterObject != null) {
                    log.debug(parameterObject.toString());
                }
                return this.sqlSessionTemplate.selectList(statementName, parameterObject, new RowBounds(begin, end - begin));
            } catch (Throwable e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public PageInfo queryForPagingList(String statementName, int offsize, int limit) throws DaoException {
        return this.queryForPagingList(statementName, null, offsize, limit);
    }

    @Override
    public PageInfo queryForPagingList(final String statementName, final Object parameterObject, final int offsize, final int limit) throws DaoException {
        PageInfo pageInfo;
        try {
            Page<?> page = PageHelper.startPage(offsize, limit, true);
            List result = this.sqlSessionTemplate.selectList(statementName, parameterObject);
            pageInfo = PageInfo.page(result, page.getPageNum(), page.getPageSize(), page.getTotal(), page.getPages());
        } catch (Throwable e) {
            throw new DaoException(e);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    private int calculateBatchNum(List<BatchResult> batchResults) {
        int retNum = 0;
        if (batchResults == null) {
            return 0;
        } else {
            Iterator var3 = batchResults.iterator();
            while (true) {
                int[] numary;
                do {
                    if (!var3.hasNext()) {
                        return retNum;
                    }
                    BatchResult batchResult = (BatchResult) var3.next();
                    numary = batchResult.getUpdateCounts();
                } while (numary == null);
                for (int i : numary) {
                    retNum += numary[i];
                }
            }
        }
    }
}
