package cn.hjw.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.jackson2.PageListJsonMapper;

import cn.hjw.util.SessionFactoryUtil;

public class BaseDao {
	protected SqlSession sqlSession = null;
	public static boolean isOpenSession = false;
	private PageList<?> pageList = null;// 分页数据 （分页用）
	public int count = 0;// 总件数 （分页用）
	public String jsonString = ""; // 分页信息json串（分页用）
	protected ObjectMapper objectMapper = new PageListJsonMapper();

	public BaseDao() {
		if (isOpenSession) {
			this.sqlSession = SessionFactoryUtil.openSession();
		} else {
			this.sqlSession = SessionFactoryUtil.getSession();
		}

	}

	public void closeSession() {
		if (isOpenSession) {
			if (sqlSession != null) {
				this.sqlSession.close();
			}

		}
	}

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	protected void setPageList(List<?> list) {
		if (list != null) {
			this.pageList = (PageList<?>) list;
			this.count = this.pageList.getPaginator().getTotalCount();

		}
	}

	protected void setPageListAsJson(List<?> list) {
		try {

			if (list != null) {
				this.jsonString = objectMapper.writeValueAsString(list);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
