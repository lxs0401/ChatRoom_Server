package dataBase;

import msg.MsgSignalKeysResp;

import java.sql.*;

/**
 * Created by hcyue on 2016/12/3.
 */

class ConnectionImpl extends DBConnection {
    ConnectionImpl() {
        try {
            Class.forName("org.sqlite.JDBC");

            conn = DriverManager.getConnection("jdbc:sqlite:db/test.db");
//            String url = "jdbc:mysql://localhost:3306/student?useSSL=false"; // �������������ݿ��url
//            String user = "root"; // �����������ݿ���û���     ����  ���� ?useSSL=false  ���о��� ��ŵ���˼����˵����ssl���ӣ����Ƿ�����û��������֤�����ַ�ʽ���Ƽ�ʹ�á�
//            String passWord = "123456"; // �����������ݿ������
//            conn = DriverManager.getConnection(url, user, passWord); // ��������
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}


public abstract class DBConnection {

    protected Connection conn;

    public static DBConnection getInstance() {
        return new ConnectionImpl();
    }

    public void close() throws SQLException {
        conn.close();
    }

    public ResultSet query (String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        return rs;
    }

    public int update(String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        int res = stmt.executeUpdate(sql);
//        conn.commit();
        return res;
    }
    public void insertSecurityKeys(int user_id,int reg_id,byte[] identityKey,byte[] signedPreKey,byte[] signedPreKeySig,int reistrationId,byte[][] preKeys) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO security_keys (user_id,reg_id,idenity_key,sig_prekey,sig_prekey_sig,prekeys ) VALUES (?,?,?,?,?,?)");
        byte [] prekeyss = new byte[3300];
        int index = 0;
        for(byte[] each : preKeys)
        {
            for(byte each2 : each)
            {
                prekeyss[index] = each2;
                index += 1;
            }
        }
        pstmt.setInt(1,user_id);
        pstmt.setInt(2,reg_id);
        pstmt.setBytes(3,identityKey);
        pstmt.setBytes(4,signedPreKey);
        pstmt.setBytes(5,signedPreKeySig);
        pstmt.setBytes(6,prekeyss);
        pstmt.execute();

    }
    public int insertAndGet(String sql) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            return id;
        }
        return -1;
    }

    public MsgSignalKeysResp getFriendsKeys(int friend_id) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM security_keys WHERE user_id = ?");
        pstmt.setInt(1,friend_id);
        ResultSet res = pstmt.executeQuery();
        MsgSignalKeysResp ans = new MsgSignalKeysResp();
        ans.setIdentityKey(res.getBytes(3));
        ans.setReistrationId(res.getInt(2));
        ans.setSignedPreKey(res.getBytes(4));
        ans.setSignedPreKeySig(res.getBytes(5));
        ans.setFirend_id(friend_id);
        byte[][] tp = new byte[100][33];
        byte[] tp2 = res.getBytes(6);
        int index = 0;
        for(int i = 0 ; i < 3300 ; i ++)
        {
            index = i/33;
            tp[index][i%33] = tp2[i];
        }
        ans.setPreKeys(tp[0]);
        ans.setTotalLen(184);
        ans.setType((byte)0x66);
        return ans;
    }
}