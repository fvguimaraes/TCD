package br.com.aoj.gateway.utils;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputStreamWrapper extends InputStream {

    private InputStream decorated;

    public InputStreamWrapper(InputStream in) throws IOException {
        try{
            byte[] bDate = IOUtils.toByteArray(in);
            this.decorated = new ByteArrayInputStream(bDate);
        }finally {
            in.close();
        }
    }

    @Override
    public int read() throws IOException {
        return this.decorated.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.decorated.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return this.decorated.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return this.decorated.skip(n);
    }

    @Override
    public int available() throws IOException {
        return this.decorated.available();
    }

    @Override
    public void close() throws IOException {
        this.decorated.reset();
    }

    @Override
    public synchronized void mark(int readlimit) {
        this.decorated.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        this.decorated.reset();
    }

    @Override
    public boolean markSupported() {
        return this.decorated.markSupported();
    }
}
