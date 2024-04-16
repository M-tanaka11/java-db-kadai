package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Connection con = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		String[][] postsList = {
				{ "1003", "2023-02-08", "昨日の夜は徹夜でした・・", "13" },
				{ "1002", "2023-02-08", "お疲れ様です！", "12" },
				{ "1003", "2023-02-09", "今日も頑張ります！", "18" },
				{ "1001", "2023-02-09", "無理は禁物ですよ！", "17" },
				{ "1002", "2023-02-10", "明日から連休ですね！", "20" }
		};

		try {
			// データベースに接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"Atnkmsynkonbu11");
			System.out.println("データベース接続成功：" + con);

			System.out.println("レコードの追加を実行します");

			String sql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES (?, ?, ?, ?);";
			preparedStatement = con.prepareStatement(sql);
			int rowCnt = 0;
			for (int i = 0; i < postsList.length; i++) {
				preparedStatement.setString(1, postsList[i][0]);
				preparedStatement.setString(2, postsList[i][1]);
				preparedStatement.setString(3, postsList[i][2]);
				preparedStatement.setString(4, postsList[i][3]);
				preparedStatement.executeUpdate();
				rowCnt++;
			}
			System.out.println(rowCnt + "件のレコードが追加されました");

			System.out.println("ユーザーIDが1002のレコードを検索しました");

			statement = con.createStatement();
			sql = "SELECT * FROM posts WHERE user_id = 1002;";
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				String at = result.getString("posted_at");
				String content = result.getString("post_content");
				int likes = result.getInt("likes");
				System.out.println(result.getRow() + "件目：投稿日時 = " + at
						+ "／投稿内容 = " + content + "／いいね数 = " + likes);
			}
		} catch (SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ignore) {
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

}
