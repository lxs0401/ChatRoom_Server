package msg;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/*
 * MsgReg为注册消息体
 */
public class MsgReg extends MsgHead {

	private String nikeName;
	private String pwd;
	private int reistrationId;
	private byte[][] preKeys;
	private byte[] identityKey;
	private byte[] signedPreKey;
	private byte[] signedPreKeySig;

	public byte[] getSignedPreKeySig() {
		return signedPreKeySig;
	}

	public void setSignedPreKeySig(byte[] signedPreKeySig) {
		this.signedPreKeySig = signedPreKeySig;
	}

	public int getReistrationId() {
		return reistrationId;
	}

	public void setReistrationId(int reistrationId) {
		this.reistrationId = reistrationId;
	}

	public byte[][] getPreKeys() {
		return preKeys;
	}

	public void setPreKeys(byte[][] preKeys) {
		this.preKeys = preKeys;
	}

	public byte[] getIdentityKey() {
		return identityKey;
	}

	public void setIdentityKey(byte[] identityKey) {
		this.identityKey = identityKey;
	}

	public byte[] getSignedPreKey() {
		return signedPreKey;
	}

	public void setSignedPreKey(byte[] signedPreKey) {
		this.signedPreKey = signedPreKey;
	}



	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNikeName() {
		return nikeName;
	}

	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}

	@Override
	public byte[] packMessage() throws IOException {
		ByteArrayOutputStream bous = new ByteArrayOutputStream();
		DataOutputStream dous = new DataOutputStream(bous);
		packMessageHead(dous);
		writeString(dous, 10, getNikeName());
		writeString(dous, 10, getPwd());
		dous.flush();
		byte[] data = bous.toByteArray();
		return data;
	}
}
