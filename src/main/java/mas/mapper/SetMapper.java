package mas.mapper;

import mas.model.dto.SetDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class SetMapper extends AbstractMapper {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionManager.getFactory();

    public static SetDTO selectSetById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("selectSetById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<SetDTO> selectSetsByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("selectSetsByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<SetDTO> selectAllSets() {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("selectAllSets");
        } finally {
            closeAllConnections();
        }
    }

    public static void updateSet(@Param("set") SetDTO set) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.update("updateSet", set);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertSet(@Param("set") SetDTO set) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("insertSet", set);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteSetById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("deleteSetById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteSetsByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("deleteSetsByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
