package mas.mapper;

import mas.model.dto.ReportDTO;
import mas.manager.MyBatisSqlSessionManager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ReportMapper extends AbstractMapper {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionManager.getFactory();

    public static ReportDTO selectReportById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectOne("selectReportById", id);
        } finally {
            closeAllConnections();
        }
    }

    public static List<ReportDTO> selectReportsByIds(@Param("id") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectReportsByIds", ids);
        } finally {
            closeAllConnections();
        }
    }

    public static List<ReportDTO> selectAllReports() {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            return session.selectList("selectAllReports");
        } finally {
            closeAllConnections();
        }
    }

    public static void updateReport(@Param("report") ReportDTO report) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.update("updateReport", report);
        } finally {
            closeAllConnections();
        }
    }

    public static void insertReport(@Param("report") ReportDTO report) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.insert("insertReport", report);
        } finally {
            closeAllConnections();
        }
    }

    public static void deleteReportById(@Param("id") String id) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteReportById", id);
        } finally {
            closeAllConnections();
        }
    }
    public static void deleteReportsByIds(@Param("ids") List<String> ids) {
        try(SqlSession session = sqlSessionFactory.openSession(true)) {
            session.delete("deleteReportsByIds", ids);
        } finally {
            closeAllConnections();
        }
    }
}
