package mas.mapper;

import mas.model.dto.TemplateDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class TemplateMapper extends AbstractMapper {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionManager.getFactory();

    public static TemplateDTO selectTemplateById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("selectTemplateById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<TemplateDTO> selectTemplatesByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("selectTemplatesByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<TemplateDTO> selectAllTemplates() {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("selectAllTemplates");
        } finally {
            closeAllConnections();
        }
    }

    public static void updateTemplate(@Param("template") TemplateDTO template) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.update("updateTemplate", template);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertTemplate(@Param("template") TemplateDTO template) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("insertTemplate", template);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteTemplateById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("deleteTemplateById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteTemplatesByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("deleteTemplatesByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
