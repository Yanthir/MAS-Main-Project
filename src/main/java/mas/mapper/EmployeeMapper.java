package mas.mapper;

import mas.model.dto.EmployeeDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class EmployeeMapper extends AbstractMapper {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionManager.getFactory();

    public static EmployeeDTO selectEmployeeById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectOne("selectEmployeeById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<EmployeeDTO> selectEmployeesByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectEmployeesByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<EmployeeDTO> selectAllEmployees() {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectAllEmployees");
        } finally {
            closeAllConnections();
        }
    }

    public static void updateEmployee(@Param("employee") EmployeeDTO employee) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("updateEmployee", employee);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertEmployee(@Param("employee") EmployeeDTO employee) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("insertEmployee", employee);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteEmployeeById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteEmployeeById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteEmployeesByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteEmployeesByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
