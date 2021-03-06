package com.byd.myssm.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.byd.myssm.entity.Worker;

public interface CarDao {
	public List<Worker> getWorkerList(@Param("search") String search,@Param("sort") String sort,@Param("order") String order,@Param("offset") int offset, @Param("limit") int limit);
	public int getWorkerTotal(@Param("search") String search);
	public List<Map<String,Object>> getManageInfo(Map<String,Object> params);
}
