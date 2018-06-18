package mas.util;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.Reader;

public class MyBatisSqlSessionFactoryProvider implements ServletContextListener {
    private static SqlSessionFactory factory;

    static {
        try {
            String resource = "mybatis/config.xml";
            Reader reader = Resources.getResourceAsReader(resource);

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory = builder.build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory get() {
        return factory;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // Do nothing
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ((PooledDataSource)factory.getConfiguration().getEnvironment().getDataSource()).forceCloseAll();
    }
}
