package mas.mapper;

import mas.model.dto.BatchDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BatchMapper extends AbstractMapper {

    public static BatchDTO selectBatchById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("selectBatchById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<BatchDTO> selectBatchesByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("selectBatchesByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<BatchDTO> selectAllBatches() {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectList("selectAllBatches");
        } finally {
            closeAllConnections();
        }
    }

    public static void updateBatch(@Param("batch") BatchDTO batch) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.update("updateBatch", batch);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertBatch(@Param("batch") BatchDTO batch) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.insert("insertBatch", batch);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteBatchById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("deleteBatchById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteBatchesByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            session.delete("deleteBatchesByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
