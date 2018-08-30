package mas.mapper;

import mas.model.dto.PersonDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PersonMapper extends AbstractMapper {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionManager.getFactory();

    public static PersonDTO selectPersonById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectOne("selectPersonById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<PersonDTO> selectPersonsByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectPersonsByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<PersonDTO> selectAllPersons() {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectAllPersons");
        } finally {
            closeAllConnections();
        }
    }

    public static void updatePerson(@Param("person") PersonDTO person) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("updatePerson", person);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertPerson(@Param("person") PersonDTO person) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("insertPerson", person);
        } finally {
            closeAllConnections();
        }
    }

    public static void deletePersonById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deletePersonById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deletePersonsByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deletePersonsByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
