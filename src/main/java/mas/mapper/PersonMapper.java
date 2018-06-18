package mas.mapper;

import mas.model.Person;
import mas.util.MyBatisSqlSessionFactoryProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PersonMapper {
    private static SqlSessionFactory sqlSessionFactory = MyBatisSqlSessionFactoryProvider.get();

    public static Person getById(@Param("id") String id) {
        Person person;
        try(SqlSession session = sqlSessionFactory.openSession()) {
            person = session.selectOne("selectPersonById", id);
        }
        return person;
    }
}
