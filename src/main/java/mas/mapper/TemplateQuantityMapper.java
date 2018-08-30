package mas.mapper;

import mas.model.dto.TemplateQuantityDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class TemplateQuantityMapper extends AbstractMapper {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionManager.getFactory();

    public static TemplateQuantityDTO selectTemplateQuantityById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectOne("selectTemplateQuantityById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<TemplateQuantityDTO> selectTemplateQuantitiesByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectTemplateQuantitiesByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<TemplateQuantityDTO> selectAllTemplateQuantities() {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectAllTemplateQuantities");
        } finally {
            closeAllConnections();
        }
    }

    public static void updateTemplateQuantity(@Param("templateQuantity") TemplateQuantityDTO templateQuantity) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("updateTemplateQuantity", templateQuantity);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertTemplateQuantity(@Param("templateQuantity") TemplateQuantityDTO templateQuantity) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("insertTemplateQuantity", templateQuantity);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteTemplateQuantityById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteTemplateQuantityById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteTemplateQuantitiesByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteTemplateQuantitiesByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
