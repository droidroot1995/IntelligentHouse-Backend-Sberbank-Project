package tk.droidroot.intelligenthouse.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tk.droidroot.intelligenthouse.Models.AlarmEntity;

@Repository
public interface AlarmRepository extends JpaRepository<AlarmEntity, Long> {
}
