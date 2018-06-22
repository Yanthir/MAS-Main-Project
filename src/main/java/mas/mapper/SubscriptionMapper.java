package mas.mapper;

import mas.model.dto.SubscriptionDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class SubscriptionMapper extends AbstractMapper {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionManager.getFactory();

    public static SubscriptionDTO selectSubscriptionById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("selectSubscriptionById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<SubscriptionDTO> selectSubscriptionsByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("selectSubscriptionsByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<SubscriptionDTO> selectAllSubscriptions() {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("selectAllSubscriptions");
        } finally {
            closeAllConnections();
        }
    }

    public static void updateSubscription(@Param("subscription") SubscriptionDTO subscription) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.update("updateSubscription", subscription);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertSubscription(@Param("subscription") SubscriptionDTO subscription) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("insertSubscription", subscription);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteSubscriptionById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("deleteSubscriptionById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteSubscriptionsByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("deleteSubscriptionsByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
