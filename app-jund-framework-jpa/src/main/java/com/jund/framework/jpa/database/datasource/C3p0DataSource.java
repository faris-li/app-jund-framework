package com.jund.framework.jpa.database.datasource;

import com.mchange.v2.c3p0.AbstractComboPooledDataSource;

import javax.naming.Referenceable;
import java.io.*;

/**
 * Created by zhijund on 2017/9/3.
 */
public class C3p0DataSource extends AbstractComboPooledDataSource implements Serializable,Referenceable{

    public C3p0DataSource(){}

    public C3p0DataSource(boolean autoregister){
        super(autoregister);
    }

    public C3p0DataSource(String configName){
        super(configName);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException{
        oos.writeShort(2);
    }

    private void writeObject(ObjectInputStream ois) throws IOException,ClassNotFoundException{
        short version = ois.readShort();
        switch(version){
            case 2:
                return;
            default:
                throw new IOException("Unsupported Serialized Version: " + version);
        }
    }
}
