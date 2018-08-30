package mas.mapper;

import mas.model.dto.VesselDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class VesselMapper extends AbstractMapper {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionManager.getFactory();

    public static VesselDTO selectVesselById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectOne("selectVesselById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<VesselDTO> selectVesselsByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectVesselsByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<VesselDTO> selectAllVessels() {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectAllVessels");
        } finally {
            closeAllConnections();
        }
    }

    public static void updateVessel(@Param("vessel") VesselDTO vessel) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("updateVessel", vessel);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertVessel(@Param("vessel") VesselDTO vessel) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("insertVessel", vessel);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteVesselById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteVesselById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteVesselsByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteVesselsByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
