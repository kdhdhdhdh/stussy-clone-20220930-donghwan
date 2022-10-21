package exception.repository;

import com.stussy.stussyclone20220930donghwan.controller.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountRepository {

    public User findUserEmail(String email) throws Exception;
    public int saveUser(User user) throws Exception;

}
