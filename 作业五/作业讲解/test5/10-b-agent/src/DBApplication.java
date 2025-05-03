
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBApplication {
	public static Connection conn = null;
	public ResultSet rs = null;

	private String databaseDriver;
	private String databaseConnStr;

	public DBApplication(String dBDriver, String dBConnStr) {
		databaseDriver = dBDriver;
		databaseConnStr = dBConnStr;
		try {
			Class.forName(databaseDriver);
			conn = DriverManager.getConnection(databaseConnStr);
		} catch (java.lang.ClassNotFoundException | SQLException e) {
			System.err.println("�����������д���:" + e.getMessage());
			System.out.print("ִ�в����д���:" + e.getMessage()); // ������ͻ���
		}
	}

	public int executeInsert(String sql) {
		int num = 0;
		try {
			Statement stmt = conn.createStatement();
			stmt.setQueryTimeout(30);
			System.out.println(sql + ";;;;");
			num = stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			System.err.println("ִ�в����д���:" + ex.getMessage());
			System.out.print("ִ�в����д���:" + ex.getMessage()); // ������ͻ���
		}

//       closeDataBase();
		return num;
	}

	public ResultSet executeQuery(String sql) {
		rs = null;
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println("ִ�в�ѯ�д���:" + ex.getMessage() + sql);
			System.out.print("ִ�в�ѯ�д���:" + ex.getMessage()); // ������ͻ���
		}

		return rs;
	}

	public int executeDelete(String sql) {
		int num = 0;
		try {
			Statement stmt = conn.createStatement();
			num = stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			System.err.println("ִ��ɾ���д���:" + ex.getMessage());
			System.out.print("ִ��ɾ���д���:" + ex.getMessage()); // ������ͻ���
		}

//       closeDataBase();
		return num;
	}

	public void closeDataBase() {
		try {
			conn.close();
		} catch (Exception end) {
			System.err.println("ִ�йر�Connection�����д���" + end.getMessage());
			System.out.print("ִ��ִ�йر�Connection�����д����д���:" + end.getMessage()); // ������ͻ���
		}
	}
}
