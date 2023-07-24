package min.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import min.dao.PolArticleDAO;
import min.model.PolArticle;
import jdbc.connection.ConnectionProvider;

//p650
//ListArticleHandler<->Service<->Dao<->DB
public class PolListArticleService {
	
	PolArticleDAO polArticleDAO= new PolArticleDAO();
	int size = 3;
	
	//총게시글수+목록조회
	//파라미터 int pageNum : 보고싶은페이지
	public PolArticlePage getPolArticlePage(int pageNum) { //이 구절 이해 안됨
		Connection conn;
		try {
			conn= ConnectionProvider.getConnection();
			
			int total = polArticleDAO.selectCount(conn);//총게시글수
			
			List<PolArticle> content = polArticleDAO.select(conn,(pageNum-1)*size,size);
			
			
			PolArticlePage pap = new PolArticlePage(total, pageNum, size, content);
			System.out.println("PolListArticleService- getPolArticlePage()의 결과 pap="+pap);
			return pap;
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}

}




