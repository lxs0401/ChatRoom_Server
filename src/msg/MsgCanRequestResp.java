package msg;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MsgCanRequestResp extends MsgHead{
    int isset;

    public int getIsset() {
        return isset;
    }

    public void setIsset(int isset) {
        this.isset = isset;
    }
    public byte[] packMessage() throws IOException {
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        DataOutputStream dous = new DataOutputStream(bous);
        packMessageHead(dous);
        dous.writeInt(getIsset());
        dous.flush();
        byte[] data = bous.toByteArray();
        return data;
    }
}
