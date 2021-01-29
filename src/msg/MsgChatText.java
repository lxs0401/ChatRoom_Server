package msg;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MsgChatText extends MsgHead {
	private byte[] msgText;

	public byte[] getMsgText() {
		return msgText;
	}

	public void setMsgText(byte[] msgText) {
		this.msgText = msgText;
	}
	public MsgChatText() {}
	public MsgChatText(int from, int target, byte[] text) {
		int totalLen = 13;
		byte msgType = 0x04;
		byte[] data = text;

		totalLen += data.length;

		setTotalLen(totalLen);
		setType(msgType);
		setDest(target);
		setSrc(from);
		setMsgText(text);
	}
	@Override
	public byte[] packMessage() throws IOException {
		ByteArrayOutputStream bous = new ByteArrayOutputStream();
		DataOutputStream dous = new DataOutputStream(bous);
		packMessageHead(dous);
		dous.write(getMsgText());
		dous.flush();
		byte[] data = bous.toByteArray();
		return data;
	}

}
