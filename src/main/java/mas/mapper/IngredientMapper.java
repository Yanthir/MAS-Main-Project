package mas.mapper;

import mas.model.dto.IngredientDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class IngredientMapper extends AbstractMapper {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionManager.getFactory();

    public static IngredientDTO selectIngredientById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectOne("selectIngredientById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<IngredientDTO> selectIngredientsByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectIngredientsByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<IngredientDTO> selectAllIngredients() {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectAllIngredients");
        } finally {
            closeAllConnections();
        }
    }

    public static void updateIngredient(@Param("ingredient") IngredientDTO ingredient) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("updateIngredient", ingredient);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertIngredient(@Param("ingredient") IngredientDTO ingredient) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("insertIngredient", ingredient);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteIngredientById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteIngredientById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteIngredientsByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteIngredientsByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
