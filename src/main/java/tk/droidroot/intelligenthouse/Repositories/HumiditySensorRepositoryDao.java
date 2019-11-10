package tk.droidroot.intelligenthouse.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HumiditySensorRepositoryDao {
    @Autowired
    private NamedParameterJdbcTemplate template;

    public void save(){

    }
}
