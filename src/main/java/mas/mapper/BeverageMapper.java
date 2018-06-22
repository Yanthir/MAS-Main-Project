package mas.mapper;

import mas.model.dto.BeverageDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BeverageMapper extends AbstractMapper {

    public static BeverageDTO selectBeverageById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("selectBeverageById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<BeverageDTO> selectBeveragesByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("selectBeveragesByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<BeverageDTO> selectAllBeverages() {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("selectAllBeverages");
        } finally {
            closeAllConnections();
        }
    }

    public static void insertBeverage(@Param("beverage") BeverageDTO beverage) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("insertBeverage", beverage);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteBeverageById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("deleteBeverageById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteBeveragesByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("deleteBeveragesByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
