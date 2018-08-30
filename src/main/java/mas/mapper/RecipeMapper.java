package mas.mapper;

import mas.model.dto.RecipeDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class RecipeMapper extends AbstractMapper {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionManager.getFactory();

    public static RecipeDTO selectRecipeById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectOne("selectRecipeById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<RecipeDTO> selectRecipesByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectRecipesByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<RecipeDTO> selectAllRecipes() {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectAllRecipes");
        } finally {
            closeAllConnections();
        }
    }

    public static void updateRecipe(@Param("recipe") RecipeDTO recipe) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("updateRecipe", recipe);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertRecipe(@Param("recipe") RecipeDTO recipe) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("insertRecipe", recipe);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteRecipeById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteRecipeById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteRecipesByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteRecipesByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
