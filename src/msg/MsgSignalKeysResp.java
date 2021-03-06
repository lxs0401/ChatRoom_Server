package msg;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MsgSignalKeysResp extends MsgHead{
    private int reistrationId;
    private byte[] preKeys;
    private byte[] identityKey;
    private byte[] signedPreKey;
    private byte[] signedPreKeySig;
    int firend_id;

    public int getReistrationId() {
        return reistrationId;
    }

    public void setReistrationId(int reistrationId) {
        this.reistrationId = reistrationId;
    }

    public byte[] getPreKeys() {
        return preKeys;
    }

    public void setPreKeys(byte[] preKeys) {
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

    public byte[] getSignedPreKeySig() {
        return signedPreKeySig;
    }

    public void setSignedPreKeySig(byte[] signedPreKeySig) {
        this.signedPreKeySig = signedPreKeySig;
    }

    public int getFirend_id() {
        return firend_id;
    }

    public void setFirend_id(int firend_id) {
        this.firend_id = firend_id;
    }
    @Override
    public byte[] packMessage() throws IOException {
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        DataOutputStream dous = new DataOutputStream(bous);
        packMessageHead(dous);

        dous.writeInt(getReistrationId());
        dous.write(preKeys);
        dous.write(identityKey);
        dous.write(signedPreKey);
        dous.write(signedPreKeySig);
        dous.writeInt(firend_id);
        dous.flush();
        byte[] data = bous.toByteArray();
        return data;
    }
}
