package cn.hjw.test;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.hjw.bean.Condition;



public class Test {
	public static void main(String[] args) {
		Object obj=null;
		System.out.println((String)obj);
	}
	public static void main2(String[] args) {
		// TODO Auto-generated method stub
		String resource = "com/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session =null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			 session = sqlSessionFactory.openSession();
			// session = SqlSessionManager.newInstance(sqlSessionFactory);
			 Condition p=new Condition();
			// p.setRoomID(2);
			// p.setDescpt("myroom2");
			// Ofmucroom room = session.selectOne("openfire.selectRoom", p);
			 
			 
		        
			 /**
			RoomMapper mapper = session.getMapper(RoomMapper.class);
			Ofmucroom room = mapper.selectRoom(2);*/
			 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			session.close();//openSession();的时候需要使用关闭
		}
		
	}
	public static void main4(String[] args) {
		// TODO Auto-generated method stub
		String resource = "cn/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session =null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			 session = sqlSessionFactory.openSession();
			// session = SqlSessionManager.newInstance(sqlSessionFactory);
			 Condition p=new Condition();
			// p.setDescpt("myroom");
			 /**
			 RoomDao dao=new RoomDao();
			 dao.setSqlSession(session);
			 List<Ofmucroom> list=dao.findByRoom(p, 1, 2);
			 for(Ofmucroom r:list){
				 System.out.println(r.getName());
				}
			
			 //获得分页的相关参数信息，例如：总记录数等，可通过 “pageList.getPaginator()” 对象获得
			    //Get totalCount
		        PageList pageList = (PageList)list;
		        System.out.println("totalCount: " + pageList.getPaginator().getTotalCount());		    
		        //将分页各参数及结果集转换为json
		        ObjectMapper objectMapper = new PageListJsonMapper();
		        System.out.println(objectMapper.writeValueAsString(list));
		        */
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			session.close();//openSession();的时候需要使用关闭
		}
		
	}
}
