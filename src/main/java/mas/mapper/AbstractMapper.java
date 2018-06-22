package mas.mapper;

import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;

public abstract class AbstractMapper {
    protected static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionManager.getFactory();

    public static void closeAllConnections() {
        ((PooledDataSource) sqlSessionFactory.getConfiguration().getEnvironment().getDataSource()).forceCloseAll();
    }
}
