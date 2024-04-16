package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Connection con = null;
		Statement statement = null;

		try {
			// データベースに接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"Atnkmsynkonbu11");

			System.out.println("データベース接続成功：" + con);
			System.out.println("レコード更新を実行します");
			statement = con.createStatement();
			String sql = "UPDATE scores SET score_math = 90, score_english = 80 WHERE id = 5;";

			int rowCnt = statement.executeUpdate(sql);
			System.out.println(rowCnt + "件のレコードが更新されました");
			
			System.out.println("数学・英語の点数が高い順に並べ替えました");
			sql = "SELECT * FROM scores ORDER BY score_math desc, score_english desc";
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int math = result.getInt("score_math");
				int english = result.getInt("score_english");
				
				System.out.println(result.getRow() + "件目：生徒id = " + id
						+ "／氏名 = " + name + "／数学 = " + math + "／英語 = " + english);
			}

		} catch (SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
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
