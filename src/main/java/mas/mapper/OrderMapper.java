package mas.mapper;

import mas.model.dto.OrderDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class OrderMapper extends AbstractMapper {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionManager.getFactory();

    public static OrderDTO selectOrderById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectOne("selectOrderById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<OrderDTO> selectOrdersByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectOrdersByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<OrderDTO> selectAllOrders() {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectAllOrders");
        } finally {
            closeAllConnections();
        }
    }

    public static void updateOrder(@Param("order") OrderDTO order) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("updateOrder", order);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertOrder(@Param("order") OrderDTO order) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("insertOrder", order);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteOrderById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteOrderById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteOrdersByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteOrdersByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
