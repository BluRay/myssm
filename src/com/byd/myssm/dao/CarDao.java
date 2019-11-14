package com.byd.myssm.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.byd.myssm.entity.Worker;

public interface CarDao {
	public List<Worker> getWorkerList(@Param("sort") String sort,@Param("order") String order,@Param("offset") int offset, @Param("limit") int limit);

}
