package mas.mapper;

import mas.model.dto.SupplierDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class SupplierMapper extends AbstractMapper {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionManager.getFactory();

    public static SupplierDTO selectSupplierById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("selectSupplierById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<SupplierDTO> selectSuppliersByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("selectSuppliersByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<SupplierDTO> selectAllSuppliers() {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("selectAllSuppliers");
        } finally {
            closeAllConnections();
        }
    }

    public static void updateSupplier(@Param("supplier") SupplierDTO supplier) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.update("updateSupplier", supplier);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertSupplier(@Param("supplier") SupplierDTO supplier) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("insertSupplier", supplier);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteSupplierById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("deleteSupplierById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteSuppliersByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("deleteSuppliersByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
