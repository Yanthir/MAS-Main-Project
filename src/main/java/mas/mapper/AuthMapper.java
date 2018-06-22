package mas.mapper;

import mas.model.dto.AuthDTO;
import mas.model.dto.BatchDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AuthMapper extends AbstractMapper {
    public static AuthDTO selectAuthByLogin(@Param("login") String login) {
        try(SqlSession session = sqlSessionFactory.openSession()) {
            return session.selectOne("selectAuthByLogin", login);
        } finally {
            closeAllConnections();
        }
    }
}
