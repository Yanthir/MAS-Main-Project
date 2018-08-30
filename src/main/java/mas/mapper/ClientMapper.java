package mas.mapper;

import mas.model.dto.ClientDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ClientMapper extends AbstractMapper {

    public static ClientDTO selectClientById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectOne("selectClientById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<ClientDTO> selectClientsByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectClientsByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<ClientDTO> selectAllClients() {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectAllClients");
        } finally {
            closeAllConnections();
        }
    }

    public static void updateClient(@Param("client") ClientDTO client) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("updateClient", client);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertClient(@Param("client") ClientDTO client) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("insertClient", client);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteClientById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteClientById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteClientsByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteClientsByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
