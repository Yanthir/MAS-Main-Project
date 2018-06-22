package mas.manager;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.Reader;

public class MyBatisSqlSessionManager implements ServletContextListener {
    private static SqlSessionFactory factory;

    public static SqlSessionFactory getFactory() {
        if(factory == null) {
            try {
                String resource = "mybatis/config.xml";
                Reader reader = Resources.getResourceAsReader(resource);

                SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
                factory = builder.build(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return factory;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // Do nothing
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        SqlSessionFactory factory = getFactory();
        if(factory != null) {
            ((PooledDataSource) factory.getConfiguration().getEnvironment().getDataSource()).forceCloseAll();
        }
    }
}
