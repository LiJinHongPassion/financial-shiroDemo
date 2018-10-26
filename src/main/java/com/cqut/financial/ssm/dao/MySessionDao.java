package com.cqut.financial.ssm.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cqut.financial.ssm.tool.SerializableUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class MySessionDao extends EnterpriseCacheSessionDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate = null;

	@Override
	protected Serializable doCreate(Session session) {
		System.out.println(">>>添加时间：" + new Date() + "   sessionId:" + session.getId());
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);
		String sql = "insert into sessions(id, session) values(?,?)";
		jdbcTemplate.update(sql, sessionId,
				SerializableUtils.serialize(session));
		return session.getId();
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		System.out.println(">>>读取时间：" + new Date() + "   sessionId:" + sessionId);
		String sql = "select session from sessions where id=?";
		List<String> sessionStrList = jdbcTemplate.queryForList(sql,
				String.class, sessionId);
		if (sessionStrList.size() == 0)
			return null;
		return SerializableUtils.deserialize(sessionStrList.get(0));
	}

	@Override
	protected void doUpdate(Session session) {
		System.out.println(">>>更新时间：" + new Date() + "   sessionId:" + session.getId());
		if (session instanceof ValidatingSession
				&& !((ValidatingSession) session).isValid()) {
			return;
		}
		String sql = "update sessions set session=? where id=?";
		jdbcTemplate.update(sql, SerializableUtils.serialize(session),
				session.getId());
	}

	/**
	 * session到期后才会删除
	 * @param session
	 */
	@Override
	protected void doDelete(Session session) {
		System.out.println(">>>删除时间：" + new Date() + "   sessionId:" + session.getId());
		String sql = "delete from sessions where id=?";
		jdbcTemplate.update(sql, session.getId());
	}
}
