package mas.mapper;

import mas.model.dto.IngredientVolumeDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class IngredientVolumeMapper extends AbstractMapper {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionManager.getFactory();

    public static IngredientVolumeDTO selectIngredientVolumeById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectOne("selectIngredientVolumeById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<IngredientVolumeDTO> selectIngredientVolumesByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectIngredientVolumesByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<IngredientVolumeDTO> selectAllIngredientVolumes() {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectAllIngredientVolumes");
        } finally {
            closeAllConnections();
        }
    }

    public static void updateIngredientVolume(@Param("ingredientVolume") IngredientVolumeDTO ingredientVolume) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("updateIngredientVolume", ingredientVolume);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertIngredientVolume(@Param("ingredientVolume") IngredientVolumeDTO ingredientVolume) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("insertIngredientVolume", ingredientVolume);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteIngredientVolumeById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteIngredientVolumeById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteIngredientVolumesByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteIngredientVolumesByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
