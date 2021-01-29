package msg;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MsgCanRequest extends MsgHead{
    int req_user_id;

    public int getReq_user_id() {
        return req_user_id;
    }

    public void setReq_user_id(int req_user_id) {
        this.req_user_id = req_user_id;
    }
    @Override
    public byte[] packMessage() throws IOException {
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        DataOutputStream dous = new DataOutputStream(bous);
        packMessageHead(dous);
        dous.writeInt(getReq_user_id());
        dous.flush();
        byte[] data = bous.toByteArray();
        return data;
    }
}
